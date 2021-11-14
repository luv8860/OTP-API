package com.rapipay.otp_handler.services.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.rapipay.otp_handler.exception.InvalidChannelName;
import com.rapipay.otp_handler.exception.InvalidEmailIDException;
import com.rapipay.otp_handler.exception.InvalidPhoneNumber;
// import com.rapipay.otp_handler.exception.OTPExpiredException;
import com.rapipay.otp_handler.exception.OTPGenTime;
import com.rapipay.otp_handler.model.OTP;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class OTPServicesImplTest {
    @Autowired
    OTPServicesImpl osl;
    //for generation test
    OTP otpTest = new OTP("a51@b.com", "xyz", "email");
    OTP otpTest2 = new OTP("a51@b.com", "xyz", "email");
    OTP otpTest3 = new OTP("a36", "xyz", "email");
    OTP otpTest4 = new OTP("9846543264", "xyz", "sms");
    OTP otpTest5 = new OTP("982", "xyz", "sms");
    OTP otpTest6 = new OTP("ggjhjhjhj@b.com", "xyz", "XYZ");

    //for validation test
    OTP otpTest7 = new OTP("a52@b.com", "xyz", "email");
    OTP otpTest8 = new OTP("9846543265", "xyz", "sms");



    @Test
    void testGenerateOtpService() {
        try {
            assertEquals(otpTest.getUserID(), osl.generateOtpService(otpTest));
            Assertions.assertThrows(OTPGenTime.class, () -> {
                osl.generateOtpService(otpTest2);
            });
            Assertions.assertThrows(InvalidEmailIDException.class, () -> {
                osl.generateOtpService(otpTest3);
            });
            assertEquals(otpTest4.getUserID(), osl.generateOtpService(otpTest4));
            Assertions.assertThrows(InvalidPhoneNumber.class, () -> {
                osl.generateOtpService(otpTest5);
            });
            Assertions.assertThrows(InvalidChannelName.class, () -> {
                osl.generateOtpService(otpTest6);
            });
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }

    @Test
    void testValidateOTPService() {
        try {
            osl.generateOtpService(otpTest7);
            assertEquals(true, osl.validateOTPService(otpTest7));
            otpTest.setOtp("1234");
            assertEquals(false, osl.validateOTPService(otpTest));
            Assertions.assertThrows(InvalidChannelName.class, () -> {
                osl.validateOTPService(otpTest6);
            });
            osl.generateOtpService(otpTest8);
            assertEquals(true, osl.validateOTPService(otpTest8));
            otpTest4.setOtp("1234");
            assertEquals(false, osl.validateOTPService(otpTest4));
            Assertions.assertThrows(InvalidPhoneNumber.class, () -> {
                osl.validateOTPService(otpTest5);
            });
            Assertions.assertThrows(InvalidEmailIDException.class, () -> {
                osl.validateOTPService(otpTest3);
            });
            Assertions.assertThrows(InvalidChannelName.class, () -> {
                osl.validateOTPService(otpTest6);
            });
            // otpTest7.setTimestamp(otpTest7.getTimestamp()-3600000);
            // Assertions.assertThrows(OTPExpiredException.class, () -> {
            //     osl.validateOTPService(otpTest7);
            // });
            // otpTest8.setTimestamp(otpTest8.getTimestamp()-3600000);
            // Assertions.assertThrows(OTPExpiredException.class, () -> {
            //     osl.validateOTPService(otpTest8);
            // });
        } catch (Exception e) {
            //TODO: handle exception
            e.printStackTrace();
        }
    }
}
