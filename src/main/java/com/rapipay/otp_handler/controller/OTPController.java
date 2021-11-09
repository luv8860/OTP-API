package com.rapipay.otp_handler.controller;
import com.rapipay.otp_handler.model.OTP;
import com.rapipay.otp_handler.services.impl.OTPServicesImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class OTPController {
    @Autowired
	private OTPServicesImpl otpServices;
    @PostMapping("/generateOTP")
	public ApplicationResponseEntity<String> generateOTP(@RequestBody OTP otp) {
		
		try {
			otpServices.generateOtpService(otp);
			return new ApplicationResponseEntity<>("200","Success",otp.getUserID());
		}
		catch(Exception e){
			return new ApplicationResponseEntity<>("999",e.toString(),null);
		}		
	}

    @GetMapping("/validateOTP")
	public ApplicationResponseEntity<Boolean> validateOTP(@RequestBody OTP otp) {
		try {
			boolean isValid=otpServices.validateOTPService(otp);
			return new ApplicationResponseEntity<>("200","Success",isValid);
		}
		catch(Exception e){
			return new ApplicationResponseEntity<>("999",e.toString(),null);
		}

	}
}
