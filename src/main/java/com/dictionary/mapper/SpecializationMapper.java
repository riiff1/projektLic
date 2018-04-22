package com.dictionary.mapper;

import com.dictionary.dto.SpecializationDto;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SpecializationMapper implements RowMapper<SpecializationDto> {
    @Override
    public SpecializationDto mapRow(ResultSet resultSet, int i) throws SQLException {
        SpecializationDto specializationDto = new SpecializationDto();
        specializationDto.setSpecializationId(resultSet.getInt("SPECIALIZATION_ID"));
        specializationDto.setName(resultSet.getString("NAME_"));
        specializationDto.setPrize(resultSet.getInt("PRIZE"));
        return specializationDto;
    }
}
