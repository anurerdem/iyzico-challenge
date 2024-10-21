package com.iyzico.challenge.bank.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BankPaymentResponse {
    private String resultCode;

    public BankPaymentResponse(String resultCode) {
        this.resultCode = resultCode;
    }
}


