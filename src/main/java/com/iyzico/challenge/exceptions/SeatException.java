package com.iyzico.challenge.exceptions;

public class SeatException
        extends Exception {
    public SeatException(){
        super("Seat already sold!");
    }

}
