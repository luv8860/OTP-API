package com.rapipay.otp_handler.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.rapipay.otp_handler.model.OTP;

@Repository
public interface OTPRepository extends JpaRepository<OTP,String>{

}
