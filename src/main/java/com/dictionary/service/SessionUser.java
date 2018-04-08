package com.dictionary.service;

import com.dictionary.dao.UserDao;
import com.dictionary.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class SessionUser {
    @Autowired
    private UserDao userDao;

    public UserDto getUser() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        String nickName = null;
        if (authentication != null) {
            if (authentication.getPrincipal() instanceof UserDetails)
                nickName = ((UserDetails) authentication.getPrincipal()).getUsername();
            else if (authentication.getPrincipal() instanceof String)
                nickName = (String) authentication.getPrincipal();
        }
        return userDao.getActiveUser(nickName);
    }
}
