package com.hashedin.productService.service;

import java.util.List;

import com.hashedin.productService.dao.Products;
import com.hashedin.productService.utility.Response;

public interface ProductServiceInterface {

	Response createProduct(Products product);

	Response updateProductPrice(Products product);

	List<Products> getAllProducts();

	Response deleteProduct(String productName);

	List<Products> getAllProductsByRating(int rating, String order);

	List<Products> getAllProductsByPrice(Long price, String orderBy);

}
