package com.hashedin.productService.dto;

public class PaymentDto {
	
	private String productName;
	private String couponName;
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getCouponName() {
		return couponName;
	}
	public void setCouponName(String couponName) {
		this.couponName = couponName;
	}
	@Override
	public String toString() {
		return "PaymentDto [productName=" + productName + ", couponName=" + couponName + "]";
	}

	
}
