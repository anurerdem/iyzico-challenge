package com.iyzico.challenge.controller;

import com.iyzico.challenge.bank.dto.request.PaymentDTO;
import com.iyzico.challenge.bank.dto.response.PaymentResponse;
import com.iyzico.challenge.bank.service.PaymentService;
import com.iyzico.challenge.exceptions.NotFoundException;
import com.iyzico.challenge.exceptions.PaymentException;
import com.iyzico.challenge.exceptions.SeatException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api/payment", produces = MediaType.APPLICATION_JSON_VALUE)
public class PaymentController {
    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping
    public ResponseEntity<PaymentResponse> buySeat(@RequestBody PaymentDTO request) throws PaymentException, SeatException, NotFoundException {
        return ResponseEntity.ok().body(paymentService.pay(request));
    }


/*
    public static final Gson gson = new Gson();

    @Autowired
    private IyzicoPaymentService paymentService;

    @RequestMapping(path = "/payment",method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PaymentServiceResponse> paymentForSeatPrice(@Valid @RequestBody PaymentServiceRequest paymentServiceRequest){
        String txnId = UUID.randomUUID().toString();

        log.info("{} PaymentController - paymentForSeatPrice() start.", txnId);
        PaymentServiceResponse response=new PaymentServiceResponse();
        response.setTransactionId(txnId);
        try {
            response.setResult(paymentService.paymentForSeat(txnId,paymentServiceRequest));
            log.info("{} PaymentController - paymentForSeatPrice() finish: {}", txnId, gson.toJson(response));

        }  catch(InvalidParameterException ex){
            log.error("{} PaymentController - paymentForSeatPrice() has an error: {}", txnId, ex.toString());
            return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
        } catch(ServiceUnavailableException ex){
            log.error("{} PaymentController - paymentForSeatPrice() has an error: {}", txnId, ex.toString());
            return new ResponseEntity(response, HttpStatus.SERVICE_UNAVAILABLE);
        }catch (Exception e) {
            log.error("{} PaymentController - paymentForSeatPrice() has an error: {}", txnId, e.toString());
            return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return ResponseEntity.ok(response);
    }

 */
}
