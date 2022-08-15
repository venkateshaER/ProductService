package com.hashedin.productService.dao;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Payment implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long paymentId;
	private String productName;
	private int quantity;
	private long price;
	private long userId; 
	private String couponName;
	private long coupon_price;
	private String timeStamp;
	
	public long getPaymentId() {
		return paymentId;
	}
	public void setPaymentId(long paymentId) {
		this.paymentId = paymentId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public long getPrice() {
		return price;
	}
	public void setPrice(long price) {
		this.price = price;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public String getCouponName() {
		return couponName;
	}
	public void setCouponName(String couponName) {
		this.couponName = couponName;
	}
	public long getCoupon_price() {
		return coupon_price;
	}
	public void setCoupon_price(long coupon_price) {
		this.coupon_price = coupon_price;
	}
	public String getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}
	@Override
	public String toString() {
		return "Payment [paymentId=" + paymentId + ", productName=" + productName + ", quantity=" + quantity
				+ ", price=" + price + ", userId=" + userId + ", couponName=" + couponName + ", coupon_price="
				+ coupon_price + ", timeStamp=" + timeStamp + "]";
	}
	
	
	

}
