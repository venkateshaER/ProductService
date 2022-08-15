package com.hashedin.productService.productController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.hashedin.productService.dao.Cart;
import com.hashedin.productService.dao.CartResponse;
import com.hashedin.productService.dao.UserResponse;
import com.hashedin.productService.dto.CartDto;
import com.hashedin.productService.service.CartServiceInterface;
import com.hashedin.productService.utility.Response;
import com.hashedin.productService.utility.ResponseUtil;
import com.hashedin.productService.utility.TokenUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
@RequestMapping("/cart")
public class CartController {
	
	@Autowired
	private CartServiceInterface cartService;
	
	@Autowired
	private RestTemplate restTemplate;
	
	public static final String USER_SERVICE="addToCart";
	
	@PostMapping("/addToCart")
	@HystrixCommand(fallbackMethod = "cartResponse")
//	@CircuitBreaker(name=USER_SERVICE, fallbackMethod = "cartResponse")
	public ResponseEntity<CartResponse> addToCart(@RequestBody CartDto cartDto, @RequestHeader String token){
		//add user code url
		UserResponse userResponse=new UserResponse();
		CartResponse cartResponse=new CartResponse();
//		try {
			userResponse=(restTemplate.getForObject("http://USER-SERVICE/user/getUser/"+token, UserResponse.class));
//		}catch (Exception e) {
//			System.out.println(""+e.getMessage());
//			Response response=ResponseUtil.getResponse(200, "Invalid User");
//			return new ResponseEntity<Response>(response,HttpStatus.OK);
//		}
		if(userResponse.getUser()!=null){//(restTemplate.getForObject("http://CITIZEN-SERVICE/citizen/getCitizen/"+id, Citizen.class));
			
		
			Response response=cartService.addToCart(cartDto,token,userResponse.getUser());
			List<Cart> cartList=cartService.getCartDetail(userResponse.getUser().getUserId());
			cartResponse.setResponse(response);
			cartResponse.setCartList(cartList);
			return new ResponseEntity<CartResponse>(cartResponse,HttpStatus.CREATED);
		}
		else {
			Response response=ResponseUtil.getResponse(200, "Invalid User");
			cartResponse.setResponse(response);
			return new ResponseEntity<CartResponse>(cartResponse,HttpStatus.OK);
		}
		
	}
	
	@GetMapping("/getCartDetail")
	public ResponseEntity<List<Cart>> getCartDetail(@RequestHeader String token){
		
		Long userid=0l;
		try {
			userid = TokenUtil.verifyToken(token);
		}catch (Exception e) {
			System.out.println(""+e.getMessage());
		}
			List<Cart> cartList=cartService.getCartDetail(userid);
		return new ResponseEntity<List<Cart>>(cartList,HttpStatus.OK);
	}
	
	public ResponseEntity<CartResponse> cartResponse(@RequestBody CartDto cartDto,@RequestHeader String token){
		CartResponse cartResponse=new CartResponse();
		Response response=ResponseUtil.getResponse(206, "Not able add or update , please try after some time");
		Long userid=0l;
		
		try {
			userid = TokenUtil.verifyToken(token);
			List<Cart>cartList=cartService.getCartDetail(userid);
			cartResponse.setCartList(cartList);
		}catch (Exception e) {
			System.out.println(""+e.getMessage());
			response=ResponseUtil.getResponse(200, "Invalid User");
			cartResponse.setResponse(response);
			return new ResponseEntity<CartResponse>(cartResponse,HttpStatus.OK);
		}
			
			cartResponse.setResponse(response);
			
			return new ResponseEntity<CartResponse>(cartResponse,HttpStatus.OK);
	}
	
	@PutMapping("/updateCart")
	public ResponseEntity<Response> updateCart(@RequestBody CartDto cartDto, @RequestHeader String token){
		//add user code url
		UserResponse userResponse=new UserResponse();
		try {
			userResponse=(restTemplate.getForObject("http://USER-SERVICE/user/getUser/"+token, UserResponse.class));
		}catch (Exception e) {
			System.out.println(""+e.getMessage());
			Response response=ResponseUtil.getResponse(200, "Invalid User");
			return new ResponseEntity<Response>(response,HttpStatus.OK);
		}
		if(userResponse.getUser()!=null){
			Response response=cartService.updateProductFromCart(cartDto,token,userResponse.getUser());
			return new ResponseEntity<Response>(response,HttpStatus.OK);
		}
		else {
			Response response=ResponseUtil.getResponse(200, "Invalid User");
			return new ResponseEntity<Response>(response,HttpStatus.OK);
		}
	}
	
	@DeleteMapping("/deleteFromCart")
	public ResponseEntity<Response> deleteCart(@RequestParam String productName,@RequestHeader String token){
		//add user code url
		UserResponse userResponse=new UserResponse();
		try {
			userResponse=(restTemplate.getForObject("http://USER-SERVICE/user/getUser/"+token, UserResponse.class));
		}catch (Exception e) {
			System.out.println(""+e.getMessage());
			Response response=ResponseUtil.getResponse(200, "Invalid User");
			return new ResponseEntity<Response>(response,HttpStatus.OK);
		}
		if(userResponse.getUser()!=null){
			Response response=cartService.deleteFrom(productName,userResponse.getUser());
			return new ResponseEntity<Response>(response,HttpStatus.OK);
		}
		else {
			Response response=ResponseUtil.getResponse(200, "Invalid User");
			return new ResponseEntity<Response>(response,HttpStatus.OK);
		}
	}
	
	
	
	
	
	
}
