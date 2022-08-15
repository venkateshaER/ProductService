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

import com.hashedin.productService.dao.Products;
import com.hashedin.productService.service.ProductServiceInterface;
import com.hashedin.productService.utility.Response;

@RestController
@RequestMapping("/product")
public class ProductController {
	
	@Autowired
	private ProductServiceInterface productService;
	
	@PostMapping("/createProduct")
	private ResponseEntity<Response> createProduct(@RequestBody Products product) {
		
		Response response=productService.createProduct(product);
		
		return new ResponseEntity<Response>(response,HttpStatus.CREATED);
		
	}
	
	@PutMapping("/updateProductPrice")
	private ResponseEntity<Response> updateProductPrice(@RequestBody Products product) {
		
		Response response=productService.updateProductPrice(product);
		
		return new ResponseEntity<Response>(response,HttpStatus.OK);
		
	}
	
	@GetMapping("/getAllProducts")
	private ResponseEntity<List<Products>> getAllproducts() {
		
		List<Products> productList=productService.getAllProducts();
		
		return new ResponseEntity<List<Products>>(productList,HttpStatus.OK);
		
	}
	
	@DeleteMapping("/deleteProduct")
	private ResponseEntity<Response> getAllproducts(@RequestParam String productName) {
		
		Response response=productService.deleteProduct(productName);
		
		return new ResponseEntity<Response>(response,HttpStatus.OK);
		
	}
	
	@GetMapping("/getAllProductsByRating")
	private ResponseEntity<List<Products>> getAllProductsByRating(@RequestParam int rating, @RequestParam String orderBy) {
		
		List<Products> productList=productService.getAllProductsByRating(rating,orderBy);
		
		return new ResponseEntity<List<Products>>(productList,HttpStatus.OK);
		
	}
	
	@GetMapping("/getAllProductsByPrice")
	private ResponseEntity<List<Products>> getAllProductsByPrice(@RequestParam Long price, @RequestParam String orderBy) {
		
		List<Products> productList=productService.getAllProductsByPrice(price,orderBy);
		
		return new ResponseEntity<List<Products>>(productList,HttpStatus.OK);
		
	}

}
