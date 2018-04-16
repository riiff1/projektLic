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
    private UserService userService;

    @Autowired
    private PaymentDao paymentDao;

    public List<PaymentDto> getAllPaymentsByUser() {
        return paymentDao.getAllPaymentsByUser(userService.getLoggedUserId());
    }

    public List<PaymentDto> getPaymentsByUserFromToDate(Date from, Date to) {
        return paymentDao.getPaymentsByUserFromToDate(userService.getLoggedUserId(), from, to);
    }
}
