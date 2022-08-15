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
public class Coupon implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long couponId;
	private String couponName;
	private long coupon_price;
	public long getCouponId() {
		return couponId;
	}
	public void setCouponId(long couponId) {
		this.couponId = couponId;
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
	@Override
	public String toString() {
		return "Coupon [couponId=" + couponId + ", couponName=" + couponName + ", coupon_price=" + coupon_price + "]";
	}
	
	

}
