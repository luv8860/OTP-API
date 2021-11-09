package com.rapipay.otp_handler.exception;

public class UserNotFoundException extends Exception{

    @Override
    public String toString() {
        return "User Not Found";
    }
    
}
