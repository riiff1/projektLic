package com.dictionary.mapper;

import com.dictionary.dto.SpecializationColorDto;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SpecializationColorMapper implements RowMapper<SpecializationColorDto> {
    @Override
    public SpecializationColorDto mapRow(ResultSet resultSet, int i) throws SQLException {
        SpecializationColorDto specializationColorDto = new SpecializationColorDto();
        specializationColorDto.setSpecializationColorId(resultSet.getLong("SPECIALIZATION_COLOR_ID"));
        specializationColorDto.setUserId(resultSet.getLong("USER_ID_FK"));
        specializationColorDto.setSpecializationId(resultSet.getLong("SPECIALIZATION_ID_FK"));
        specializationColorDto.setColor(resultSet.getString("COLOR"));
        specializationColorDto.setSpecName(resultSet.getString("NAME_"));
        return specializationColorDto;
    }
}
