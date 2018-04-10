package com.dictionary.restController;

import com.dictionary.dao.SpecializationDao;
import com.dictionary.dto.DictionaryDto;
import com.dictionary.dto.SpecializationDto;
import com.dictionary.service.DictionaryService;
import com.dictionary.service.SessionUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DictionaryRestController {

    @Autowired
    private DictionaryService dictionaryService;

    @RequestMapping(value = "/specialization/availableByCurrentUser", method = RequestMethod.GET)
    public List<SpecializationDto> getAvailableSpecialization() {
        return dictionaryService.getAvailableSpecialization();
    }

    @RequestMapping(value = "/specialization", method = RequestMethod.GET)
    public List<SpecializationDto> getAllSpecialization() {
        return dictionaryService.getAllSpecialization();
    }
}
