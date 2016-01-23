package com.haks.entities;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Dao {
	
	public static EntityManagerFactory entityManagerFactory;
	
	static{
		entityManagerFactory = Persistence.createEntityManagerFactory("bazy");
	}
	EntityManager entityManager;

	public void open(){
		entityManager = Dao.entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		
	}
	public EntityManager getEntityManager(){
		return entityManager;
	}
	public void close() {
		entityManager.getTransaction().commit();
		entityManager.close();
		//entityManagerFactory.close();
	}
	public void fastPersist(Object e){
		e = e.getClass().cast(e);
		this.open();
		this.getEntityManager().persist(e);
		this.close();
	}

}
