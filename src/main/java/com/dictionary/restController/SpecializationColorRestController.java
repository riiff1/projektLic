package com.dictionary.restController;

import com.dictionary.dto.SpecializationColorDto;
import com.dictionary.service.SpecializationColorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SpecializationColorRestController {

    @Autowired
    private SpecializationColorService specializationColorService;

    @RequestMapping(value = "/specializationColor/availableByCurrentUser", method = RequestMethod.GET)
    public List<SpecializationColorDto> getAvailableSpecialization() {
        return specializationColorService.getAvailableSpecializationByUser();
    }
}
