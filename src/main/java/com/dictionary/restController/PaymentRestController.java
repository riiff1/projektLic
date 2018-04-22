package com.dictionary.restController;



import com.dictionary.dto.PaymentDto;
import com.dictionary.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
public class PaymentRestController {

    @Autowired
    private PaymentService paymentService;

    @RequestMapping(value = "/payment/allPaymentsByUser", method = RequestMethod.GET)
    public List<PaymentDto> getAllPaymentsByUser() {
        return paymentService.getAllPaymentsByUser();
    }

    @RequestMapping(value = "/payment/paymentsByUserFromToDate", method = RequestMethod.GET)
    public List<PaymentDto> getPaymentsByUserFromToDate(@RequestParam("dateFrom") String dateFromString,
                                                        @RequestParam("dateTo") String dateToString) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date dateFrom = null;
        Date dateTo = null;
        try {
            dateFrom = formatter.parse(dateFromString);
            dateTo = formatter.parse(dateToString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return paymentService.getPaymentsByUserFromToDate(dateFrom, dateTo);
    }

    @RequestMapping(value = "/payment/savePayment", method = RequestMethod.POST)
    public void savePayment(@RequestParam("specializationList") List<Long> specializationList) {
        paymentService.createPayment(specializationList);
    }
}
