package com.hashedin.productService.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hashedin.productService.dao.Coupon;
import com.hashedin.productService.repository.CouponRepository;
import com.hashedin.productService.utility.Response;
import com.hashedin.productService.utility.ResponseUtil;

@Service
public class CouponService implements CouponServiceInterface{
	
	@Autowired
	private CouponRepository couponRepository;

	@Override
	public Response createCoupon(Coupon coupon) {
		boolean isCoupon=couponRepository.findByCouponName(coupon.getCouponName()).isPresent();
		if(isCoupon) {
			Response response=ResponseUtil.getResponse(201, "Coupon Name Already Exits");
			return response;
		}
		else {
			couponRepository.save(coupon);
			Response response=ResponseUtil.getResponse(201, "Coupon Created Successfully");
			return response;
		}
	}

	@Override
	public Response updateCoupon(Coupon coupon) {
		boolean isCoupon=couponRepository.findByCouponName(coupon.getCouponName()).isPresent();
		if(isCoupon) {
			Coupon c=couponRepository.findByCouponName(coupon.getCouponName()).get();
			c.setCoupon_price(coupon.getCoupon_price());
			couponRepository.save(c);
			Response response=ResponseUtil.getResponse(201, "Coupon Updated");
			return response;
		}
		else {
			Response response=ResponseUtil.getResponse(204, "Coupon Not Exits");
			return response;
		}
	}

	@Override
	public List<Coupon> getAllCoupon() {
		return couponRepository.findAll();
	}

	@Override
	public Response deleteCoupon(String couponName) {
		Long id=0l;
		id=couponRepository.findByIdFromCouponName(couponName);
		if(id!=null && id!=0l) {
			couponRepository.deleteById(id);
			Response response=ResponseUtil.getResponse(201, "Coupon Deleted");
			return response;
		}
		else {
			Response response=ResponseUtil.getResponse(204, "Coupon Not Exits");
			return response;
		}
		
		
	}

}
