package com.hashedin.productService.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hashedin.productService.dao.Products;
import com.hashedin.productService.repository.ProductRepository;
import com.hashedin.productService.utility.Response;
import com.hashedin.productService.utility.ResponseUtil;

@Service
public class ProductService implements ProductServiceInterface{
	
	@Autowired
	private ProductRepository productRepository;

	@Override
	public Response createProduct(Products product) {
		boolean isProduct=productRepository.findByProductName(product.getProductName()).isPresent();
		if(isProduct) {
			Response response=ResponseUtil.getResponse(201, "Product Name Already Exits");
			return response;
		}
		else {
			productRepository.save(product);
			Response response=ResponseUtil.getResponse(201, "Product Created Successfully");
			return response;
		}
	}

	@Override
	public Response updateProductPrice(Products product) {
		boolean isProduct=productRepository.findByProductName(product.getProductName()).isPresent();
		if(isProduct) {
			Products p=productRepository.findByProductName(product.getProductName()).get();
			p.setCategories(product.getCategories());
			p.setRatings(product.getRatings());
			p.setTypes(product.getTypes());
			p.setQuantity(product.getQuantity());
			p.setProduct_price(product.getProduct_price());
			productRepository.save(p);
			Response response=ResponseUtil.getResponse(201, "Product Updated");
			return response;
		}
		else {
			Response response=ResponseUtil.getResponse(204, "Product Not Exits");
			return response;
		}
	}

	@Override
	public List<Products> getAllProducts() {
		return productRepository.findAll();
	}

	@Override
	public Response deleteProduct(String productName) {
		Long id=0l;
		id=productRepository.findByIdFromProductName(productName);
		if(id!=null && id!=0l) {
			productRepository.deleteById(id);
			Response response=ResponseUtil.getResponse(201, "Product Deleted");
			return response;
		}
		else {
			Response response=ResponseUtil.getResponse(204, "Product Not Exits");
			return response;
		}
		
		
	}

	@Override
	public List<Products> getAllProductsByRating(int rating, String order) {
		if(order!=null && !order.equals("") && order.contains("desc")) {
			return productRepository.findByratingsAndDesc(rating);
		}
		else {
			return productRepository.findByratingsAndAsc(rating);
		}
	}

	@Override
	public List<Products> getAllProductsByPrice(Long price, String order) {
		if(order!=null && !order.equals("") && order.contains("desc")) {
			return productRepository.findByPriceAndDesc(price);
		}
		else {
			return productRepository.findByPriceAndAsc(price);
		}
	}

}
