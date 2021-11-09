package com.rapipay.otp_handler.exception;

public class EmailNotSentException extends Exception{

    @Override
    public String toString() {
        return "Something went Wrong while Sending Email";
    }
    
}
