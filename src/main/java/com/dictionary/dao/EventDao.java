package com.dictionary.dao;

import com.dictionary.dto.EventDto;
import com.dictionary.mapper.EventMapper;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

@Component
public class EventDao extends BaseDao {

    private static final String sqlFutureEvensBySpecialization = "select * from TBL_EVENT "
            + "where SPECIALIZATION_ID_FK = ? and EVENT_DATE > current_date();";
    private static final String sqlFutureEventsBySpecializationByUser = "select ev.* "
            + "from TBL_EVENT ev JOIN TBL_USER_EVENT us ON ev.EVENT_ID = us.EVENT_ID_FK "
            + "where us.USER_ID_FK = ? and ev.SPECIALIZATION_ID_FK = ? and ev.EVENT_DATE > current_date();";
    private static final String sqlAllEventByUser = "select ev.* " + "from TBL_EVENT ev JOIN TBL_USER_EVENT us ON ev.EVENT_ID = us.EVENT_ID_FK "
            + "where us.USER_ID_FK = ?;";
    private static final String sqlAllEventBySpecializationByUser = "select ev.* from TBL_EVENT ev JOIN TBL_USER_EVENT us ON ev.EVENT_ID = us.EVENT_ID_FK "
            + "where us.USER_ID_FK = ? and ev.SPECIALIZATION_ID_FK = ?;";
    private static final String sqlInsertToEvent = "insert into TBL_USER_EVENT values (null, ?, ?);";

    public List<EventDto> getFutureEventsBySpecialization(int specializationId) {
        return getTemplate().query(sqlFutureEvensBySpecialization, new Object[] { specializationId }, new EventMapper());
    }

    public List<EventDto> getFutureEventsBySpecializationByUser(long userId, int specializationId) {
        return getTemplate().query(sqlFutureEventsBySpecializationByUser, new Object[] { userId, specializationId }, new EventMapper());
    }

    public List<EventDto> getAllEventsByUser(long userId) {
        return getTemplate().query(sqlAllEventByUser, new Object[] { userId }, new EventMapper());
    }

    public List<EventDto> getAllEventsBySpecializationByUser(long userId, long specializationId) {
        return getTemplate().query(sqlAllEventBySpecializationByUser, new Object[] { userId, specializationId }, new EventMapper());
    }

    public long insertToEvent(final long userId, final long eventId) throws MySQLIntegrityConstraintViolationException {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        getTemplate().update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement statement = con.prepareStatement(sqlInsertToEvent, Statement.RETURN_GENERATED_KEYS);
                statement.setLong(1, userId);
                statement.setLong(2, eventId);
                return statement;
            }
        }, keyHolder);
        return keyHolder.getKey().longValue();
    }
}
