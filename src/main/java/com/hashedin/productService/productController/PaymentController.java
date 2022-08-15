package com.hashedin.productService.productController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.hashedin.productService.dao.Payment;
import com.hashedin.productService.dao.User;
import com.hashedin.productService.dao.UserResponse;
import com.hashedin.productService.dto.PaymentDto;
import com.hashedin.productService.service.PaymentServiceInterface;
import com.hashedin.productService.utility.Response;
import com.hashedin.productService.utility.ResponseUtil;

@RestController
@RequestMapping("/payment")
public class PaymentController {
	
	@Autowired
	private PaymentServiceInterface paymentService;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@PostMapping("/makePayment")
	public ResponseEntity<UserResponse> createPayment(@RequestBody PaymentDto paymentDto, @RequestHeader String token){
		//add user code url
		UserResponse userResponse=new UserResponse();
		try {
			userResponse=(restTemplate.getForObject("http://USER-SERVICE/user/getUser/"+token, UserResponse.class));
		}catch (Exception e) {
			System.out.println(""+e.getMessage());
			Response response=ResponseUtil.getResponse(200, "Invalid User");
			userResponse.setResponse(response);
			return new ResponseEntity<UserResponse>(userResponse,HttpStatus.OK);
		}
		if(userResponse.getUser()!=null){
		
			UserResponse paymentResponse=paymentService.createPayment(paymentDto,userResponse.getUser());
			///updatePrice/{price}/{token}
			if(paymentResponse.getUser()!=null) {
				User user=(restTemplate.getForObject("http://USER-SERVICE/user/updatePrice/"+paymentResponse.getUser().getMoneyWallet()+"/"+paymentResponse.getUser().getUserId(), User.class));
			}
			return new ResponseEntity<UserResponse>(paymentResponse,HttpStatus.CREATED);
		}
		else {
			Response response=ResponseUtil.getResponse(200, "Invalid User");
			userResponse.setResponse(response);
			return new ResponseEntity<UserResponse>(userResponse,HttpStatus.OK);
		}
	}
	
	@GetMapping("/paymentHistroy")
	public List<Payment> paymentHistory(@RequestHeader String token){
		List<Payment> paymentList=paymentService.getAllPayments(token);
		return paymentList;
	}

}
