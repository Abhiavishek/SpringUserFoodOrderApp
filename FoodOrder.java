package org.jsp.springUserfoodOrderApp.dto;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
public class FoodOrder {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private double cost;
	private String food_item;
	@CreationTimestamp
	private LocalDate ordered_time;
	@UpdateTimestamp
	private LocalDate del_time;
	@ManyToOne
	@JoinColumn
	private User user;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public double getCost() {
		return cost;
	}
	public void setCost(double cost) {
		this.cost = cost;
	}
	public String getFood_item() {
		return food_item;
	}
	public void setFood_item(String food_item) {
		this.food_item = food_item;
	}
	public LocalDate getOrdered_time() {
		return ordered_time;
	}
	public void setOrdered_time(LocalDate ordered_time) {
		this.ordered_time = ordered_time;
	}
	public LocalDate getDel_time() {
		return del_time;
	}
	public void setDel_time(LocalDate del_time) {
		this.del_time = del_time;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	@Override
	public String toString() {
		return "FoodOrder [id=" + id + ", cost=" + cost + ", food_item=" + food_item + ", ordered_time=" + ordered_time
				+ ", del_time=" + del_time + "]";
	}
	
	

}
