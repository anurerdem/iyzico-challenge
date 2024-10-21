package com.iyzico.challenge.bank.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class BankPaymentRequest {
    private BigDecimal price;
}
