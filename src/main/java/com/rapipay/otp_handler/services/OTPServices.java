package com.rapipay.otp_handler.services;

import com.rapipay.otp_handler.exception.EmailNotSentException;
import com.rapipay.otp_handler.exception.InvalidChannelName;
import com.rapipay.otp_handler.exception.InvalidEmailIDException;
import com.rapipay.otp_handler.exception.InvalidPhoneNumber;
import com.rapipay.otp_handler.exception.OTPExpiredException;
import com.rapipay.otp_handler.exception.UserNotFoundException;
import com.rapipay.otp_handler.model.OTP;

public interface OTPServices {
    public String generateOtpService(OTP otp) throws InvalidEmailIDException, EmailNotSentException ,InvalidPhoneNumber,InvalidChannelName;//Function to generate OTP
    public boolean validateOTPService(OTP otp) throws InvalidEmailIDException, UserNotFoundException,InvalidPhoneNumber,InvalidChannelName,OTPExpiredException;//Function to validate OTP
}
