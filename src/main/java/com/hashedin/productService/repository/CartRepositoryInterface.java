package com.hashedin.productService.repository;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hashedin.productService.dao.Cart;
@Repository
public interface CartRepositoryInterface extends JpaRepository<Cart, Long>{
	
	@Query("SELECT u FROM Cart u WHERE u.userId = ?1")
	List<Cart>findByUserId(Long userId);
	
	@Transactional
	@Modifying
	@Query("DELETE FROM Cart c WHERE c.productName= ?1 and c.userId= ?2")
	void deleteRecordByproductNameAndUserId(String productName, Long userId);
	
	@Query("SELECT c FROM Cart c WHERE c.productName= ?1 and c.userId= ?2")
	Optional<Cart> findByproductNameAndUserId(String productName, Long userId);
	

}
