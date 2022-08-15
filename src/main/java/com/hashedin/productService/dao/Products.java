package com.hashedin.productService.dao;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Products implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long productId;
	private String productName;
	private long product_price;
	private String categories;
	private String types; 
	private int quantity;
	private int ratings;
	
	public long getProductId() {
		return productId;
	}
	public void setProductId(long productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public long getProduct_price() {
		return product_price;
	}
	public void setProduct_price(long product_price) {
		this.product_price = product_price;
	}
	public String getCategories() {
		return categories;
	}
	public void setCategories(String categories) {
		this.categories = categories;
	}
	public String getTypes() {
		return types;
	}
	public void setTypes(String types) {
		this.types = types;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getRatings() {
		return ratings;
	}
	public void setRatings(int ratings) {
		this.ratings = ratings;
	}
	@Override
	public String toString() {
		return "Products [productId=" + productId + ", productName=" + productName + ", product_price=" + product_price
				+ ", categories=" + categories + ", types=" + types + ", quantity=" + quantity + ", ratings=" + ratings
				+ "]";
	}

	
}
