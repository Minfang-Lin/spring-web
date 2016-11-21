package com.netease.course.model;

/**
 * @author linminfang
 * @create_time：2016.11.05 
 * @version V1.0.0 
 *
 */
public class Trx {
	private int id;
	private int contentId;
	private int personId;
	private double price;
	private Long time;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getContentId() {
		return contentId;
	}
	public void setContentId(int contentId) {
		this.contentId = contentId;
	}
	public int getPersonId() {
		return personId;
	}
	public void setPersonId(int personId) {
		this.personId = personId;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public Long getTime() {
		return time;
	}
	public void setTime(Long time) {
		this.time = time;
	}
}
