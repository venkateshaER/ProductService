package com.hashedin.productService.dto;

public class CartDto {
	
	private String productName;
	private int quantity;
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
	@Override
	public String toString() {
		return "CartDto [productName=" + productName + ", quantity=" + quantity + "]";
	}
	
	

}
