package com.haks.javase;
import com.haks.entities.Dao;
import com.haks.entities.Users;

public class Main {

	public static void main(String[] args) {

		Users user = new Users();
		user.setName("Laluœ");
		user.setEmail("xxx@xx.xx");
		user.setPassword("password");
		
		/*Dao dao = new Dao();
		dao.open();
		dao.getEntityManager().persist(user);
		dao.close();*/
		
		new Dao().fastPersist(user);
	}

}
