package com.dictionary.service;

import com.dictionary.dao.UserDao;
import com.dictionary.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private SessionUser sessionUser;

    @Autowired
    private UserDao userDao;

    public String getLoggedUser() {
        return sessionUser.getUser().getUserName();
    }

    public long getLoggedUserId() {
        return sessionUser.getUser().getUserId();
    }

    public boolean existUser(String userName) {
        return "".equals(userName)? true : userDao.existUser(userName);
    }

    public boolean updatePassForCurrentUser(String newPass, String passw) {
        long userId = getLoggedUserId();
        String dbPass = userDao.getPasswordByCurrentUser(userId);
        if(!new BCryptPasswordEncoder().matches(passw, dbPass)) {
            return false;
        }
        String encodePass = new BCryptPasswordEncoder().encode(newPass);
        return userDao.updatePass(userId, encodePass);
    }

    public boolean updateEmailForCurrentUser(String newEmail, String passw) {
        long userId = getLoggedUserId();
        String dbPass = userDao.getPasswordByCurrentUser(userId);
        if(!new BCryptPasswordEncoder().matches(passw, dbPass)) {
            return false;
        }
        return userDao.updateEmail(userId, newEmail);
    }

    public UserDto getNickAndEmail() {
        long userId = getLoggedUserId();
        return userDao.getNickAndEmail(userId);
    }
}
