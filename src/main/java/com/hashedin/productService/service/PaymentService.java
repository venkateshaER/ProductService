package com.hashedin.productService.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.hashedin.productService.dao.Cart;
import com.hashedin.productService.dao.Coupon;
import com.hashedin.productService.dao.Payment;
import com.hashedin.productService.dao.Products;
import com.hashedin.productService.dao.User;
import com.hashedin.productService.dao.UserResponse;
import com.hashedin.productService.dto.PaymentDto;
import com.hashedin.productService.repository.CartRepositoryInterface;
import com.hashedin.productService.repository.CouponRepository;
import com.hashedin.productService.repository.PaymentRepository;
import com.hashedin.productService.repository.ProductRepository;
import com.hashedin.productService.utility.Response;
import com.hashedin.productService.utility.ResponseUtil;
import com.hashedin.productService.utility.TokenUtil;
import com.hashedin.productService.utility.Utility;


@Service
public class PaymentService implements PaymentServiceInterface{
	
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private CouponRepository couponRepository;
	@Autowired
	private CartRepositoryInterface cartRepository;
	
//	@Autowired
//	private UserRepositoryInterface userRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private PaymentRepository paymentRepository;
	
	@Value("${product.discount}")
	private String productDiscount;
	
	@Override
	public UserResponse createPayment(PaymentDto paymentDto,User user) {
		Long disCount=Long.valueOf(productDiscount);
		UserResponse userResponse=new UserResponse();
		//Long userid = TokenUtil.verifyToken(token);
//		boolean isUser=userRepository.findById(userid).isPresent();
//		if(isUser) {
			//User user=userRepository.findById(userid).get();
			if(!user.isSignIn()) {
				Response reponse=ResponseUtil.getResponse(204, "Please SignIn");
				userResponse.setResponse(reponse);
				return userResponse;
			}
			else {
				boolean isProduct=productRepository.findByProductName(paymentDto.getProductName()).isPresent();
				if(isProduct) {
				
				boolean isCart=cartRepository.findByproductNameAndUserId(paymentDto.getProductName(), user.getUserId()).isPresent();
				if(isCart) {
					Cart cart=cartRepository.findByproductNameAndUserId(paymentDto.getProductName(), user.getUserId()).get();
					Products product=productRepository.findByProductName(paymentDto.getProductName()).get();
					if(product.getQuantity()>0 && product.getQuantity()>=product.getQuantity()-cart.getQuantity()) {
						product.setQuantity(product.getQuantity()-cart.getQuantity());
					}
					else {
						Response reponse=ResponseUtil.getResponse(204, "Not enough Quantity");
						userResponse.setResponse(reponse);
						return userResponse;
					}
					if(paymentDto.getCouponName()!=null && !paymentDto.getCouponName().equals("")) {
						
						boolean isCoupon=couponRepository.findByCouponName(paymentDto.getCouponName()).isPresent();
						if(isCoupon) {
							
							Coupon coupon=couponRepository.findByCouponName(paymentDto.getCouponName()).get();
							Long price=cart.getPrice()-coupon.getCoupon_price();
							Long discountPrice=0l;
							if(cart.getQuantity()>=5)
								discountPrice=price*disCount/100;
							
							price=price-discountPrice;
							
							if(user.getMoneyWallet()>=price) {
								Long couponPrice=coupon.getCoupon_price();
								Payment payment=modelMapper.map(paymentDto, Payment.class);
								payment.setCouponName(paymentDto.getCouponName());
								payment.setProductName(paymentDto.getProductName());
								payment.setTimeStamp(Utility.todayDate());
								payment.setQuantity(cart.getQuantity());
								payment.setCoupon_price(couponPrice);
								payment.setPrice(price);
								payment.setUserId(user.getUserId());
								user.setMoneyWallet(user.getMoneyWallet()-price);
								
								//user.setUpdatedDate(Utility.todayDate());
								cartRepository.deleteRecordByproductNameAndUserId(paymentDto.getProductName(), user.getUserId());
								//userRepository.save(user);
								paymentRepository.save(payment);
								productRepository.save(product);
								Response reponse=ResponseUtil.getResponse(204, "Payment Successfull");
								userResponse.setResponse(reponse);
								userResponse.setUser(user);
								return userResponse;
								
							}
							else {
								Response reponse=ResponseUtil.getResponse(204, "Not enough money, Please add");
								userResponse.setResponse(reponse);
								return userResponse;
								
							}
						}
						else {
							Response reponse=ResponseUtil.getResponse(204, "Coupon Not Present, Please Remove or Change");
							userResponse.setResponse(reponse);
							return userResponse;
						}
					}
					else {
						Long price=cart.getPrice();
						if(user.getMoneyWallet()>=price) {
							
							Long discountPrice=0l;
							if(cart.getQuantity()>=5)
								discountPrice=price*disCount/100;
							
							price=price-discountPrice;
							
							Payment payment=modelMapper.map(paymentDto, Payment.class);
							payment.setProductName(paymentDto.getProductName());
							payment.setTimeStamp(Utility.todayDate());
							payment.setQuantity(cart.getQuantity());
							payment.setUserId(user.getUserId());
							payment.setPrice(cart.getPrice());
							user.setMoneyWallet(user.getMoneyWallet()-price);
							user.setUpdatedDate(Utility.todayDate());
							cartRepository.deleteRecordByproductNameAndUserId(paymentDto.getProductName(), user.getUserId());
							//userRepository.save(user);
							paymentRepository.save(payment);
							productRepository.save(product);
							Response reponse=ResponseUtil.getResponse(204, "Payment Successfull Without coupon");
							userResponse.setResponse(reponse);
							userResponse.setUser(user);
							return userResponse;
						}
						else {
							Response reponse=ResponseUtil.getResponse(204, "Not enough money, Please add");
							userResponse.setResponse(reponse);
							return userResponse;
						}
						
					}
				}
				
				else {
					Response reponse=ResponseUtil.getResponse(204, "Product Not Present in the cart please add");
					userResponse.setResponse(reponse);
					return userResponse;
				}
				}
				else {
					Response reponse=ResponseUtil.getResponse(204, "Product Not Present");
					userResponse.setResponse(reponse);
					return userResponse;
				}
			}
		
			
//		}
//		else {
//			Response reponse=ResponseUtil.getResponse(204, "Invalid user");
//			return reponse;
//		
//		}
		
	}

	@Override
	public List<Payment> getAllPayments(String token) {
		Long userid = TokenUtil.verifyToken(token);
		return paymentRepository.findByUserId(userid);
	}
	

}
