package org.jsp.springUserfoodOrderApp.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.jsp.springUserfoodOrderApp.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao {
	@Autowired
	EntityManager m;
	
	public User saveUser (User u) {
		EntityTransaction t = m.getTransaction();
		m.persist(u);
		t.begin();
		t.commit();
		return u;
		
	}
	
	public User fetchUserById(int id) {
		return m.find(User.class, id);
	}
	
	public User updateUser(User u) {
			EntityTransaction t =m.getTransaction();
			m.merge(u);
			t.begin();
			t.commit();
			return u;
	}
	
	public boolean deleteUser(User u) {
		EntityTransaction t = m.getTransaction();
		m.remove(u);
		t.begin();
		t.commit();
		return true;
	}
	
	public User fetchUserByPhoneAndPassword(long phone, String password) {
		
		String qry = "select u from User u where u.phone=?1 and u.password=?2";
		Query q = m.createQuery(qry);
		if(q!=null) {
			q.setParameter(1, phone);
			q.setParameter(2, password);
			return (User)q.getSingleResult();
		}
		return null;
	}
	
	public User FetchUserByFoodId(int fid) {
		String qry = "select f.user from FoodOrder f where f.id=?1";
		Query q = m.createQuery(qry);
		q.setParameter(1, fid);
		return (User)q.getSingleResult();
	}

}
