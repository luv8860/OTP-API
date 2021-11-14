package com.rapipay.otp_handler.util;

import java.util.Random;

public class OTPGenerationUtil {
    
    public int otpValue(){
        Random rand=new Random();
        return rand.nextInt(8999)+1000;
    }
}
