package erspro;

import io.javalin.Javalin;
import org.hibernate.*;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;

import controller.UserController;
import models.Users;
import data.UsersDAO;





/**
 * Maps api endpoint urls to Handler methods in the usercontroller to handle the requests
 * 
 * @author Justin Kroh
 * 

 * */
public class Main {
	private static SessionFactory factory;
	//private Session session;
	
	public static Javalin app;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 	
		//UsersDAO userdao = new UsersDAO();
			
		//userdao.addEmployee("Justin", "Kroh", "JK3@gmail.com", "12345", "77 test lane", "EMPLOYEE");
		
		//userdao.submitReimbursement(1, "AWS BILL", 100, "PENDING");
		
		//userdao.listEmployees();
			
			
		   //int r = Main.addEmployee("Justin", "Kroh", "JK@gmail.com", "12345", "77 test lane", "EMPLOYEE");
		   
		  // System.out.println(r);
		   
	    app = Javalin.create(config -> {
            config.addStaticFiles("/public/"); // get javalin to serve our static files from the public folder.
            // which I added to the resources directory
            config.contextPath = "/erspro";   // set the application context path will result in
            // http://localhost:8080/numbers
	    })	.start(7000);
	    app.get("/", ctx -> ctx.result("Hello World lol"));
	    
	    app.get("/users", UserController.fetchAllUsernames);
	    
	    //app.get("/home", UserController.viewHomepage);
	    
	    app.post("/check_credentials", UserController.checkCredentials);
	    
	    app.get("/getUserSessionData", UserController.getUserSessionData);
	    
	    app.get("/getUserReimbursements", UserController.getUserReimbursements);
	    
	    app.post("/createReimbursementRequest", UserController.createReimbursementRequest);
	    
	    app.post("/updateUserEmail", UserController.updateUserEmail);
	    
	    app.post("/updateUserPassword", UserController.updateUserPassword);
	    
	    app.post("/updateUserFirstName", UserController.updateUserFirstName);
	    
	    app.post("/updateUserLastName", UserController.updateUserLastName);
	    
	    app.post("/updateUserAddress", UserController.updateUserAddress);
	    
	    app.post("/getUserReimbursementsByID", UserController.getUserReimbursementsByID);
	    
	    app.get("/getAllReimbursements", UserController.getAllReimbursements);
	    
	    app.get("/getUserPendingReimbursements", UserController.getUserPendingReimbursements);
	    
	    app.get("/getUserApprovedReimbursements", UserController.getUserApprovedReimbursements);
	    
	    app.get("/getUserDeniedReimbursements", UserController.getUserDeniedReimbursements);
	      
	    app.post("/updateReimbursementStatus", UserController.updateReimbursementStatus);
	    
	    
	      


	}
	
}
	
