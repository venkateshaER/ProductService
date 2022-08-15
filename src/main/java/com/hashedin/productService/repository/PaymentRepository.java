package com.hashedin.productService.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hashedin.productService.dao.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long>{
	
	@Query("SELECT u FROM Payment u WHERE u.userId = ?1")
	List<Payment>findByUserId(Long userId);

}
