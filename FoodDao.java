package org.jsp.springUserfoodOrderApp.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.jsp.springUserfoodOrderApp.dto.FoodOrder;
import org.jsp.springUserfoodOrderApp.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
@Repository
public class FoodDao {
	@Autowired
	EntityManager m;
	
	public FoodOrder saveFood(FoodOrder fo, int uid) {
		User u = m.find(User.class, uid);
		if(u !=null) {
			u.getFood().add(fo);
			fo.setUser(u);
			EntityTransaction t = m.getTransaction();
			m.persist(fo);
			t.begin();
			t.commit();
			return fo;
		}
		return null;
	}
	
	public FoodOrder updateFood(FoodOrder fo) {
		EntityTransaction t = m.getTransaction();
	    m.merge(fo);
		t.begin();
		t.commit();
		return fo;
	}
	
	public FoodOrder findFoodById(int id) {
		return  m.find(FoodOrder.class, id);
	}
	
	public boolean deleteFoodOrder(FoodOrder f) {
		EntityTransaction t = m.getTransaction();
		m.remove(f);
		t.begin();
		t.commit();
		return true;
	}
	
	public List<FoodOrder> fetchFoodByFoodItems(String foodItem){
		String qry = "select f from FoodOrder f where f.food_item=?1";
		Query q = m.createQuery(qry);
		q.setParameter(1, foodItem);
		return q.getResultList();
	}
	
	public List<FoodOrder> fetchFoodByUserId(int uid){
		String qry = "select u.food from User u where u.id=?1";
		Query q = m.createQuery(qry);
		q.setParameter(1, uid);
		return q.getResultList();
	}
	
	public List<FoodOrder> DeleteFoodByUserId(int uid){
		String qry = "select u.food from User u where u.id=?1";
		Query q = m.createQuery(qry);
		q.setParameter(1, uid);
		return q.getResultList();
	}
	
	

}
