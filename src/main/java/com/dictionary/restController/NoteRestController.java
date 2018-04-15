package com.dictionary.restController;

import com.dictionary.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NoteRestController {

    @Autowired
    private NoteService noteService;

    @RequestMapping(value = "/note/getNoteMessageForDictionary", method = RequestMethod.GET)
    public String getFutureEventsBySpecialization(@RequestParam("dictionaryId") long dictionaryId) {
        return noteService.getNoteMessageForDictionary(dictionaryId);
    }
}
