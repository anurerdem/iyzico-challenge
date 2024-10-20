package com.iyzico.challenge.exceptions;


public class NotFoundException extends Exception {
    public NotFoundException(){
        super("record not found");
    }
}