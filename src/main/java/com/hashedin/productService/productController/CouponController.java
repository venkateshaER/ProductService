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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hashedin.productService.dao.Coupon;
import com.hashedin.productService.service.CouponServiceInterface;
import com.hashedin.productService.utility.Response;

@RestController
@RequestMapping("/coupon")
public class CouponController {
	
	@Autowired
	private CouponServiceInterface couponService;
	
	@PostMapping("/createCoupon")
	private ResponseEntity<Response>createCoupon(@RequestBody Coupon coupon){
		Response response=couponService.createCoupon(coupon);
		return new ResponseEntity<Response>(response, HttpStatus.CREATED);
	}
	
	@PutMapping("/updateCouponPrice")
	private ResponseEntity<Response> updateCouponrice(@RequestBody Coupon coupon) {
		
		Response response=couponService.updateCoupon(coupon);
		
		return new ResponseEntity<Response>(response,HttpStatus.OK);
		
	}
	
	@GetMapping("/getAllCoupon")
	private ResponseEntity<List<Coupon>> getAllCoupon() {
		
		List<Coupon> couponList=couponService.getAllCoupon();
		
		return new ResponseEntity<List<Coupon>>(couponList,HttpStatus.OK);
		
	}
	
	@DeleteMapping("/deleteCoupon")
	private ResponseEntity<Response> getAllCoupon(@RequestParam String CouponName) {
		
		Response response=couponService.deleteCoupon(CouponName);
		
		return new ResponseEntity<Response>(response,HttpStatus.OK);
		
	}

}
