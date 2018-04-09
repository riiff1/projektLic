package com.dictionary.mapper;

import com.dictionary.dto.PaymentDto;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PaymentMapper implements RowMapper<PaymentDto> {
    @Override
    public PaymentDto mapRow(ResultSet resultSet, int i) throws SQLException {
        PaymentDto paymentDto = new PaymentDto();
        paymentDto.setPaymentId(resultSet.getInt("PAYMENT_ID"));
        paymentDto.setUserId(resultSet.getInt("USER_ID_FK"));
        paymentDto.setCreationTime(resultSet.getTimestamp("CREATION_TIME"));
        paymentDto.setPrize(resultSet.getFloat("PRIZE"));
        paymentDto.setDiscount(resultSet.getInt("DISCOUNT"));
        paymentDto.setExpireDate(resultSet.getDate("EXPIRE_DATE"));
        return paymentDto;
    }
}
