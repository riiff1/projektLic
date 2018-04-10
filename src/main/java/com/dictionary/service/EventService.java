package com.dictionary.service;

import com.dictionary.dao.EventDao;
import com.dictionary.dto.EventDto;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {

    @Autowired
    private SessionUser sessionUser;

    @Autowired
    private EventDao eventDao;

    public List<EventDto> getFutureEventsBySpecialization(int specializationId) {
        return eventDao.getFutureEventsBySpecialization(specializationId);
    }

    public List<EventDto> getFutureEventsBySpecializationByUser(int specializationId) {
        long userId = sessionUser.getUser().getUserId();
        return eventDao.getFutureEventsBySpecializationByUser(userId, specializationId);
    }

    public List<EventDto> getAllEventsByUser() {
        long userId = sessionUser.getUser().getUserId();
        return eventDao.getAllEventsByUser(userId);
    }

    public List<EventDto> getAllEventsBySpecializationByUser(int specializationId) {
        long userId = sessionUser.getUser().getUserId();
        return eventDao.getAllEventsBySpecializationByUser(userId, specializationId);
    }

    public boolean insertToEvent(long eventId) {

        try {
            long userId = sessionUser.getUser().getUserId();
            eventDao.insertToEvent(userId, eventId);
            return true;
        } catch (MySQLIntegrityConstraintViolationException e) {

        }
        return false;
    }
}
