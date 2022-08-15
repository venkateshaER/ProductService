package com.hashedin.productService.service;

import java.util.List;

import com.hashedin.productService.dao.Payment;
import com.hashedin.productService.dao.User;
import com.hashedin.productService.dao.UserResponse;
import com.hashedin.productService.dto.PaymentDto;

public interface PaymentServiceInterface {

//	Response createPayment(PaymentDto paymentDto, String token);

	List<Payment> getAllPayments(String token);

	UserResponse createPayment(PaymentDto paymentDto, User user);

}
