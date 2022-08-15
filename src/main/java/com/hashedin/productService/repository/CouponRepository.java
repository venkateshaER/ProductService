package com.hashedin.productService.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.hashedin.productService.dao.Coupon;

public interface CouponRepository extends JpaRepository<Coupon, Long> {
	
	@Query("SELECT u FROM Coupon u WHERE u.couponName = ?1")
	Optional<Coupon>findByCouponName(String productName);
	
	@Query("SELECT u.couponId FROM Coupon u WHERE u.couponName = ?1")
	Long findByIdFromCouponName(String productName);

}
