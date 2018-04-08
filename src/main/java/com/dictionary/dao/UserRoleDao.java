package com.dictionary.dao;

import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

@Component
public class UserRoleDao extends BaseDao {

    private static final String sql = "insert into TBL_USER_ROLE values (null, ?, 'ROLE_USER');";

    public int insertUserRoles(final int userId) {


        KeyHolder keyHolder = new GeneratedKeyHolder();
        getTemplate().update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement statement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                statement.setInt(1, userId);
                return statement;
            }
        },keyHolder);
        return keyHolder.getKey().intValue();
    }

    public void deleteUserRoles(int userIdFk) {
        String sql = "DELETE FROM TBL_USER_ROLE WHERE UserId=?";
        getTemplate().update(sql, new Object[] {userIdFk});
    }
}
