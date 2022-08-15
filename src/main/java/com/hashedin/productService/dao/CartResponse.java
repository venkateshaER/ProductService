package com.hashedin.productService.dao;

import java.util.List;

import com.hashedin.productService.utility.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartResponse {
	
	private Response response;
	private List<Cart> cartList;
	public Response getResponse() {
		return response;
	}
	public void setResponse(Response response) {
		this.response = response;
	}
	public List<Cart> getCartList() {
		return cartList;
	}
	public void setCartList(List<Cart> cartList) {
		this.cartList = cartList;
	}
	@Override
	public String toString() {
		return "CartResponse [response=" + response + ", cartList=" + cartList + "]";
	}

	
}
