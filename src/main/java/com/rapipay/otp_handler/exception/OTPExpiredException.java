package com.rapipay.otp_handler.exception;

public class OTPExpiredException extends Exception{

    @Override
    public String toString() {
        return "OTP Expired";
    }
    
}