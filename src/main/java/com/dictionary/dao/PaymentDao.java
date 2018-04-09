package com.dictionary.dao;

import com.dictionary.dto.PaymentDto;
import com.dictionary.mapper.PaymentMapper;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class PaymentDao extends BaseDao{

    private static final String sqlGetAllPaymentsByUser = "select * from TBL_PAYMENT where USER_ID_FK = ?;";
    private static final String sqlPaymentsByUserFromToDate = "select * from TBL_PAYMENT "
            + "where USER_ID_FK = ? and (DATE (CREATION_TIME)between ? and ?);";

    public List<PaymentDto> getAllPaymentsByUser(long userId) {
        return getTemplate().query(sqlGetAllPaymentsByUser, new Object[]{userId}, new PaymentMapper());
    }

    public List<PaymentDto> getPaymentsByUserFromToDate(long userId, Date from, Date to) {
        return getTemplate().query(sqlPaymentsByUserFromToDate, new Object[]{userId, from, to}, new PaymentMapper());
    }
}
