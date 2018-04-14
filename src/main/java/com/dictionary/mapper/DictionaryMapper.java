package com.dictionary.mapper;

import com.dictionary.dto.DictionaryDto;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DictionaryMapper implements RowMapper<DictionaryDto> {
    @Override
    public DictionaryDto mapRow(ResultSet resultSet, int i) throws SQLException {
        DictionaryDto dictionaryDto = new DictionaryDto();
        dictionaryDto.setDictionaryId(resultSet.getLong("DICTIONARY_ID"));
        dictionaryDto.setSpecjalizationId(resultSet.getLong("SPECIALIZATION_ID_FK"));
        dictionaryDto.setWord(resultSet.getString("WORD"));
        dictionaryDto.setExpression(resultSet.getString("EXPRESSION"));
        return dictionaryDto;
    }
}
