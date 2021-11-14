package com.rapipay.otp_handler.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document("OTP")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OTP {
	
	@Id
	String userID;
	String otp;
	long timestamp;
	String orderID;
	String channelName;

	public OTP() {
		super();
	}
	public OTP(String userID,  String orderID, String channelName) {
		this.userID = userID;
		this.orderID = orderID;
		this.channelName = channelName;
	}
	public OTP(String userID, String otp, String orderID, String channelName) {
		this.userID = userID;
		this.otp = otp;
		this.orderID = orderID;
		this.channelName = channelName;
	}

	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getOtp() {
		return otp;
	}
	public long getTimestamp() {
		return timestamp;
	}
	public String getOrderID() {
		return orderID;
	}
	public void setOrderID(String orderID) {
		this.orderID = orderID;
	}
	public String getChannelName() {
		return channelName;
	}
	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}
	public void setTimestamp() {
		this.timestamp = System.currentTimeMillis();
	}
	public void setTimestamp(long a) {
		this.timestamp = a;
	}
	public void setOtp(String otp){
		this.otp=otp;//method to create an otp
	}
	@Override
	public String toString() {
		return "OTP [channelName=" + channelName + ", orderID=" + orderID + ", otp=" + otp + ", timestamp=" + timestamp
				+ ", userID=" + userID + "]";
	}
			
}
