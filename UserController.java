package org.jsp.springUserfoodOrderApp.controller;

import java.util.List;
import java.util.Scanner;

import org.jsp.springUserfoodOrderApp.UserConfig;
import org.jsp.springUserfoodOrderApp.dao.FoodDao;
import org.jsp.springUserfoodOrderApp.dao.UserDao;
import org.jsp.springUserfoodOrderApp.dto.FoodOrder;
import org.jsp.springUserfoodOrderApp.dto.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class UserController {
	
	static Scanner s = new Scanner(System.in);
	static UserDao uDao;
	static FoodDao fDao;
	static {
		ApplicationContext c = new AnnotationConfigApplicationContext(UserConfig.class);
		uDao = c.getBean(UserDao.class);
		fDao = c.getBean(FoodDao.class);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		System.out.println("1. Enter to perform save User: ");
		System.out.println("2. Enter id to fetchUser");
		System.out.println("3. Enter the id to UpdateUser");
		System.out.println("4. Enter the id to deleteUser");
		System.out.println("5. To fetch User by phone and password");
		System.out.println("6. To save FoodOrder");
		System.out.println("7. To update the FoodOrder");
		System.out.println("8. To fetch Food By Id");
		System.out.println("9. To delete FoodOrder");
		System.out.println("10. To fetch FoodOrder By FoodItem");
		System.out.println("11. To fetch FoodOrder By UserId");
		System.out.println("12. To fetch User By FoodId");
		System.out.println("13. To delete FoodBy userId");
		int choice = s.nextInt();
		switch(choice) {
		case 1:{
			saveUser();
			break;
		}
		case 2:{
			fetchById();
			break;
		}
		case 3:{
			UpdateuserById();
			break;
		}
		case 4:{
			DeleteUserById();
			break;
		}
		case 5:{
			fetchUserByPhoneAndPassword();
			break;
		}
		case 6:{
			saveFoodOrder();
			break;
		}
		case 7:{
			updateFoodOrder();
			break;
		}
		case 8:{
			fetchFoodById();
			break;
		}
		case 9:{
			deleteFoodOrder();
			break;
		}
		case 10:{
			fetchFoodByFoodItems();
			break;
		}
		case 11:{
			fetchFoodByUserId();
			break;
		}
		case 12:{
			fetchUserByFoodId();
			break;
		}
		case 13:{
			deleteFoodByUserId();
			break;
		}
		}

	}

	public static void deleteFoodByUserId() {
		// TODO Auto-generated method stub
		
		System.out.println("Enter the userId");
		int uid = s.nextInt();
		List<FoodOrder> f = fDao.DeleteFoodByUserId(uid);
		for(FoodOrder fo:f) {
			boolean deleted = fDao.deleteFoodOrder(fo);
			System.out.println("Deleted All");
		}
		
		
	}

	public static void fetchUserByFoodId() {
		// TODO Auto-generated method stub
		
		System.out.println("Enter the foodId");
		int fid = s.nextInt();
		User u = uDao.FetchUserByFoodId(fid);
		if(u!=null) {
			System.out.println(u);
		}
		else {
			System.err.println("invalid id");
		}
		
	}

	public static void fetchFoodByUserId() {
		// TODO Auto-generated method stub
		
		System.out.println("Enter the userId");
		int uid = s.nextInt();
		List<FoodOrder> f = fDao.fetchFoodByUserId(uid);
		for(FoodOrder fo: f) {
			System.out.println(fo);
		}
		
	}

	public static void fetchFoodByFoodItems() {
		// TODO Auto-generated method stub
		System.out.println("Enter the foodItems");
		String foodItems = s.next();
		List<FoodOrder> f = fDao.fetchFoodByFoodItems(foodItems);
		for(FoodOrder fo: f) {
			System.out.println(fo);
		}
		
	}

	public static void deleteFoodOrder() {
		// TODO Auto-generated method stub
		
		System.out.println("Enter the id to delete");
		int id = s.nextInt();
		FoodOrder f = fDao.findFoodById(id);
		if(f!=null) {
			boolean deleted = fDao.deleteFoodOrder(f);
			System.out.println("Deleted");
		}
		else {
			System.err.println("Invalid id");
		}
		
	}

	public static void fetchFoodById() {
		// TODO Auto-generated method stub
		
		System.out.println("Enter the id");
		int id = s.nextInt();
		FoodOrder f = fDao.findFoodById(id);
		if(f!=null) {
			System.out.println(f);
		}
		else {
			System.err.println("Invalid id");
		}
		
	}

	public static void updateFoodOrder() {
		// TODO Auto-generated method stub
		
		System.out.println("Enter the id");
		int id = s.nextInt();
		FoodOrder f = fDao.findFoodById(id);
		if(f!=null) {
			System.out.println("Enter the cost and food_items");
			double cost = s.nextDouble();
			String food_items = s.next();
			f.setFood_item(food_items);
			f.setCost(cost);
			f = fDao.updateFood(f);
		}
		else {
			System.err.println("Invalid Id");
		}
		
		
	}

	public  static void saveFoodOrder() {
		// TODO Auto-generated method stub
		
		System.out.println("Enter the uid");
		int uid = s.nextInt();
		System.out.println("Enter the cost and food_item");
		double cost = s.nextDouble();
		String food_items = s.next();
		FoodOrder fo = new FoodOrder();
		fo.setCost(cost);
		fo.setFood_item(food_items);
		fo = fDao.saveFood(fo, uid);
		System.out.println(fo.getId());
		
	}

	public static void fetchUserByPhoneAndPassword() {
		// TODO Auto-generated method stub
		
		System.out.println("Enter the phone and password");
		long phone = s.nextLong();
		String password = s.next();
		User u = uDao.fetchUserByPhoneAndPassword(phone, password);
		if(u!=null) {
			System.out.println(u);
		}
		else {
			System.err.println("invalid phone and password");
		}
		
	}

	public static void DeleteUserById() {
		// TODO Auto-generated method stub
		
		System.out.println("Enter the id ");
		int id = s.nextInt();
		
		User u = uDao.fetchUserById(id);
		if(u!=null) {
				List<FoodOrder> f = fDao.DeleteFoodByUserId(id);
				for(FoodOrder fo:f) {
					boolean del = fDao.deleteFoodOrder(fo);
					System.out.println("Deleted All");
				}
				boolean deleted = uDao.deleteUser(u);
			
		}
		else {
			System.err.println("not deleted");
		}
		
	}

	private static void UpdateuserById() {
		// TODO Auto-generated method stub
		
		System.out.println("Enter the id to update");
		int id = s.nextInt();
		System.out.println("Enter the name and password to update");
		String name = s.next();
		String password = s.next();
		User u = uDao.fetchUserById(id);
		if(u!=null) {
			u.setPassword(password);
			u.setName(name);
			u = uDao.updateUser(u);
		}
		else {
			System.err.println("Invalid id");
		}
		
	}

	public static void fetchById() {
		// TODO Auto-generated method stub
		
		System.out.println("Enter the id");
		int id = s.nextInt();
		User u = uDao.fetchUserById(id);
		System.out.println(u.getName());
		
	}

	public static void saveUser() {
		// TODO Auto-generated method stub
		
		System.out.println("Enter the name, email, phone and password");
		String name = s.next();
		String email = s.next();
		long phone = s.nextLong();
		String password = s.next();
		User u = new User();
		u.setEmail(email);
		u.setName(name);
		u.setPhone(phone);
		u.setPassword(password);
		u = uDao.saveUser(u);
		System.out.println("save with id :" + u.getId());
	}

}
