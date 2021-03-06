package com.dictionary.restController;

import com.dictionary.dto.SpecializationColorDto;
import com.dictionary.service.SpecializationColorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SpecializationColorRestController {

    @Autowired
    private SpecializationColorService specializationColorService;

    @RequestMapping(value = "/specializationColor/availableByCurrentUser", method = RequestMethod.GET)
    public List<SpecializationColorDto> getAvailableSpecialization() {
        return specializationColorService.getAvailableSpecializationByUser();
    }

    @RequestMapping(value = "/specializationColor/postColor", method = RequestMethod.POST)
    public void setColors(@RequestBody List<SpecializationColorDto> specColorDto) {
        specializationColorService.updateSpecializationColor(specColorDto);
    }
}
