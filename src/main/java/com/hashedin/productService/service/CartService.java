package com.hashedin.productService.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hashedin.productService.dao.Cart;
import com.hashedin.productService.dao.Products;
import com.hashedin.productService.dao.User;
import com.hashedin.productService.dto.CartDto;
import com.hashedin.productService.repository.CartRepositoryInterface;
import com.hashedin.productService.repository.ProductRepository;
import com.hashedin.productService.utility.Response;
import com.hashedin.productService.utility.ResponseUtil;
import com.hashedin.productService.utility.TokenUtil;

@Service
public class CartService implements CartServiceInterface{
	
	@Autowired
	private CartRepositoryInterface cartRepository;

//	@Autowired
//	private UserRepositoryInterface userRepository;
	
	@Autowired
	public ProductRepository productRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public Response addToCart(CartDto cartDto, String token,User user) {
		Long userid = TokenUtil.verifyToken(token);
//		boolean isUser=userRepository.findById(userid).isPresent();
//		if(isUser) {
			//User user=userRepository.findById(userid).get();
			if(!user.isSignIn()) {
				Response reponse=ResponseUtil.getResponse(204, "Please SignIn");
				return reponse;
			}
			else {
				Cart cart=modelMapper.map(cartDto,Cart.class);
				
				boolean isProduct=cartRepository.findByproductNameAndUserId(cartDto.getProductName(), userid).isPresent();
				if(!isProduct) {
					Products product=productRepository.findByProductName(cartDto.getProductName()).get();
					Long price=product.getProduct_price()*cartDto.getQuantity();
					cart.setProductName(cartDto.getProductName());
					cart.setPrice(price);
					cart.setUserId(userid);
					cart.setQuantity(cartDto.getQuantity());
					cartRepository.save(cart);
					Response reponse=ResponseUtil.getResponse(201, "Added to cart");
					return reponse;
				}
				else {
					Response reponse=ResponseUtil.getResponse(202, "Product Already exits please update");
					return reponse;
				}
			}
//		}
//		else {
//			Response reponse=ResponseUtil.getResponse(203, "User Not Exits");
//			return reponse;
//			
//		}
		
	}
	
	@Override
	public Response updateProductFromCart(CartDto cartDto, String token,User user) {
		//Long userid = TokenUtil.verifyToken(token);
//		boolean isUser=userRepository.findById(userid).isPresent();
//		if(isUser) {
//			User user=userRepository.findById(userid).get();
			if(!user.isSignIn()) {
				Response reponse=ResponseUtil.getResponse(204, "Please SignIn");
				return reponse;
			}
			else {
				
				boolean isProduct=cartRepository.findByproductNameAndUserId(cartDto.getProductName(), user.getUserId()).isPresent();
				if(isProduct) {
					Products product=productRepository.findByProductName(cartDto.getProductName()).get();
					Cart cartDetail=cartRepository.findByproductNameAndUserId(cartDto.getProductName(), user.getUserId()).get();
					Long price=product.getProduct_price()*cartDto.getQuantity();
					cartDetail.setProductName(cartDto.getProductName());
					cartDetail.setPrice(price);
					cartDetail.setUserId(user.getUserId());
					cartDetail.setQuantity(cartDto.getQuantity());
					cartRepository.save(cartDetail);
					Response reponse=ResponseUtil.getResponse(201, "Product updated to cart");
					return reponse;
				}
				else {
					Response reponse=ResponseUtil.getResponse(202, "Product Not exits, please add to cart");
					return reponse;
				}
			}
//		}
//		else {
//			Response reponse=ResponseUtil.getResponse(203, "User Not Exits");
//			return reponse;
//			
//		}
		
	}

	@Override
	public List<Cart> getCartDetail(Long userid) {
		
		return cartRepository.findByUserId(userid);
	}

	@Override
	public Response getCart(User user) {
//		Long userid = TokenUtil.verifyToken(token);
//		boolean isUser=userRepository.findById(userid).isPresent();
//		if(isUser) {
//			User user=userRepository.findById(userid).get();
			if(!user.isSignIn()) {
				Response reponse=ResponseUtil.getResponse(204, "Please SignIn");
				return reponse;
			}
			else {
				Response reponse=ResponseUtil.getResponse(201, "Server down");
				return reponse;
			}
//		}
//		else {
//			Response reponse=ResponseUtil.getResponse(203, "User Not Exits");
//			return reponse;
//		}

	}

	@Override
	public Response deleteFrom(String productName,User user) {
//		Long userid = TokenUtil.verifyToken(token);
//		boolean isUser=userRepository.findById(userid).isPresent();
//		if(isUser) {
//			User user=userRepository.findById(userid).get();
			if(!user.isSignIn()) {
				Response reponse=ResponseUtil.getResponse(204, "Please SignIn");
				return reponse;
			}
			else {
				boolean isProduct=cartRepository.findByproductNameAndUserId(productName, user.getUserId()).isPresent();
				if(isProduct) {
					cartRepository.deleteRecordByproductNameAndUserId(productName, user.getUserId());
					Response reponse=ResponseUtil.getResponse(201, "Product Deleted");
					return reponse;
				}
				else {
					Response reponse=ResponseUtil.getResponse(202, "Product Not exits, please add to cart");
					return reponse;
				}
				
			}
			
		
//		}
//		else {
//			Response reponse=ResponseUtil.getResponse(203, "User Not Exits");
//			return reponse;
//			
//		}
	}
}
