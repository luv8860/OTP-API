package com.rapipay.otp_handler.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import net.bytebuddy.utility.RandomString;
@Entity
public class OTP {
	
	@Id
	String userID;
	
	@Column(name="otp")
	String otp;
	
	@Column(name="timestamp")	
	long timestamp;

	@Column(name="order_id")
	String orderID;

	@Column(name="channel_name")
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
	public void setOtp(){
		this.otp=RandomString.make(8);//method to create an otp
	}
	@Override
	public String toString() {
		return "OTP [channelName=" + channelName + ", orderID=" + orderID + ", otp=" + otp + ", timestamp=" + timestamp
				+ ", userID=" + userID + "]";
	}
			
}
