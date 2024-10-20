package com.iyzico.challenge.bank.service;

import com.iyzico.challenge.bank.dto.request.PaymentDTO;
import com.iyzico.challenge.bank.dto.response.PaymentResponse;
import com.iyzico.challenge.bank.repository.PaymentRepository;
import com.iyzico.challenge.exceptions.PaymentException;
import com.iyzico.challenge.exceptions.SeatException;
import com.iyzico.challenge.seat.SeatService;
import com.iyzico.challenge.seat.entity.Seat;
import com.iyzipay.model.Status;
import com.iyzipay.model.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@Transactional
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final IyzicoSandboxPaymentIntegrationService iyzicoSandboxPaymentIntegration;
    private final SeatService seatService;

    public PaymentService(PaymentRepository paymentRepository, SeatService seatService, IyzicoSandboxPaymentIntegrationService iyzicoSandboxPaymentIntegration) {
        this.paymentRepository = paymentRepository;
        this.seatService = seatService;
        this.iyzicoSandboxPaymentIntegration = iyzicoSandboxPaymentIntegration;
    }

    public PaymentResponse pay(PaymentDTO paymentDTO) throws PaymentException {
        List<Seat> seats = new ArrayList<>();
        for (Long id : paymentDTO.getSeatIds()) {
            try {
                Seat seat = seatService.getSeat(id);
                if(Boolean.TRUE.equals(seat.getIsSold())){
                    throw new SeatException();
                }
                seats.add(seat);
            }
            catch (Exception ex) {
                log.error("Selected Seat already sold");
                throw new PaymentException(ex.getMessage() + " Payment failed.");
            }
        }

        Payment iyzicoPaymentResponse = iyzicoSandboxPaymentIntegration.pay(seats);
        PaymentResponse response = new PaymentResponse();
        response.setResult(Status.SUCCESS.getValue().equals(iyzicoPaymentResponse.getStatus()) ? Status.SUCCESS.getValue() : Status.FAILURE.getValue());
        response.setPaymentId(iyzicoPaymentResponse.getPaymentId());
        if(Status.SUCCESS.getValue().equals(iyzicoPaymentResponse.getStatus())){
            save(response, seats);
        }

        return response;
    }

    public void save(PaymentResponse response, List<Seat> seats) {
        seatService.updateSeatsStatus(seats);
        savePayment(response, seats);
        log.info("Payment Id : {} - Payment Result : {} - Payment completed!", response.getPaymentId(), response.getResult());
    }

    public void savePayment(PaymentResponse response, List<Seat> seats) {
        for(Seat seat : seats) {
            com.iyzico.challenge.bank.entity.Payment payment = new com.iyzico.challenge.bank.entity.Payment();
            payment.setPrice(seat.getSeatPrice());
            payment.setSeat(seat);
            payment.setFlight(seat.getFlight());
            payment.setBankResponse(response.getResult());
            paymentRepository.save(payment);
        }
    }
}
