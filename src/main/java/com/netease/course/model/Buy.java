package com.netease.course.model;

public class Buy {
	private int id;
	private String title;
	private String image;
	private int buyPrice;
	private long buyTime;
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
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public int getBuyPrice() {
		return buyPrice;
	}
	public void setBuyPrice(int buyPrice) {
		this.buyPrice = buyPrice;
	}
	public long getBuyTime() {
		return buyTime;
	}
	public void setBuyTime(long buyTime) {
		this.buyTime = buyTime;
	}
}
