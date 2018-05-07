package com.dictionary.dao;

import com.dictionary.dto.UserDto;
import com.dictionary.mapper.UserMapper;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.List;

@Component
public class UserDao extends BaseDao {

    private static final String sqlExistUser = "SELECT EXISTS(SELECT * FROM TBL_USER WHERE USER_NAME = ?)";
    private static final String sqlPassForCurrentUser = "select PASSWORD from TBL_USER where USER_ID = ?;";
    private static final String sqlUpdatePass = "update TBL_USER set PASSWORD = ? where USER_ID = ?;";
    private static final String sqlUpdateEmail = "update TBL_USER set EMAIL = ? where USER_ID = ?;";
    private static final String sqlNickAndEmail = "select * from TBL_USER where USER_ID = ?;";

    @Autowired
    private UserRoleDao userRoleDao;

    public UserDto getActiveUser(String nickName) {
        String sql = "select * from TBL_USER where Enabled = 1 and  USER_NAME= ?;";
        UserDto user = getTemplate().queryForObject(sql, new Object[] {nickName}, new UserMapper());
        return user;
    }

    public int insertUser(final UserDto userDto) throws MySQLIntegrityConstraintViolationException {
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

    public void insertUserAndRoles(UserDto userDto) throws MySQLIntegrityConstraintViolationException {
        int newId = insertUser(userDto);
        userRoleDao.insertUserRoles(newId);
    }

    public boolean existUser(String userName) {
        long booleanNumber = getTemplate().queryForObject(sqlExistUser, new Object[]{userName}, Long.class);
        return booleanNumber == 1 ? true: false;
    }

    public String getPasswordByCurrentUser(long currentUserId) {
        return getTemplate().queryForObject(sqlPassForCurrentUser, new Object[]{currentUserId}, String.class);

    }

    public boolean updatePass(long userId, String newPass) {
        int row = getTemplate().update(sqlUpdatePass, new Object[]{newPass, userId});
        return row == 1 ? true: false;
    }

    public boolean updateEmail(long userId, String newEmail) {
        int row = getTemplate().update(sqlUpdateEmail, new Object[]{newEmail, userId});
        return row == 1 ? true: false;
    }

    public UserDto getNickAndEmail(long userId) {
        UserDto nickAndEmail = getTemplate().queryForObject(sqlNickAndEmail, new Object[] { userId }, new UserMapper());
        nickAndEmail.setPassword(null);
        nickAndEmail.setUserId(0);
        return nickAndEmail;
    }
}
