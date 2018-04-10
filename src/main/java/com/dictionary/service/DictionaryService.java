package com.dictionary.service;

import com.dictionary.dao.SpecializationDao;
import com.dictionary.dto.SpecializationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DictionaryService {

    @Autowired
    private SessionUser sessionUser;

    @Autowired
    private SpecializationDao specializationDao;

    public List<SpecializationDto> getAvailableSpecialization() {
        return specializationDao.getAvailableSpecializationByUser(sessionUser.getUser().getUserId());
    }

    public List<SpecializationDto> getAllSpecialization() {
        return specializationDao.getAllSpecialization();
    }
}