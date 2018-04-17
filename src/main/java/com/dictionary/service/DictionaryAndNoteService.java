package com.dictionary.service;

import com.dictionary.dao.DictionaryAndNoteDao;
import com.dictionary.dto.NoteAndDictionaryDto;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DictionaryAndNoteService {

    @Autowired
    private UserService userService;

    @Autowired
    private DictionaryAndNoteDao dictionaryAndNoteDao;

    public List<NoteAndDictionaryDto> getDictionaryAndNoteBySpecialization(long specializationId) {
        List<NoteAndDictionaryDto> dictionaryAndNote = dictionaryAndNoteDao.getDictionaryAndNoteForUser(specializationId, userService.getLoggedUserId());
        return dictionaryAndNote;
    }

    public void removeNote(long noteId) {
        dictionaryAndNoteDao.removeNote(noteId, userService.getLoggedUserId());
    }

    public void editNote(long noteId, String message) {
        dictionaryAndNoteDao.editNote(noteId, userService.getLoggedUserId(), message);
    }

    public long insertNote(long dictionaryId, String message) {
        try {
            return dictionaryAndNoteDao.insertNote(userService.getLoggedUserId(), dictionaryId, message);
        } catch (MySQLIntegrityConstraintViolationException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
