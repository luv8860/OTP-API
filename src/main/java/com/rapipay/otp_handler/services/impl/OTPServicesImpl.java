package com.rapipay.otp_handler.services.impl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.rapipay.otp_handler.exception.EmailNotSentException;
import com.rapipay.otp_handler.exception.InvalidChannelName;
import com.rapipay.otp_handler.exception.InvalidEmailIDException;
import com.rapipay.otp_handler.exception.InvalidPhoneNumber;
import com.rapipay.otp_handler.exception.OTPExpiredException;
import com.rapipay.otp_handler.exception.UserNotFoundException;
import com.rapipay.otp_handler.model.OTP;
import com.rapipay.otp_handler.repository.OTPRepository;
// import com.rapipay.otp_handler.services.EmailUtilService;
import com.rapipay.otp_handler.services.OTPServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OTPServicesImpl implements OTPServices {
    @Autowired
    OTPRepository otpRepository;

    public String generateOtpService(OTP otp)
            throws InvalidEmailIDException, EmailNotSentException, InvalidPhoneNumber, InvalidChannelName {
        if (otp.getChannelName().equals("email")) {
            String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(otp.getUserID());
            if (matcher.matches()) {
                otp.setOtp();
                otp.setTimestamp();
                this.otpRepository.save(otp);
                // EmailUtilService eus = new EmailUtilService();
                // eus.sendEmail(otp);
                System.out.println(otp.toString());
                return otp.getUserID();
            } else {
                InvalidEmailIDException e = new InvalidEmailIDException();
                throw e;
            }
        } else if (otp.getChannelName().equals("sms")) {
            String regex = "^[0-9]{10}$";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(otp.getUserID());
            if (matcher.matches()) {
                otp.setOtp();
                otp.setTimestamp();
                // SMS Service
                this.otpRepository.save(otp);
                return otp.getUserID();
            } else {
                InvalidPhoneNumber e = new InvalidPhoneNumber();
                throw e;
            }
        } else {
            InvalidChannelName e = new InvalidChannelName();
            throw e;
        }        
    }

    public boolean validateOTPService(OTP otp) throws InvalidEmailIDException, UserNotFoundException, InvalidPhoneNumber, OTPExpiredException, InvalidChannelName {
        if (otp.getChannelName().equals("email")) {
            String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(otp.getUserID());
            if (matcher.matches()) {
                OTP fetchedOTP = otpRepository.findById(otp.getUserID()).orElseThrow(() -> new UserNotFoundException());
                System.out.println((System.currentTimeMillis() - fetchedOTP.getTimestamp()) / (60000));
                if (fetchedOTP.getOtp().equals(otp.getOtp())) {
                    if ((System.currentTimeMillis() - fetchedOTP.getTimestamp()) / (60000) < 5) {
                        otp.toString();
                        return true;
                    } else {
                        OTPExpiredException e = new OTPExpiredException();
                        throw e;
                    }
                }
                return false;
            } else {
                InvalidEmailIDException e = new InvalidEmailIDException();
                throw e;
            }
        } else if (otp.getChannelName().equals("sms")) {
            return true;
        } else {
            InvalidChannelName e = new InvalidChannelName();
            throw e;
        }
    }
}
