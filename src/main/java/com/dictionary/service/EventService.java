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
    private UserService userService;

    @Autowired
    private EventDao eventDao;

    public List<EventDto> getFutureEventsBySpecialization(int specializationId) {
        return eventDao.getFutureEventsBySpecialization(specializationId);
    }

    public List<EventDto> getFutureEventsBySpecializationByUser(int specializationId) {
        return eventDao.getFutureEventsBySpecializationByUser(userService.getLoggedUserId(), specializationId);
    }

    public List<EventDto> getAllEventsByUser() {
        return eventDao.getAllEventsByUser(userService.getLoggedUserId());
    }

    public List<EventDto> getAllEventsBySpecializationByUser(int specializationId) {
        return eventDao.getAllEventsBySpecializationByUser(userService.getLoggedUserId(), specializationId);
    }

    public List<EventDto> getAllEventsForAllSpecializationByUser() {
        return eventDao.getAllEventsForAllSpecializationByUser(userService.getLoggedUserId());
    }

    public boolean insertToEvent(long eventId) {

        try {
            eventDao.insertToEvent(userService.getLoggedUserId(), eventId);
            return true;
        } catch (MySQLIntegrityConstraintViolationException e) {

        }
        return false;
    }
}
