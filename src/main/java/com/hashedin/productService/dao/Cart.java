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
public class Cart implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long cartId;
	private String productName;
	private int quantity;
	private long price;
	private long userId; 
	
	public long getCartId() {
		return cartId;
	}
	public void setCartId(long cartId) {
		this.cartId = cartId;
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
	@Override
	public String toString() {
		return "Cart [cartId=" + cartId + ", productName=" + productName + ", quantity=" + quantity + ", price=" + price
				+ ", userId=" + userId + "]";
	}
	
	
	
	
	

}
