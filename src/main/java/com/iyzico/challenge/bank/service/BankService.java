package com.iyzico.challenge.bank.service;

import com.iyzico.challenge.bank.dto.request.BankPaymentRequest;
import com.iyzico.challenge.bank.dto.response.BankPaymentResponse;
import org.springframework.stereotype.Service;

@Service
public class BankService {

    /**
     * Bank Latency Simulation (avg: 5 seconds)
     */
    public BankPaymentResponse pay(BankPaymentRequest request) {
        try {
            Thread.sleep(5000);
            return new BankPaymentResponse("200");
        } catch (InterruptedException e) {
            e.printStackTrace();
            return null;
        }
    }
}
