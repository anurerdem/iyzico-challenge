package com.iyzico.challenge.service;

import com.iyzico.challenge.bank.dto.request.PaymentRequest;
import com.iyzico.challenge.bank.dto.response.PaymentResponse;
import com.iyzico.challenge.bank.repository.PaymentRepository;
import com.iyzico.challenge.bank.service.IyzicoSandboxPaymentService;
import com.iyzico.challenge.bank.service.PaymentService;
import com.iyzico.challenge.entity.Seat;
import com.iyzico.challenge.seat.SeatService;
import com.iyzipay.model.Payment;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PaymentServiceTest {

    @InjectMocks
    private PaymentService paymentService;

    @Mock
    private PaymentRepository paymentRepository;
    @Mock
    private IyzicoSandboxPaymentService iyzicoSandboxPaymentIntegration;
    @Mock
    private SeatService seatService;

    @Test
    public void pay_WhenSendExistRequest_ThenReturnPayment() {
        PaymentRequest paymentRequest = new PaymentRequest();
        List<Long> seatIds = new ArrayList<>();
        seatIds.add(1L);
        paymentRequest.setSeatIds(seatIds);
        List<Seat> seats = new ArrayList<>();
        Seat seat = new Seat();
        seat.setId(1L);
        seats.add(seat);
        when(seatService.getSeat(1L)).thenReturn(seat);
        Payment iyzicoPaymentResponse = new Payment();
        iyzicoPaymentResponse.setStatus("success");
        iyzicoPaymentResponse.setPaymentId("123456");
        when(iyzicoSandboxPaymentIntegration.pay(seats)).thenReturn(iyzicoPaymentResponse);

        PaymentResponse actual = paymentService.pay(paymentRequest);
        PaymentResponse expected = new PaymentResponse();
        expected.setPaymentId("123456");

        assertEquals(expected.getPaymentId(), actual.getPaymentId());
    }
}
