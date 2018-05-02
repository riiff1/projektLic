package com.dictionary.service;

import com.dictionary.dao.SpecializationColorDao;
import com.dictionary.dao.SpecializationDao;
import com.dictionary.dto.SpecializationColorDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpecializationColorService {

    @Autowired
    private UserService userService;

    @Autowired
    private SpecializationColorDao specializationColorDao;
    
    @Autowired
    private SpecializationDao specializationDao;
    
    public List<SpecializationColorDto> getAvailableSpecializationByUser() {
        long userId = userService.getLoggedUserId();
        return specializationColorDao.getAvailableSpecializationByUser(userId, specializationDao.getAvailableSpecializationByUser(userId));
    }

    public void updateSpecializationColor(List<SpecializationColorDto> specializationColorDto) {
        specializationColorDao.updateSpecializationColorBatch(specializationColorDto);
    }
}
