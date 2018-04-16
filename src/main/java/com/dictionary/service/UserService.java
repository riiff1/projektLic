package com.dictionary.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private SessionUser sessionUser;

    public String getLoggedUser() {
        return sessionUser.getUser().getUserName();
    }

    public long getLoggedUserId() {
        return sessionUser.getUser().getUserId();
    }
}
