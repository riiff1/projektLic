package com.dictionary.restController;

import com.dictionary.dto.EventDto;
import com.dictionary.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EventRestController {

    @Autowired
    private EventService eventService;

    @RequestMapping(value = "/event/futureEventsBySpecialization", method = RequestMethod.GET)
    public List<EventDto> getFutureEventsBySpecialization(@RequestParam("specializationId") int specializationId) {
        return eventService.getFutureEventsBySpecialization(specializationId);
    }

    @RequestMapping(value = "/event/futureEventsBySpecializationByUser", method = RequestMethod.GET)
    public List<EventDto> getFutureEventsBySpecializationByUser(@RequestParam("specializationId") int specializationId) {
        return eventService.getFutureEventsBySpecializationByUser(specializationId);
    }

    @RequestMapping(value = "/event/allEventsByUser", method = RequestMethod.GET)
    public List<EventDto> getAllEventsByUser() {
        return eventService.getAllEventsByUser();
    }

    @RequestMapping(value = "/event/allEventsBySpecializationByUser", method = RequestMethod.GET)
    public List<EventDto> getAllEventsBySpecializationByUser(@RequestParam("specializationId") int specializationId) {
        return eventService.getAllEventsBySpecializationByUser(specializationId);
    }
}
