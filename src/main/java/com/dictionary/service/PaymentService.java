package com.dictionary.service;

import com.dictionary.dao.PaymentDao;
import com.dictionary.dto.PaymentDto;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class PaymentService {

    @Autowired
    private UserService userService;

    @Autowired
    private PaymentDao paymentDao;

    public List<PaymentDto> getAllPaymentsByUser() {
        return paymentDao.getAllPaymentsByUser(userService.getLoggedUserId());
    }

    public List<PaymentDto> getPaymentsByUserFromToDate(Date from, Date to) {
        return paymentDao.getPaymentsByUserFromToDate(userService.getLoggedUserId(), from, to);
    }

    public void createPayment(List<Long> specializationList) {
        Timestamp createdTime = new Timestamp(System.currentTimeMillis());

        Calendar cal = Calendar.getInstance();
        cal.setTime(createdTime);
        cal.add(Calendar.YEAR, 1);
        Timestamp expireDate = new Timestamp(cal.getTime().getTime());

        float prize = paymentDao.getPrizeSumforSpecialization(specializationList);
        long paymentId = 0;
        try {
            paymentId = paymentDao.insertPayment(userService.getLoggedUserId(), createdTime, prize, null, expireDate);
            paymentDao.insertPaymentSpecializationBatch(paymentId, specializationList);
        } catch (MySQLIntegrityConstraintViolationException e) {
            e.printStackTrace();
        }
    }
}
