package com.dictionary.dao;

import com.dictionary.dto.DictionaryDto;
import com.dictionary.mapper.DictionaryMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DictionaryDao extends BaseDao {

    private static final String sqlDictionaryForSpecialization = "select * from TBL_DICTIONARY where "
            + "SPECIALIZATION_ID_FK = ? order by WORD asc;";

    public List<DictionaryDto> getDictionaryForSpecialization(long specializationId) {
        return getTemplate().query(sqlDictionaryForSpecialization, new Object[] { specializationId }, new DictionaryMapper());
    }
}
