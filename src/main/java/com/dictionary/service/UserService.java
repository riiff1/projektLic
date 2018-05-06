package com.dictionary.service;

import com.dictionary.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
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
}
