package com.dictionary.restController;

import com.dictionary.dto.SpecializationDto;
import com.dictionary.service.SpecializationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SpecializationRestController {

    @Autowired
    private SpecializationService specializationService;

    @RequestMapping(value = "/specialization/availableByCurrentUser", method = RequestMethod.GET)
    public List<SpecializationDto> getAvailableSpecialization() {
        return specializationService.getAvailableSpecialization();
    }

    @RequestMapping(value = "/specialization", method = RequestMethod.GET)
    public List<SpecializationDto> getAllSpecialization() {
        return specializationService.getAllSpecialization();
    }
}
