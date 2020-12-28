package data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
//import org.hibernate.mapping.List;

import models.Users;
import models.Reimbursement;
import utils.DatabaseConnection;


/**
 * The UserDAO manages all interactions with the Database utilizing hibernate
 *
 * @author Justin Kroh
 * */
public class UsersDAO {
	
	private DatabaseConnection connection;
	private SessionFactory factory;
	
	public UsersDAO () {
		
		connection = new DatabaseConnection();
		this.factory = connection.getFactory();
	}
	
	
	@Deprecated
	   public Integer addEmployee(String fname, String lname, String email, String password, String address, String userType){
		   	  Session session = factory.openSession();
		      Transaction tx = null;
		      Integer userID = null;
		      
		      try {
		         tx = session.beginTransaction();
		         Users user = new Users();
		         user.setFirstName(fname);
		         user.setLastName(lname);
		         user.setEmail(email);
		         user.setPassword(password);
		         user.setAddress(address);
		         user.setUser_type(userType);
		         userID = (Integer) session.save(user); 
		         tx.commit();
		      } catch (HibernateException e) {
		         if (tx!=null) tx.rollback();
		         e.printStackTrace(); 
		      } finally {
		         session.close(); 
		      }
		      return userID;
		   }

	
	/**
	 * creates a New Reimbursement in the Database
	 * @param user_id the user_id
	 * @param description the type of reimbursement being requested
	 * @param amount the amount of money of the reimbursement
	 * @param the status of the reimbursement
	 * 
	 * @author Justin Kroh
	 * 
	 * @return reimbursementID ID stored in db
	 * */
	   public Integer submitReimbursement(int user_id, String description, float amount, String status) {
		   
		   Session session = factory.openSession();
		      Transaction tx = null;
		      Integer reimbursementID = null;
		      
		      try {
		    	  
		    	  System.out.println("AHHHAHAHAHA");
		         tx = session.beginTransaction();
		         Reimbursement reim = new Reimbursement();
		         reim.setUser_id(user_id);
		         reim.setDescription(description);
		         reim.setAmount(amount);
		         reim.setStatus(status);
		         reimbursementID = (Integer) session.save(reim); 
		         tx.commit();
		         System.out.println("LOLOLOLOL");
		      } catch (HibernateException e) {
		    	  
		    	  System.out.println("SOmethings Wrong");
		         if (tx!=null) tx.rollback();
		         System.out.println("Something is wrong");
		         e.printStackTrace(); 
		      } finally { 
		         session.close(); 
		      }
		      return reimbursementID;
	  }
	   
	   
		/**
		 * Retrieves a list of all users in db
	
		 * 
		 * @author Justin Kroh
		 * 
		 * @return list of all users in db
		 * */
	   public List<Users> listEmployees(){
		      Session session = factory.openSession();
		      Transaction tx = null;
		      List<Users> userlist = null;
		      
		      try {
		         tx = session.beginTransaction();
		        userlist = session.createQuery("FROM Users").list(); 
		         for (Users u: userlist){ 
		            System.out.print("First Name: " + u.getFirstName()); 
		            System.out.print("  Last Name: " + u.getLastName()); 
		            System.out.println("  Salary: " + u.getEmail()); 
		         }
		         tx.commit();
		      } catch (HibernateException e) {
		         if (tx!=null) tx.rollback();
		         e.printStackTrace(); 
		      } finally {
		         session.close(); 
		      }
		      
		      return userlist;
		   }
	   
	   
		/**
		 * gets user account by email
	
		 * @param String email
		 * @author Justin Kroh
		 * 
		 * @return user with email
		 * */
	   public Users getUserByEmail(String email){
		      Session session = factory.openSession();
		      Transaction tx = null;
		      List<Users> userlist = null;
		      Users user = null;
		      
		      try {
		         tx = session.beginTransaction();
		         
		         String hql = "FROM Users U WHERE U.email = :useremail";
		         Query query = session.createQuery(hql);
		         query.setParameter("useremail", email);
		         userlist = query.list();	         

		         tx.commit();
		      } catch (HibernateException e) {
		         if (tx!=null) tx.rollback();
		         e.printStackTrace(); 
		      } finally {
		         session.close(); 
		      }
		
		      
		      System.out.println(userlist);
		      return userlist.get(0);
		   }
	   
	   
		/**
		 * gets user reimbursements by ID
	
		 * @param user_id
		 * @author Justin Kroh
		 * 
		 * @return list of all reimbursements associated with user
		 * */
	   public List<Reimbursement> getUserReimbursements(int user_id){
		      Session session = factory.openSession();
		      Transaction tx = null;
		      List<Reimbursement> reimList = null;
		      
		      try {
		         tx = session.beginTransaction();
		         
		         String hql = "FROM Reimbursement R WHERE R.user_id = :userID";
		         Query query = session.createQuery(hql);
		         query.setParameter("userID", user_id);
		         reimList = query.list();	         

		         tx.commit();
		      } catch (HibernateException e) {
		         if (tx!=null) tx.rollback();
		         e.printStackTrace(); 
		      } finally {
		         session.close(); 
		      }
		
		      
		      System.out.println(reimList);
		      return reimList;
		   }
	   
	   
	   
		/**
		 * gets user reimbursements by user id and status
	
		 * @param status user_id status PENDING, APPROVED, DENIED
		 * @author Justin Kroh
		 * 
		 * @return list of reimbursements associated with user with said criteria
		 * */
	   public List<Reimbursement> getUserReimbursementsByStatus(int user_id, String status){
		      Session session = factory.openSession();
		      Transaction tx = null;
		      List<Reimbursement> reimList = null;
		      
		      try {
		         tx = session.beginTransaction();
		         
		         String hql = "FROM Reimbursement R WHERE R.user_id = :userID and R.status = :status";
		         Query query = session.createQuery(hql);
		         query.setParameter("userID", user_id);
		         query.setParameter("status", status);
		         reimList = query.list();	         

		         tx.commit();
		      } catch (HibernateException e) {
		         if (tx!=null) tx.rollback();
		         e.printStackTrace(); 
		      } finally {
		         session.close(); 
		      }
		
		      
		      System.out.println(reimList);
		      return reimList;
		   }
	   
	   
	   
	   
	   
		/**
		 * gets all reimbursements in db
	
		 * @author Justin Kroh
		 * 
		 * @return list of all reimbursements
		 * */
	   public List<Reimbursement> getAllReimbursements(){
		      Session session = factory.openSession();
		      Transaction tx = null;
		      List<Reimbursement> reimList = null;
		      
		      try {
		         tx = session.beginTransaction();
		         
		         String hql = "FROM Reimbursement";
		         Query query = session.createQuery(hql);
		         reimList = query.list();	         

		         tx.commit();
		      } catch (HibernateException e) {
		         if (tx!=null) tx.rollback();
		         e.printStackTrace(); 
		      } finally {
		         session.close(); 
		      }
		
		      
		      System.out.println(reimList);
		      return reimList;
		   }
	   
	   /*
	   @Deprecated
	   public void updateUserInfo(int user_id, String column_name, String value){
		      Session session = factory.openSession();
		      Transaction tx = null;
		      List<Reimbursement> reimList = null;
		      
		      try {
		         tx = session.beginTransaction();
		         
		         String hql = "UPDATE Users set :column_name = :value WHERE user_id = :userID";
		         Query query = session.createQuery(hql);
		         //query.setParameter("column_name", column_name);
		         query.setParameter("value", value);
		         query.setParameter("userID", user_id);
		         query.executeUpdate();

		         tx.commit();
		      } catch (HibernateException e) {
		         if (tx!=null) tx.rollback();
		         e.printStackTrace(); 
		      } finally {
		         session.close(); 
		      }
		
		      
		      System.out.println(reimList);
		      //return reimList;
		   }
	  */ 
	   

	   public boolean updateUserAddress(int user_id, String value){
		      Session session = factory.openSession();
		      Transaction tx = null;
		      List<Reimbursement> reimList = null;
		      boolean pass = true;
		      
		      try {
		         tx = session.beginTransaction();
		         
		         String hql = "UPDATE Users set address = :value WHERE user_id = :userID";
		         Query query = session.createQuery(hql);
		         query.setParameter("value", value);
		         query.setParameter("userID", user_id);
		         query.executeUpdate();

		         tx.commit();
		      } catch (HibernateException e) {
		         if (tx!=null) tx.rollback();
		         e.printStackTrace(); 
		         pass = false;
		      } finally {
		         session.close(); 
		      }
		      return pass;
		   }
	   
		/**
		 * updates user Last name in db
	
		 * @param String value - value to be updated
		 * @param user_id 
		 * @author Justin Kroh
		 * 
		 * @return successful boolean
		 * */
	   public boolean updateUserLastName(int user_id, String value){
		      Session session = factory.openSession();
		      Transaction tx = null;
		      List<Reimbursement> reimList = null;
		      boolean pass = true;
		      
		      try {
		         tx = session.beginTransaction();
		         
		         String hql = "UPDATE Users set lastName = :value WHERE user_id = :userID";
		         Query query = session.createQuery(hql);
		         query.setParameter("value", value);
		         query.setParameter("userID", user_id);
		         query.executeUpdate();

		         tx.commit();
		      } catch (HibernateException e) {
		         if (tx!=null) tx.rollback();
		         e.printStackTrace(); 
		         pass = false;
		      } finally {
		         session.close(); 
		      }
		      return pass;
		   }
	   
		/**
		 * updates user First Name in db
	
		 * @param String value - value to be updated
		 * @param user_id 
		 * @author Justin Kroh
		 * 
		 * @return successful boolean
		 * */
	   public boolean updateUserFirstName(int user_id, String value){
		      Session session = factory.openSession();
		      Transaction tx = null;
		      List<Reimbursement> reimList = null;
		      boolean pass = true;
		      
		      try {
		         tx = session.beginTransaction();
		         
		         String hql = "UPDATE Users set firstName = :value WHERE user_id = :userID";
		         Query query = session.createQuery(hql);
		         query.setParameter("value", value);
		         query.setParameter("userID", user_id);
		         query.executeUpdate();

		         tx.commit();
		      } catch (HibernateException e) {
		         if (tx!=null) tx.rollback();
		         e.printStackTrace(); 
		         pass = false;
		      } finally {
		         session.close(); 
		      }
		      return pass;
		   }
	   
	   
		/**
		 * updates user password in db
	
		 * @param String value - value to be updated
		 * @param user_id 
		 * @author Justin Kroh
		 * 
		 * @return successful boolean
		 * */
	   public boolean updateUserPassword(int user_id, String value){
		      Session session = factory.openSession();
		      Transaction tx = null;
		      List<Reimbursement> reimList = null;
		      boolean pass = true;
		      
		      try {
		         tx = session.beginTransaction();
		         
		         String hql = "UPDATE Users set password = :value WHERE user_id = :userID";
		         Query query = session.createQuery(hql);
		         query.setParameter("value", value);
		         query.setParameter("userID", user_id);
		         query.executeUpdate();

		         tx.commit();
		      } catch (HibernateException e) {
		         if (tx!=null) tx.rollback();
		         e.printStackTrace(); 
		         pass = false;
		      } finally {
		         session.close(); 
		      }
		      return pass;
		   }
	   
		/**
		 * updates user email in db
	
		 * @param String value - value to be updated
		 * @param user_id 
		 * @author Justin Kroh
		 * 
		 * @return successful boolean
		 * */
	   public boolean updateUserEmail(int user_id, String value){
		      Session session = factory.openSession();
		      Transaction tx = null;
		      List<Reimbursement> reimList = null;
		      boolean pass = true;
		      
		      try {
		         tx = session.beginTransaction();
		         
		         String hql = "UPDATE Users set email = :value WHERE user_id = :userID";
		         Query query = session.createQuery(hql);
		         query.setParameter("value", value);
		         query.setParameter("userID", user_id);
		         query.executeUpdate();

		         tx.commit();
		      } catch (HibernateException e) {
		         if (tx!=null) tx.rollback();
		         e.printStackTrace(); 
		         pass = false;
		      } finally {
		         session.close(); 
		      }
		      return pass;
		   }
	   
	   
		/**
		 * updates reimbursement status of a reimbursement in db
	
		 * @param String value - value to be updated
		 * @param reimbursement_id 
		 * @author Justin Kroh
		 * 
		 * @return successful boolean
		 * */
	   public boolean updateReimbursementStatus(int reimbursement_id, String value){
		      Session session = factory.openSession();
		      Transaction tx = null;
		      List<Reimbursement> reimList = null;
		      boolean pass = true;
		      
		      try {
		         tx = session.beginTransaction();
		         
		         String hql = "UPDATE Reimbursement set status = :value WHERE reimbursement_id = :reimbursementID";
		         Query query = session.createQuery(hql);
		         query.setParameter("value", value);
		         query.setParameter("reimbursementID", reimbursement_id);
		         query.executeUpdate();

		         tx.commit();
		      } catch (HibernateException e) {
		         if (tx!=null) tx.rollback();
		         e.printStackTrace(); 
		         pass = false;
		      } finally {
		         session.close(); 
		      }
		      return pass;
		   }
	   
	   

	   
		/**
		 * verifies user credentials in db
	
		 * @param email
		 * @param password 
		 * @author Justin Kroh
		 * 
		 * @return successful boolean
		 * */
	   public boolean checkCredentials(String email, String password){
		      Session session = factory.openSession();
		      Transaction tx = null;
		      List<Users> userlist = null;
		      
		      boolean bool = false;
		      
		      System.out.println(email);
		      System.out.println(password);
		      
		      try {
		         tx = session.beginTransaction();
		        userlist = session.createQuery("FROM Users").list(); 
		         for (Users u: userlist){ 
		        	 
		        	 if (u.getEmail().equals(email) && u.getPassword().equals(password)) {
		        		 
		        		 bool = true;
		        		 
		        		 System.out.print("First Name: " + u.getFirstName()); 
				         System.out.print("  Last Name: " + u.getLastName()); 
				         System.out.println("  Salary: " + u.getEmail()); 
		        	 }
		        	 else System.out.println("AHHH");
		         }
		         tx.commit();
		      } catch (HibernateException e) {
		         if (tx!=null) tx.rollback();
		         e.printStackTrace(); 
		      } finally {
		         session.close(); 
		      }
		      
		      return bool;
		   }

	public DatabaseConnection getConnection() {
		return connection;
	}

	public void setConnection(DatabaseConnection connection) {
		this.connection = connection;
	}

	public SessionFactory getFactory() {
		return factory;
	}

	public void setFactory(SessionFactory factory) {
		this.factory = factory;
	}
		   
	   
}


