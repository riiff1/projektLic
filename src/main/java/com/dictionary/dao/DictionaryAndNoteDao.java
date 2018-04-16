package com.dictionary.dao;

import com.dictionary.dto.DictionaryDto;
import com.dictionary.dto.NoteAndDictionaryDto;
import com.dictionary.dto.NoteDto;
import com.dictionary.mapper.NoteMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DictionaryAndNoteDao extends BaseDao {

    private static final String sqlNoteMessageForDictionaryForUser = "select * from TBL_NOTE where USER_ID_FK = ? and DICTIONARY_ID_FK = ? limit 1;";

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
}
