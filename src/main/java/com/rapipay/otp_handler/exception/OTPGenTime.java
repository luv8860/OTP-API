package com.rapipay.otp_handler.exception;

public class OTPGenTime extends Exception{
    
    @Override
    public String toString() {
        return "Please Request After 2 Minutes";
    }
}
