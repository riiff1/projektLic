package com.dictionary.service;

import com.dictionary.dao.PaymentDao;
import com.dictionary.dto.PaymentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PaymentService {

    @Autowired
    private SessionUser sessionUser;
    @Autowired
    private PaymentDao paymentDao;

    public List<PaymentDto> getAllPaymentsByUser() {
        long userId = sessionUser.getUser().getUserId();
        return paymentDao.getAllPaymentsByUser(userId);
    }

    public List<PaymentDto> getPaymentsByUserFromToDate(Date from, Date to) {
        long userId = sessionUser.getUser().getUserId();
        return paymentDao.getPaymentsByUserFromToDate(userId, from, to);
    }
}
