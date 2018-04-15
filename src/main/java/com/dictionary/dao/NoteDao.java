package com.dictionary.dao;

import com.dictionary.dto.NoteDto;
import com.dictionary.mapper.NoteMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class NoteDao extends BaseDao {

    private static final String sqlNoteMessageForDictionaryForUser = "select * from TBL_NOTE where USER_ID_FK = ? and DICTIONARY_ID_FK = ? limit 1;";

    public NoteDto getNoteMessageForDictionaryForUser(long userId, long dictionaryId) {
        return getTemplate().queryForObject(sqlNoteMessageForDictionaryForUser, new Object[] { userId, dictionaryId }, new NoteMapper());
    }
}
