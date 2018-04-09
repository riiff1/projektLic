package com.dictionary.service;

import com.dictionary.dao.UserDao;
import com.dictionary.dao.UserRoleDao;
import com.dictionary.dto.UserDto;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {

    @Autowired
    UserDao userDao;

    @Autowired
    UserRoleDao userRoleDao;

    public int registerUser(UserDto userDto) {
        int userId = 0;
        try {
            userId = userDao.insertUser(userDto);
            userRoleDao.insertUserRoles(userId);
        } catch (MySQLIntegrityConstraintViolationException e) {
            e.printStackTrace();
        }
        return userId;
    }
}
