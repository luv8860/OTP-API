package com.rapipay.otp_handler.repository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


import com.rapipay.otp_handler.model.OTP;

@Repository
public interface OTPRepository extends  MongoRepository<OTP,String>{

}
