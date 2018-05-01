package com.dictionary.mapper;

import com.dictionary.dto.EventDto;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EventMapper implements RowMapper<EventDto> {
    @Override
    public EventDto mapRow(ResultSet resultSet, int i) throws SQLException {
        EventDto eventDto = new EventDto();
        eventDto.setEventId(resultSet.getInt("EVENT_ID"));
        eventDto.setEventName(resultSet.getString("EVENT_NAME"));
        eventDto.setSpecializationId(resultSet.getInt("SPECIALIZATION_ID_FK"));
        eventDto.setEventStartTime(resultSet.getTimestamp("EVENT_START_TIMESTAMP"));
        eventDto.setEventEndTime(resultSet.getTimestamp("EVENT_END_TIMESTAMP"));
        eventDto.setAddress(resultSet.getString("ADDRESS"));
        eventDto.setCity(resultSet.getString("CITY"));
        eventDto.setZip(resultSet.getString("ZIP"));
        eventDto.setPhone(resultSet.getString("PHONE"));
        eventDto.setCreationTime(resultSet.getTimestamp("CREATION_TIME"));
        return eventDto;
    }
}
