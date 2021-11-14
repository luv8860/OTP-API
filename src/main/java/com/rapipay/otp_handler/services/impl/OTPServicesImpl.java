package com.rapipay.otp_handler.services.impl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


import com.rapipay.otp_handler.exception.EmailNotSentException;
import com.rapipay.otp_handler.exception.InvalidChannelName;
import com.rapipay.otp_handler.exception.InvalidEmailIDException;
import com.rapipay.otp_handler.exception.InvalidPhoneNumber;
import com.rapipay.otp_handler.exception.OTPExpiredException;
import com.rapipay.otp_handler.exception.OTPGenTime;
import com.rapipay.otp_handler.exception.UserNotFoundException;
import com.rapipay.otp_handler.model.OTP;
import com.rapipay.otp_handler.repository.OTPRepository;
import com.rapipay.otp_handler.services.OTPServices;
import com.rapipay.otp_handler.util.OTPGenerationUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class OTPServicesImpl implements OTPServices {
    @Autowired
    OTPRepository otpRepository;
    OTPGenerationUtil otpgen=new OTPGenerationUtil();
    @Value("${app.generate}")
    public int x;
    @Value("${app.validate}")
    public int y;
    private static final Logger LOGGER = LoggerFactory.getLogger(OTPServicesImpl.class);
    public String generateOtpService(OTP otp)
            throws InvalidEmailIDException, EmailNotSentException, InvalidPhoneNumber, InvalidChannelName, UserNotFoundException,OTPGenTime,Exception {
                OTP fetchedOTP = otpRepository.findById(otp.getUserID()).orElse(null);
                System.out.println(x+"bla 2bla");
                if(fetchedOTP!=null){
                    if(((System.currentTimeMillis() - fetchedOTP.getTimestamp()) / (60000))<x){
                        OTPGenTime e=new OTPGenTime();
                        throw e;
                    }
                }
        if (otp.getChannelName().equals("email")) {
            String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(otp.getUserID());
            if (matcher.matches()) {
                otp.setOtp(String.valueOf(otpgen.otpValue()));
                otp.setTimestamp();
                this.otpRepository.save(otp);
                System.out.println(otp.toString());
                LOGGER.info("Succesfully generated otp and sent");
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
                otp.setOtp(String.valueOf(otpgen.otpValue()));
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

    public boolean validateOTPService(OTP otp) throws InvalidEmailIDException, UserNotFoundException, InvalidPhoneNumber, OTPExpiredException, InvalidChannelName,Exception {
        if (otp.getChannelName().equals("email")) {
            String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(otp.getUserID());
            if (matcher.matches()) {
                OTP fetchedOTP = otpRepository.findById(otp.getUserID()).orElseThrow(() -> new UserNotFoundException());
                System.out.println((System.currentTimeMillis() - fetchedOTP.getTimestamp()) / (60000));
                if (fetchedOTP.getOtp().equals(otp.getOtp())) {
                    if ((System.currentTimeMillis() - fetchedOTP.getTimestamp()) / (60000) <y) {
                        LOGGER.info("Succesfully validated");
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
            String regex = "^[0-9]{10}$";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(otp.getUserID());
            if (matcher.matches()) {
                OTP fetchedOTP = otpRepository.findById(otp.getUserID()).orElseThrow(() -> new UserNotFoundException());
                System.out.println((System.currentTimeMillis() - fetchedOTP.getTimestamp()) / (60000));
                if (fetchedOTP.getOtp().equals(otp.getOtp())) {
                    if ((System.currentTimeMillis() - fetchedOTP.getTimestamp()) / (60000) <y) {
                        LOGGER.info("Succesfully validated");
                        otp.toString();
                        return true;
                    } else {
                        OTPExpiredException e = new OTPExpiredException();
                        throw e;
                    }
                }
                return false;
            } else {
                InvalidPhoneNumber e = new InvalidPhoneNumber();
                throw e;
            }
        } else {
            InvalidChannelName e = new InvalidChannelName();
            throw e;
        }
    }
}
