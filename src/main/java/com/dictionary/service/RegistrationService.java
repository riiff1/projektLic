package com.dictionary.service;

import com.dictionary.dao.SpecializationColorDao;
import com.dictionary.dao.SpecializationDao;
import com.dictionary.dao.UserDao;
import com.dictionary.dao.UserRoleDao;
import com.dictionary.dto.SpecializationDto;
import com.dictionary.dto.UserDto;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegistrationService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserRoleDao userRoleDao;
    @Autowired
    private SpecializationColorDao specializationColorDao;
    @Autowired
    private SpecializationDao specializationDao;


    public int registerUser(UserDto userDto) {
        int userId = 0;
        try {
            userId = userDao.insertUser(userDto);
            userRoleDao.insertUserRoles(userId);
            List<SpecializationDto> allSpecialization = specializationDao.getAllSpecialization();
            specializationColorDao.insertDefaultColorForSpecializations(userId, allSpecialization);
        } catch (MySQLIntegrityConstraintViolationException e) {
            e.printStackTrace();
        }
        return userId;
    }
}
