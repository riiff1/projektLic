package com.dictionary.dao;

import com.dictionary.dto.UserDto;
import com.dictionary.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.sql.*;

@Component
public class UserDao extends BaseDao {

    @Autowired
    private UserRoleDao userRoleDao;

    public UserDto getActiveUser(String nickName) {
        String sql = "select * from TBL_USER where Enabled = 1 and  USER_NAME= ?;";
        UserDto user = getTemplate().queryForObject(sql, new Object[] {nickName}, new UserMapper());
        return user;
    }

    public int insertUser(final UserDto userDto) {
        final String sql = "insert into TBL_USER values (null, ?, ?, ?, ?);";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        getTemplate().update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement statement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                statement.setString(1, userDto.getUserName());
                statement.setString(2, userDto.getEmail());
                statement.setString(3, userDto.getPassword());
                statement.setBoolean(4, userDto.isEnabled());
                return statement;
            }
        },keyHolder);
        return keyHolder.getKey().intValue();
    }

    public void deleteUser(int id) {
        String sql = "DELETE FROM TBL_USER WHERE USER_ID=?";
        getTemplate().update(sql, new Object[] {id});
    }


    public void updateTrade(int id, UserDto userDto) {
        String sql = "update TBL_USER set LastName = ?, FirstName = ?, NickName = ?, Password = ? WHERE UserId = ?;";
        Object[] params = new Object[]{userDto.getUserName(), userDto.getEmail(), userDto.getPassword(), userDto.getUserId()};
        int[] types = new int[]{Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR};
        getTemplate().update(sql, params, types);
    }

    public void insertUserAndRoles(UserDto userDto) {
        int newId = insertUser(userDto);
        userRoleDao.insertUserRoles(newId);
    }
}