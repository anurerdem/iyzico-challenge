package com.iyzico.challenge.bank.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PaymentResponse {
    private String id;
    private String paymentId;
    private String status;
    private String message;
    private String error;
    private String result;
    private String error_description;
    private String error_url;
    private String error_type;
    private String error_cause;
    private String error_location;
    private String error_timestamp;
    private String error_source;
    private String error_code;
    private String error_message;
    private String error_location_url;
    private String error_timestamp_url;
    private String error_source_url;
    private String error_code_url;
    private String error_message_url;
    private String error_location_url_url;
    private String error_timestamp_url_url;
    private String error_source_url_url;
    private String error_code_url_url;
    private String error_message_url_url;

}
