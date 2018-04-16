package com.dictionary.service;

import com.dictionary.dao.SpecializationDao;
import com.dictionary.dto.SpecializationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpecializationService {

    @Autowired
    private UserService userService;

    @Autowired
    private SpecializationDao specializationDao;

    public List<SpecializationDto> getAvailableSpecialization() {
        return specializationDao.getAvailableSpecializationByUser(userService.getLoggedUserId());
    }

    public List<SpecializationDto> getAllSpecialization() {
        return specializationDao.getAllSpecialization();
    }
}