package com.dictionary.restController;

import com.dictionary.dto.NoteAndDictionaryDto;
import com.dictionary.service.DictionaryAndNoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DictionaryAndNoteRestController {

    @Autowired
    private DictionaryAndNoteService dictionaryAndNoteService;

    @RequestMapping(value = "/getDictionaryAndNoteBySpecialization", method = RequestMethod.GET)
    public List<NoteAndDictionaryDto> getDictionaryAndNoteBySpecialization(@RequestParam("specializationId") long specializationId) {
        return dictionaryAndNoteService.getDictionaryAndNoteBySpecialization(specializationId);
    }

    @RequestMapping(value = "/note/removeNote", method = RequestMethod.DELETE)
    public void removeNote(@RequestParam("noteId") long noteId) {
        dictionaryAndNoteService.removeNote(noteId);
    }

    @RequestMapping(value = "/note/updateNote", method = RequestMethod.POST)
    public void editNote(@RequestParam("noteId") long noteId, @RequestParam("message") String message) {
        dictionaryAndNoteService.editNote(noteId, message);
    }

    @RequestMapping(value = "/note/insertNote", method = RequestMethod.POST)
    public long addNote(@RequestParam("dictionaryId") long dictionaryId, @RequestParam("message") String message) {
        return dictionaryAndNoteService.insertNote(dictionaryId, message);
    }
}
