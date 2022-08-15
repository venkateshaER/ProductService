package com.hashedin.productService.service;

import java.util.List;

import com.hashedin.productService.dao.Cart;
import com.hashedin.productService.dao.User;
import com.hashedin.productService.dto.CartDto;
import com.hashedin.productService.utility.Response;

public interface CartServiceInterface {

//	Response addToCart(CartDto cartDto, String token);

	List<Cart> getCartDetail(Long userid);

//	Response getCart(String token);

//	Response deleteFrom(String productName, String token);

//	Response updateProductFromCart(CartDto cartDto, String token);

	Response addToCart(CartDto cartDto, String token, User user);

	Response updateProductFromCart(CartDto cartDto, String token, User user);

	Response getCart(User user);

	Response deleteFrom(String productName, User user);

}
