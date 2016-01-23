/**
* LoginBean.java
*
*/
package com.haks.managedBeans;


import java.sql.ResultSet;
import java.util.Iterator;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.haks.entities.Dao;
import com.haks.entities.Users;

@ManagedBean(name="loginBean")
@SessionScoped
public class LoginBean {
	private String name;
	private String password;
	private String message;
	private List<Users> usersList;
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

	public String redirect(){
		//Tworzymy obiekt Dao, który ma entityManager
		Dao dao = new Dao();
		//begin transaction
		dao.open();
		//Zmienna na naszego usera
		Users u = null;
		
		//u = dao.getEntityManager().find(Users.class, 1L);
		System.out.println(this.getName());
		
		//pobieramy naszego usera z bazy
		Query query  = dao.getEntityManager().
				createQuery("select u from Users u where u.name = '"+this.getName()+
						"'",Users.class);
		Iterator<?> iterator = query.getResultList().iterator();
		//jezeli istnieje w bazie user z tym loginem
		if (iterator.hasNext()){
			u = (Users)iterator.next();
		}else{
		//jezeli nie istnieje user z tym loginem
			u = null;
		}
		
		//jezeli istnieje wiecej uzytkownikow z tym samym loginem
		if (iterator.hasNext()){
			System.out.println("Wiêcej u¿tkowników ni¿ 1 z tym samym loginem ERROR");
		}
		
		//transaction commit i close
		dao.close();

		//przekierowanie do ponownego logowania lub do strony welcome
		if (u == null){
			this.message = "Nie ma takiego u¿ytkownika";
			return "login.xhtml";
		}
		if (u != null && u.getPassword().equals(this.password))
			return "welcome.xhtml";

		if (u.getPassword() != this.password)
			this.message = "Z³e has³o";
		
		return "login.xhtml";

	}
	
	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public List<Users> getUsersList() {
		Dao dao = new Dao();
		dao.open();
		TypedQuery<Users> query = dao.getEntityManager().
				createQuery("select u from Users u",Users.class);
	
		this.usersList = query.getResultList();
		return this.usersList;
	}

	public void setUsersList(ResultSet usersList) {
		
	}

	public void setPassword(final String password) {
		this.password = password;
	}
}