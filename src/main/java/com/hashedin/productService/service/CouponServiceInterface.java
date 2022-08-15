package com.hashedin.productService.service;

import java.util.List;

import com.hashedin.productService.dao.Coupon;
import com.hashedin.productService.utility.Response;

public interface CouponServiceInterface {

	Response createCoupon(Coupon coupon);

	Response updateCoupon(Coupon coupon);

	List<Coupon> getAllCoupon();

	Response deleteCoupon(String couponName);

}
