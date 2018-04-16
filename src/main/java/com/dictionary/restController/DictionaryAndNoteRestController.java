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
}
