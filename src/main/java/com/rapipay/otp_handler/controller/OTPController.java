package com.rapipay.otp_handler.controller;
import com.rapipay.otp_handler.model.OTP;
import com.rapipay.otp_handler.services.impl.OTPServicesImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

	private static final Logger LOGGER = LoggerFactory.getLogger(OTPController.class);
    @PostMapping("/generateOTP")
	public ApplicationResponseEntity<String> generateOTP(@RequestBody OTP otp) {
		
		try {
			LOGGER.info("Entered generate otp section");
			otpServices.generateOtpService(otp);
			return new ApplicationResponseEntity<>("200","Success",otp.getUserID());
		}
		catch(Exception e){
			LOGGER.error("Error level log message:"+e.toString());
			e.printStackTrace();
			return new ApplicationResponseEntity<>("999",e.toString(),null);
		}		
	}

    @GetMapping("/validateOTP")
	public ApplicationResponseEntity<Boolean> validateOTP(@RequestBody OTP otp) {
		try {
			LOGGER.info("Entered validate otp section");
			boolean isValid=otpServices.validateOTPService(otp);
			return new ApplicationResponseEntity<>("200","Success",isValid);
		}
		catch(Exception e){
			LOGGER.error("Error level log message:"+e.toString());
			return new ApplicationResponseEntity<>("999",e.toString(),null);
		}
	}
}
