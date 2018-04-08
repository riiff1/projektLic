package com.dictionary.mapper;

import com.dictionary.dto.UserEventDto;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserEventMapper implements RowMapper<UserEventDto> {
    @Override
    public UserEventDto mapRow(ResultSet resultSet, int i) throws SQLException {
        UserEventDto userEventDto = new UserEventDto();
        userEventDto.setUserEventId(resultSet.getInt("USER_EVENT_ID"));
        userEventDto.setUserId(resultSet.getInt("USER_ID_FK"));
        userEventDto.setEventId(resultSet.getInt("EVENT_ID_FK"));
        return userEventDto;
    }
}
