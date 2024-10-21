package com.iyzico.challenge.exceptions;

import com.iyzico.challenge.enums.ErrorCode;

public class SeatException
        extends BaseException {

    private static final long serialVersionUID = -8277709196836634389L;

    public SeatException(ErrorCode errorCode) {
        super(errorCode);
    }

}
