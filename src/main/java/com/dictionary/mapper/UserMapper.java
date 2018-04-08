package com.dictionary.mapper;

import com.dictionary.dto.UserDto;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements RowMapper<UserDto> {
    @Override
    public UserDto mapRow(ResultSet resultSet, int i) throws SQLException {
        UserDto user = new UserDto();
        user.setUserId(resultSet.getInt("USER_ID"));
        user.setUserName(resultSet.getString("USER_NAME"));
        user.setEmail(resultSet.getString("EMAIL"));
        user.setPassword(resultSet.getString("PASSWORD"));
        user.setEnabled(resultSet.getBoolean("ENABLED"));
        return user;
    }
}
