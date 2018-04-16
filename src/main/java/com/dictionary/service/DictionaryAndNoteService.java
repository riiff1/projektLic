package com.dictionary.service;

import com.dictionary.dao.DictionaryAndNoteDao;
import com.dictionary.dto.NoteAndDictionaryDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DictionaryAndNoteService {

    @Autowired
    private SessionUser sessionUser;

    @Autowired
    private DictionaryAndNoteDao dictionaryAndNoteDao;

    public List<NoteAndDictionaryDto> getDictionaryAndNoteBySpecialization(long specializationId) {
        long userId = sessionUser.getUser().getUserId();
        List<NoteAndDictionaryDto> dictionaryAndNote = dictionaryAndNoteDao.getDictionaryAndNoteForUser(specializationId, userId);
        return dictionaryAndNote;
    }
}
