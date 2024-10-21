package com.iyzico.challenge.controller;

import com.iyzico.challenge.bank.dto.request.PaymentRequest;
import com.iyzico.challenge.bank.dto.response.PaymentResponse;
import com.iyzico.challenge.bank.service.PaymentService;
import com.iyzico.challenge.exceptions.PaymentException;
import com.iyzico.challenge.exceptions.SeatException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api/v1/payment", produces = MediaType.APPLICATION_JSON_VALUE)
public class PaymentController {
    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping
    public ResponseEntity<PaymentResponse> buySeat(@RequestBody PaymentRequest request) throws PaymentException, SeatException {
        return ResponseEntity.ok().body(paymentService.pay(request));
    }
}
