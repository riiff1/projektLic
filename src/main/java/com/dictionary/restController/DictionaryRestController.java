package com.dictionary.restController;

import com.dictionary.dto.DictionaryDto;
import com.dictionary.service.DictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DictionaryRestController {

    @Autowired
    private DictionaryService dictionaryService;

    @RequestMapping(value = "/dictionaryForSpecialization", method = RequestMethod.GET)
    public List<DictionaryDto> getFutureEventsBySpecialization(@RequestParam("specializationId") int specializationId) {
        return dictionaryService.getDictionaryForSpecialization(specializationId);
    }
}
