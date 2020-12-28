package utils;


import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;

import models.Users;

public class DatabaseConnection {
	
	/**
	 * Creates a session factory object based on settings in hibernate.cfg.xml
	 * The Session factory creates sessions that interact with the database

	 * @author Justin Kroh
	 * 
	 * */
	private SessionFactory factory;
	

	public DatabaseConnection() {
	   try {
	         factory = new AnnotationConfiguration().
	                   configure().
	                   addAnnotatedClass(Users.class).
	                   buildSessionFactory();
	      } catch (Throwable ex) { 
	         System.err.println("Failed to create sessionFactory object." + ex);
	         throw new ExceptionInInitializerError(ex); 
	      }
	 	
	}


	public SessionFactory getFactory() {
		return factory;
	}


	public void setFactory(SessionFactory factory) {
		this.factory = factory;
		
	}



	
	
	
}
