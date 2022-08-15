package com.hashedin.productService.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hashedin.productService.dao.Products;

@Repository
public interface ProductRepository extends JpaRepository<Products, Long>{
	
	@Query("SELECT u FROM Products u WHERE u.productName = ?1")
	Optional<Products>findByProductName(String productName);
	
	@Query("SELECT u.productId FROM Products u WHERE u.productName = ?1")
	Long findByIdFromProductName(String productName);

	@Query("SELECT c FROM Products c where c.ratings> ?1 order by c.ratings desc")
	List<Products>findByratingsAndDesc(int ratings);
	
	@Query("SELECT c FROM Products c where c.ratings> ?1 order by c.ratings ASC")
	List<Products>findByratingsAndAsc(int ratings);
	
	@Query("SELECT c FROM Products c where c.product_price> ?1 order by c.product_price desc")
	List<Products>findByPriceAndDesc(Long price);
	
	@Query("SELECT c FROM Products c where c.product_price> ?1 order by c.product_price ASC")
	List<Products>findByPriceAndAsc(Long price);

}
