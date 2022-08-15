package com.hashedin.productService.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
	
	private long userId;
	private String userName;
	private String emailId;
	private String password;
	private String phone;
	private String createdDate;
	private String updatedDate;
	private boolean signIn;
	private long moneyWallet;
	private String token;
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	public String getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(String updatedDate) {
		this.updatedDate = updatedDate;
	}
	public boolean isSignIn() {
		return signIn;
	}
	public void setSignIn(boolean signIn) {
		this.signIn = signIn;
	}
	public long getMoneyWallet() {
		return moneyWallet;
	}
	public void setMoneyWallet(long moneyWallet) {
		this.moneyWallet = moneyWallet;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName + ", emailId=" + emailId + ", password=" + password
				+ ", phone=" + phone + ", createdDate=" + createdDate + ", updatedDate=" + updatedDate + ", signIn="
				+ signIn + ", moneyWallet=" + moneyWallet + ", token=" + token + "]";
	}
	
	
	

}
