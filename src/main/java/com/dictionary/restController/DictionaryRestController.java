package com.dictionary.restController;

import com.dictionary.dao.SpecializationDao;
import com.dictionary.dto.DictionaryDto;
import com.dictionary.dto.SpecializationDto;
import com.dictionary.service.SessionUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DictionaryRestController {

    @Autowired
    private SessionUser sessionUser;

    @Autowired
    private SpecializationDao specializationDao;

    @RequestMapping(value = "/specialization/availableByCurrentUser", method = RequestMethod.GET)
    public List<SpecializationDto> getAvailableSpecialization() {
        return specializationDao.getAvailableSpecializationByUser(sessionUser.getUser().getUserId());
    }

    @RequestMapping(value = "/specialization", method = RequestMethod.GET)
    public List<SpecializationDto> getAllSpecialization() {
        return specializationDao.getAllSpecialization();
    }
}
