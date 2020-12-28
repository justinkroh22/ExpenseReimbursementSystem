package controller;

import java.util.List;

import data.UsersDAO;
import erspro.Main;
import io.javalin.http.Handler;
import models.Users;
import models.Reimbursement;
import io.javalin.Javalin;



/**
 * The User Contoller "Handles" All incoming API requests. The handlers call the DAO which interacts with the database.
 *
 * @author Justin Kroh

 * */
public class UserController {

    /**
     * Returns a list of all users in the database.
     *
     * @author Justin Kroh
     * @return List of all users.
     * */
    public static Handler fetchAllUsernames = ctx -> {
        UsersDAO userdao = new UsersDAO();
        List<Users> userlist = userdao.listEmployees();
        
        if (userlist != null) {
        	ctx.status(200);
        }
        ctx.json(userlist);
    };
    
    
    
    
    /**
     * Creates a reimbursement request using form fields
     *
     * @author Justin Kroh
     * */
    public static Handler createReimbursementRequest = ctx -> {
        UsersDAO userdao = new UsersDAO();
        
        System.out.println(ctx.body());
        
        String description = ctx.formParam("description");
        
        System.out.println(description + "1");
        
        String amount = ctx.formParam("amount");
        
        System.out.println(amount + "2");
        
        String status = "PENDING";
        
        Float parsedAmount = Float.parseFloat(amount);
        
        int user_id = ctx.sessionAttribute("user_id");
        
        int reimID = userdao.submitReimbursement(user_id, description, parsedAmount, status);
        
        

        System.out.println(ctx.body());
        
        ctx.json(reimID);
        System.out.println(reimID);
    };
    
    /*
    @Deprecated
    public static Handler updateUserInfo = ctx -> {
        UsersDAO userdao = new UsersDAO();
        
        System.out.println(ctx.body());
        
        String email = ctx.formParam("email");
        
        String password = ctx.formParam("password");
        
        String firstName = ctx.formParam("firstName");
        
        String lastName = ctx.formParam("lastName");
        
        String address = ctx.formParam("address");
        
        
        //String status = "PENDING";
        
        
        int user_id = ctx.sessionAttribute("user_id");
        
        userdao.updateUserInfo(user_id, "email", email);
        userdao.updateUserInfo(user_id, "password", password);
        userdao.updateUserInfo(user_id, "firstName", firstName);
        userdao.updateUserInfo(user_id, "lastName", lastName);
        userdao.updateUserInfo(user_id, "address", address);
        
        

        System.out.println(ctx.body());
        
        //ctx.json(reimID);
        //System.out.println(reimID);
    };
    
    */
    
    /**
     * Updates user email in databse
     *
     * @author Justin Kroh
     * */
    public static Handler updateUserEmail = ctx -> {
        UsersDAO userdao = new UsersDAO();
        
        System.out.println(ctx.body());
        
        String email = ctx.formParam("email");


        int user_id = ctx.sessionAttribute("user_id");
        
        boolean pass = userdao.updateUserEmail(user_id, email);

        if (pass == true) {
        	ctx.status(200);
        }
        
        ctx.json("updated");
        //System.out.println(reimID);
    };
    
    /**
     * Updates user password in database
     *
     * @author Justin Kroh
     * */
    public static Handler updateUserPassword = ctx -> {
        UsersDAO userdao = new UsersDAO();
        
        System.out.println(ctx.body());
       
        String password = ctx.formParam("password");
        
        
        
        int user_id = ctx.sessionAttribute("user_id");

        boolean pass = userdao.updateUserPassword(user_id, password);

        if (pass == true) {
        	ctx.status(200);
        }
        System.out.println(ctx.body());
        
        ctx.json("updated");
        //System.out.println(reimID);
    };
    
    /**
     * Updates user first name in databse
     *
     * @author Justin Kroh
     * */
    public static Handler updateUserFirstName = ctx -> {
        UsersDAO userdao = new UsersDAO();
        
        System.out.println(ctx.body());
        
        String firstName = ctx.formParam("firstName");
        
        
        int user_id = ctx.sessionAttribute("user_id");
        

        boolean pass = userdao.updateUserFirstName(user_id, firstName);
        
        if (pass == true) {
        	ctx.status(200);
        }


        System.out.println(ctx.body());
        
        ctx.json("updated");
        //System.out.println(reimID);
    };
    
    
    /**
     * Updates user lastname in databse
     *
     * @author Justin Kroh
     * */
    public static Handler updateUserLastName = ctx -> {
        UsersDAO userdao = new UsersDAO();
        
        System.out.println(ctx.body());

        
        String lastName = ctx.formParam("lastName");
        
        int user_id = ctx.sessionAttribute("user_id");

        boolean pass = userdao.updateUserLastName(user_id, lastName);
        
        if (pass == true) {
        	ctx.status(200);
        }

        
        ctx.json("updated");
        //System.out.println(reimID);
    };
    
    
    /**
     * Updates user street address in databse
     *
     * @author Justin Kroh
     * */
    public static Handler updateUserAddress = ctx -> {
        UsersDAO userdao = new UsersDAO();
        
        System.out.println(ctx.body());
        
        String address = ctx.formParam("address");
        
        System.out.println(address);
       
        int user_id = ctx.sessionAttribute("user_id");
        
        boolean pass = userdao.updateUserAddress(user_id, address);
        
        if (pass == true) {
        	ctx.status(200);
        }
        
        ctx.json("updated");
        //System.out.println(reimID);
    };
    
    /**
     * Updates reimbursement status in database
     * PENDING, APPROVED, DENIED
     *
     * @author Justin Kroh
     * */
    public static Handler updateReimbursementStatus = ctx -> {
        UsersDAO userdao = new UsersDAO();
        
        System.out.println(ctx.body());
        
        String status = ctx.formParam("status");
        
        String reimbursement_id = ctx.formParam("reimbursement_id");
        System.out.println(status);
       
        
        int parsedID = Integer.parseInt(reimbursement_id);
        //int user_id = ctx.sessionAttribute("user_id");
        
        boolean pass = userdao.updateReimbursementStatus(parsedID, status);
        
        if (pass == true) {
        	ctx.status(200);
        }

        ctx.json("updated");
        //System.out.println(reimID);
    };
    
    

    
    /**
     * Checks user credentials in DB
     *
     * @author Justin Kroh
     * */
    public static Handler checkCredentials = ctx -> {
        UsersDAO userdao = new UsersDAO();
       
        
        System.out.println(ctx.body() + "this is the body");

        
        String email = ctx.formParam("email");
        
        String password = ctx.formParam("password");
        
        Boolean check = userdao.checkCredentials(email, password);
        
        
        
        if (check == true ) {
        	
        	
        	Users user = userdao.getUserByEmail(email);
        	ctx.status(200);
        	ctx.sessionAttribute("user_id", user.getUser_id());
        	ctx.sessionAttribute("email", email);
        	ctx.sessionAttribute("first_name", user.getFirstName());
        	ctx.sessionAttribute("last_name", user.getLastName());
        	ctx.sessionAttribute("address", user.getAddress());
        	ctx.sessionAttribute("usertype", user.getUser_type());
        	
        	if (user.getUser_type().equals("EMPLOYEE")) {
        		ctx.redirect("employee_home.html");
        	}
        	else ctx.redirect("manager_home.html");
        }
        else ctx.redirect("Login.html");
        
        
        ctx.json(check);
        System.out.println("check");
    };
    
    
    /**
     * Gets all Reimbursements associated with a user
     *
     * @author Justin Kroh
     * */
    public static Handler getUserReimbursements = ctx -> {
    	   
    	int user_id = ctx.sessionAttribute("user_id");
    	
    	UsersDAO userdao = new UsersDAO();
    	
    	List<Reimbursement> reimList = userdao.getUserReimbursements(user_id);
    	
    	if (reimList != null) {
    		ctx.status(200);
    	}
    	
    	ctx.json(reimList);
    	System.out.println(ctx.json(reimList));
    	

    	
    	
    	
    
   // ctx.json(check);
    System.out.println("check");
};



/**
 * gets all pending reimbursements associated with a user
 *
 * @author Justin Kroh
 * */
	public static Handler getUserPendingReimbursements = ctx -> {
	   
		int user_id = ctx.sessionAttribute("user_id");
	
		UsersDAO userdao = new UsersDAO();
	
		List<Reimbursement> reimList = userdao.getUserReimbursementsByStatus(user_id, "PENDING");
	
		if (reimList != null) {
    		ctx.status(200);
    	}
		
		ctx.json(reimList);
		System.out.println(ctx.json(reimList));
	
		// ctx.json(check);
		System.out.println("check");
	};

	
	/**
	 * gets all approved reimbursements associated with a user
	 *
	 * @author Justin Kroh
	 * */
	public static Handler getUserApprovedReimbursements = ctx -> {
		   
		int user_id = ctx.sessionAttribute("user_id");
	
		UsersDAO userdao = new UsersDAO();
	
		List<Reimbursement> reimList = userdao.getUserReimbursementsByStatus(user_id, "APPROVED");
	
		if (reimList != null) {
    		ctx.status(200);
    	}
		
		ctx.json(reimList);
		System.out.println(ctx.json(reimList));
	
		// ctx.json(check);
		System.out.println("check");
	};
	
	
	/**
	 * gets all denied reimbursements associated with a user
	 *
	 * @author Justin Kroh
	 * */
	public static Handler getUserDeniedReimbursements = ctx -> {
		   
		int user_id = ctx.sessionAttribute("user_id");
	
		UsersDAO userdao = new UsersDAO();
	
		List<Reimbursement> reimList = userdao.getUserReimbursementsByStatus(user_id, "DENIED");
	
		if (reimList != null) {
    		ctx.status(200);
    	}
		
		ctx.json(reimList);
		System.out.println(ctx.json(reimList));
	
		// ctx.json(check);
		System.out.println("check");
	};



	/**
	 * gets all  reimbursements associated with a user by their ID
	 *
	 * @author Justin Kroh
	 * */
	public static Handler getUserReimbursementsByID = ctx -> {
	   
		
        UsersDAO userdao = new UsersDAO();
        
        System.out.println(ctx.body());
        
       // String status = ctx.formParam("status");
        
        String user_id = ctx.formParam("user_id");
             
        int parsedID = Integer.parseInt(user_id);

		List<Reimbursement> reimList = userdao.getUserReimbursements(parsedID);
		
		if (reimList != null) {
    		ctx.status(200);
    	}
		
		ctx.json(reimList);
		System.out.println(ctx.json(reimList));
		
		System.out.println("check");
	};
	
	
	
	/**
	 * gets all reimbursements in the database
	 *
	 * @author Justin Kroh
	 * */
	public static Handler getAllReimbursements = ctx -> {
	   
		
        UsersDAO userdao = new UsersDAO();
        
        System.out.println(ctx.body());

		List<Reimbursement> reimList = userdao.getAllReimbursements();
		
		if (reimList != null) {
    		ctx.status(200);
    	}
		
		ctx.json(reimList);
		System.out.println(ctx.json(reimList));
		
		System.out.println("check");
	};


    
	/**
	 * gets all data associated with a user account
	 *
	 * @author Justin Kroh
	 * */
    public static Handler getUserSessionData = ctx -> {
   
        	
        	String email = ctx.sessionAttribute("email");
        	
        	UsersDAO userdao = new UsersDAO();
        	
        	Users user = userdao.getUserByEmail(email);
        	
        	if (user != null) {
        		ctx.status(200);
        	}
        	
        	
        	ctx.json(user);
        	System.out.println(ctx.json(user));
        	
        	System.out.println(user);
        	
        	
        	
        	
        
       // ctx.json(check);
        System.out.println("check");
    };
	
}
