package com.dictionary.service;

import com.dictionary.dao.DictionaryDao;
import com.dictionary.dto.DictionaryDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DictionaryService {

    @Autowired
    private DictionaryDao dictionaryDao;

    public List<DictionaryDto> getDictionaryForSpecialization(long specializationId) {
        return dictionaryDao.getDictionaryForSpecialization(specializationId);
    }
}
