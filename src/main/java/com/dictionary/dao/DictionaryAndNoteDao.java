package com.dictionary.dao;

import com.dictionary.dto.DictionaryDto;
import com.dictionary.dto.NoteAndDictionaryDto;
import com.dictionary.dto.NoteDto;
import com.dictionary.mapper.NoteMapper;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Component
public class DictionaryAndNoteDao extends BaseDao {

    private static final String sqlNoteMessageForDictionaryForUser = "select * from TBL_NOTE where USER_ID_FK = ? and DICTIONARY_ID_FK = ? limit 1;";

    private static final String sqlDeleteNoteById = "delete from TBL_NOTE where USER_ID_FK = ? and NOTE_ID = ?;";
    private static final String sqlUpdateNote = "update TBL_NOTE set MESSAGE = ? where NOTE_ID = ? and USER_ID_FK = ?;";
    private static final String sqlInsertNote = "insert into TBL_NOTE values (null, ?, ?, ?);";

    @Autowired
    private DictionaryDao dictionaryDao;

    public List<NoteAndDictionaryDto> getDictionaryAndNoteForUser(long specializationId, long userId) {
        List<DictionaryDto> dictionaryForSpecialization = dictionaryDao.getDictionaryForSpecialization(specializationId);
        List<NoteAndDictionaryDto> noteAndDictionaryDto = new ArrayList<NoteAndDictionaryDto>(dictionaryForSpecialization.size());
        
        for(DictionaryDto dictionaryDto: dictionaryForSpecialization) {
            NoteAndDictionaryDto noteAndDictionry = new NoteAndDictionaryDto();
            List<NoteDto> messages = getTemplate().query(sqlNoteMessageForDictionaryForUser, new Object[] { userId, dictionaryDto.getDictionaryId() }, new NoteMapper());
            if(!messages.isEmpty()) {
                noteAndDictionry.setNoteId(messages.get(0).getNoteId());
                noteAndDictionry.setMessage(messages.get(0).getMessage());
                noteAndDictionry.setUserId(messages.get(0).getUserId());
            }

            noteAndDictionry.setDictionaryId(dictionaryDto.getDictionaryId());
            noteAndDictionry.setSpecializationId(dictionaryDto.getSpecjalizationId());
            noteAndDictionry.setWord(dictionaryDto.getWord());
            noteAndDictionry.setExpression(dictionaryDto.getExpression());

            noteAndDictionaryDto.add(noteAndDictionry);
        }
        return noteAndDictionaryDto;
    }

    public void removeNote(long noteId, long userId) {
        getTemplate().update(sqlDeleteNoteById, new Object[]{userId, noteId});
    }

    public void editNote(long noteId, long userId, String message) {
        getTemplate().update(sqlUpdateNote, new Object[]{message, noteId, userId});
    }

    public long insertNote(final long userId, final long dictionaryId, final String message) throws MySQLIntegrityConstraintViolationException {
        //getTemplate().update(sqlInsertNote, new Object[]{userId, dictionaryId, message});

        KeyHolder keyHolder = new GeneratedKeyHolder();
        getTemplate().update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement statement = con.prepareStatement(sqlInsertNote, Statement.RETURN_GENERATED_KEYS);
                statement.setLong(1, userId);
                statement.setLong(2, dictionaryId);
                statement.setString(3, message);
                return statement;
            }
        },keyHolder);
        return keyHolder.getKey().intValue();
    }
}
