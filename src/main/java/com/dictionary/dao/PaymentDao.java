package com.dictionary.dao;

import com.dictionary.dto.PaymentDto;
import com.dictionary.dto.SpecializationDto;
import com.dictionary.mapper.PaymentMapper;
import com.dictionary.mapper.SpecializationMapper;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import com.sun.deploy.util.StringUtils;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class PaymentDao extends BaseDao{

    private static final String sqlGetAllPaymentsByUser = "select * from TBL_PAYMENT where USER_ID_FK = ? order by CREATION_TIME desc;";
    private static final String sqlPaymentsByUserFromToDate = "select * from TBL_PAYMENT "
            + "where USER_ID_FK = ? and (DATE (CREATION_TIME)between ? and ?);";
    private static final String sqlInsertPayment = "insert into TBL_PAYMENT values (null, ?, ?, ?, ?, ?);";
    private static final String sqlPrizeSum = "select sum(PRIZE) as prize from TBL_SPECIALIZATION where SPECIALIZATION_ID in (%s) limit 1;";
    private static final String sqlInsertPaymentSpecialization = "insert into TBL_PAYMENT_SPECIALIZATION values (null, ?, ?);";
    private static final String sqlPaymentDetails = "select sp.* "
            + "from TBL_PAYMENT_SPECIALIZATION paysp JOIN TBL_SPECIALIZATION sp ON paysp.SPECIALIZATION_ID_FK = sp.SPECIALIZATION_ID "
            + "where paysp.PAYMENT_ID_FK = ?;";

    public List<PaymentDto> getAllPaymentsByUser(long userId) {
        return getTemplate().query(sqlGetAllPaymentsByUser, new Object[]{userId}, new PaymentMapper());
    }

    public List<PaymentDto> getPaymentsByUserFromToDate(long userId, Date from, Date to) {
        return getTemplate().query(sqlPaymentsByUserFromToDate, new Object[]{userId, from, to}, new PaymentMapper());
    }

    public float getPrizeSumforSpecialization(List<Long> specializationList) {
        String specIds = specializationList.stream().map(s -> String.valueOf(s)).collect(Collectors.joining(","));
        return getTemplate().queryForObject(String.format(sqlPrizeSum, specIds), Float.class);
    }

    public void insertPaymentSpecializationBatch(long paymentId, List<Long> specializations) {
        getTemplate().batchUpdate(sqlInsertPaymentSpecialization, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement, int i) throws SQLException {
                long specializationId = specializations.get(i);
                preparedStatement.setLong(1, paymentId);
                preparedStatement.setLong(2, specializationId);
            }

            @Override
            public int getBatchSize() {
                return specializations.size();
            }
        });
    }

    public long insertPayment(final long userId, final Timestamp createdTimeStamp, final float prize, final Integer discount, final Timestamp expireDate) throws MySQLIntegrityConstraintViolationException {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        getTemplate().update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement statement = con.prepareStatement(sqlInsertPayment, Statement.RETURN_GENERATED_KEYS);
                statement.setLong(1, userId);
                statement.setTimestamp(2, createdTimeStamp);
                statement.setFloat(3, prize);
                statement.setObject(4, discount);
                statement.setTimestamp(5, expireDate);
                return statement;
            }
        },keyHolder);
        return keyHolder.getKey().longValue();
    }

    public List<SpecializationDto> getPaymentDetails(long paymentId) {
        return getTemplate().query(sqlPaymentDetails, new Object[]{paymentId}, new SpecializationMapper());
    }
}
