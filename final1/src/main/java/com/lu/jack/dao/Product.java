package com.lu.jack.dao;




public class Product {
	
		public Product(int id, String title, String detail, String image, String summary, double buyPrice,long buyTime,boolean isBuy,int price) {
		super();
		this.id = id;
		this.title = title;
		this.detail = detail;
		this.image = image;
		this.summary = summary;
		this.buyPrice = buyPrice;
		this.isBuy=isBuy;
		this.buyTime=buyTime;
		this.price=price;
	}
		
		
	public Product(){
		
	}
	private int price;
	private Boolean isBuy;
	private long buyTime;
	private int id;
	private String title;
	private String detail;
	private String image;
	private String summary;
	private double buyPrice;
	
	private  Boolean  IsSold;
	
	

	public Boolean getIsBuy() {
		return isBuy;
	}


	public void setIsBuy(Boolean isBuy) {
		this.isBuy = isBuy;
	}


	public long getBuyTime() {
		return buyTime;
	}


	public void setBuyTime(long buyTime) {
		this.buyTime = buyTime;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getDetail() {
		return detail;
	}


	public void setDetail(String detail) {
		this.detail = detail;
	}


	public String getImage() {
		return image;
	}


	public void setImage(String image) {
		this.image = image;
	}


	public String getSummary() {
		return summary;
	}


	public void setSummary(String summary) {
		this.summary = summary;
	}


	public double getBuyPrice() {
		return buyPrice;
	}


	public void setBuyPrice(double buyPrice) {
		this.buyPrice = buyPrice;
	}


	public Boolean getIsSold() {
		return IsSold;
	}


	public void setIsSold(Boolean isSold) {
		IsSold = isSold;
	}


	public int getPrice() {
		return price;
	}


	public void setPrice(int price) {
		this.price = price;
	}




	
	
}
