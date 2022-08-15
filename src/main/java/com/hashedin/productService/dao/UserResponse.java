package com.hashedin.productService.dao;

import com.hashedin.productService.utility.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserResponse {
	
	private Response response;
	private User user;
	public Response getResponse() {
		return response;
	}
	public void setResponse(Response response) {
		this.response = response;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	@Override
	public String toString() {
		return "UserResponse [response=" + response + ", user=" + user + "]";
	}
	
	

}
