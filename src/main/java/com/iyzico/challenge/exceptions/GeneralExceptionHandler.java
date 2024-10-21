package com.iyzico.challenge.exceptions;

import com.iyzico.challenge.enums.ErrorCode;
import org.h2.jdbc.JdbcSQLIntegrityConstraintViolationException;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

@ControllerAdvice
@RestControllerAdvice
public class GeneralExceptionHandler extends ResponseEntityExceptionHandler {

    private final MessageSource messageSource;

    public GeneralExceptionHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ExceptionHandler({BaseException.class})
    public ResponseEntity<?> handleBaseException(HttpServletRequest req, BaseException ex) {

        String message = messageSource.getMessage(ex.getErrorCode().getCode(), null, req.getLocale());

        return ResponseEntity.status(ErrorCode.SEAT_OPERATION_FAILED.getHttpStatus()).body(message);
    }

    @ExceptionHandler({SQLException.class})
    public ResponseEntity<?> handleSQLException(HttpServletRequest req, SQLException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("");
    }
}
