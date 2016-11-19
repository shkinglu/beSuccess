package com.lu.jack.dao;

import java.util.List;

public class User {

	

	public User( Integer id, String userName, String password, String nickName, Integer userType) {
		super();
		this.setId(id);
		this.userName = userName;
		this.password = password;
		this.nickName = nickName;
		this.userType = userType;
	}
		
	
	private List<Product>  product;
	
	private int id;
	private int  userType;
	private String userName;
	private String password;
	private String nickName;
	
	public  User(){
	
 	}
 


	

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public int getUserType() {
		return userType;
	}

	public void setUserType(int userType) {
		this.userType = userType;
	}
	
	




	public List<Product> getProduct() {
		return product;
	}





	public void setProduct(List<Product> product) {
		this.product = product;
	}





	public int getId() {
		return id;
	}





	public void setId(int id) {
		this.id = id;
	}
	
}
