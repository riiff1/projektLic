package com.dictionary.service;

import com.dictionary.dao.NoteDao;
import com.dictionary.dto.NoteDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NoteService {

    @Autowired
    private SessionUser sessionUser;

    @Autowired
    private NoteDao noteDao;

    public String getNoteMessageForDictionary(long dictionaryId) {
        long userId = sessionUser.getUser().getUserId();
        NoteDto noteDto = noteDao.getNoteMessageForDictionaryForUser(userId, dictionaryId);
        if(noteDto != null) {
            return noteDto.getMessage();
        }
        return null;
    }
}
