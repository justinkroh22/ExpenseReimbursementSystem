package erspro;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;
import java.util.Properties;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import controller.UserController;
import data.UsersDAO;
import io.javalin.http.Context;
import models.Reimbursement;
import models.Users;
import utils.DatabaseConnection;


import static org.mockito.Mockito.*;



public class Tests {


	UsersDAO userdao;
	private Context ctx;


	 @BeforeClass
	 public static void environmentSetup() {
		

	 }
	
	
	
	@Before
	public void setConnection() throws SQLException, IOException {
		System.out.println("before");
		
		ctx = mock(Context.class);
		

	    

	}

	

	
    @Test
    public void testConnection() {
    	userdao = new UsersDAO();
		SessionFactory factory = userdao.getFactory();
		Session session = factory.openSession();
		session.close();
		factory.close();
    	
    	
    }
	
    
    @Test
    public void testGetUserSessionData() throws Exception {
    	when(ctx.sessionAttribute("email")).thenReturn("test");
    	UserController.getUserSessionData.handle(ctx);
    	
        verify(ctx).status(200);

    	System.out.println(ctx);
    }
    

    
    @Test
    public void testGetAllReimbursements() throws Exception {
    	
    	UserController.getAllReimbursements.handle(ctx);
    	
        verify(ctx).status(200);

    	System.out.println(ctx);
    }
    

    @Test
    public void testGetUserReimbursementsByID() throws Exception {
    	when(ctx.formParam("user_id")).thenReturn("4");
    	UserController.getUserReimbursementsByID.handle(ctx);
    	
        verify(ctx).status(200);

    	System.out.println(ctx);
    }
    
    @Test
    public void testGetUserDeniedReimbursements() throws Exception {
    	when(ctx.sessionAttribute("user_id")).thenReturn(4);
    	UserController.getUserDeniedReimbursements.handle(ctx);
    	
        verify(ctx).status(200);

    	System.out.println(ctx);
    }
	
    @Test
    public void testGetUserPendingReimbursements() throws Exception {
    	when(ctx.sessionAttribute("user_id")).thenReturn(4);
    	UserController.getUserPendingReimbursements.handle(ctx);
    	
    	ctx.status(200);
    	System.out.println(ctx);
    }
	
    
    @Test
    public void testGetUserApprovedReimbursements() throws Exception {
    	when(ctx.sessionAttribute("user_id")).thenReturn(4);
    	UserController.getUserApprovedReimbursements.handle(ctx);
    	
        verify(ctx).status(200);

    	System.out.println(ctx);
    }
	
    @Test
    public void testGetUserReimbursements() throws Exception {
    	when(ctx.sessionAttribute("user_id")).thenReturn(4);
    	UserController.getUserReimbursements.handle(ctx);
    	
        verify(ctx).status(200);

    	System.out.println(ctx);
    }
    
    
    @Test
    public void testCheckCredentials() throws Exception {
    	when(ctx.formParam("email")).thenReturn("test");
    	when(ctx.formParam("password")).thenReturn("test");
    	UserController.checkCredentials.handle(ctx);
    	
        verify(ctx).status(200);

    	System.out.println(ctx);
    }
    
    @Test
    public void testUpdateReimbursementStatus() throws Exception {
    	when(ctx.formParam("reimbursement_id")).thenReturn("7");
    	when(ctx.formParam("status")).thenReturn("test");
    	UserController.updateReimbursementStatus.handle(ctx);
    	
        verify(ctx).status(200);

    	System.out.println(ctx);
    }
	
    @Test
    public void testFetchAllUsernames() throws Exception {

    	UserController.fetchAllUsernames.handle(ctx);
    	
        verify(ctx).status(200);

    	System.out.println(ctx);
    }
    
    @Test
    public void testUpdateUserEmail() throws Exception {
    	when(ctx.sessionAttribute("user_id")).thenReturn(6);
    	when(ctx.formParam("email")).thenReturn("test2");
    	
    	UserController.updateUserEmail.handle(ctx);
    	
        verify(ctx).status(200);

    	System.out.println(ctx);
    }
    
    @Test
    public void testUpdateUserPassword() throws Exception {
    	when(ctx.sessionAttribute("user_id")).thenReturn(6);
    	when(ctx.formParam("password")).thenReturn("test2");
    	
    	UserController.updateUserPassword.handle(ctx);
    	
        verify(ctx).status(200);

    	System.out.println(ctx);
    }
    
    @Test
    public void testUpdateUserFirstName() throws Exception {
    	when(ctx.sessionAttribute("user_id")).thenReturn(6);
    	when(ctx.formParam("firstName")).thenReturn("test2");
    	
    	UserController.updateUserFirstName.handle(ctx);
    	
        verify(ctx).status(200);

    	System.out.println(ctx);
    }
    
    @Test
    public void testUpdateUserLastName() throws Exception {
    	when(ctx.sessionAttribute("user_id")).thenReturn(6);
    	when(ctx.formParam("lastName")).thenReturn("test2");
    	
    	UserController.updateUserLastName.handle(ctx);
    	
        verify(ctx).status(200);

    	System.out.println(ctx);
    }
    
    @Test
    public void testUpdateUserAddress() throws Exception {
    	when(ctx.sessionAttribute("user_id")).thenReturn(6);
    	when(ctx.formParam("address")).thenReturn("test2");
    	
    	UserController.updateUserAddress.handle(ctx);
    	
        verify(ctx).status(200);

    	System.out.println(ctx);
    }
    
    /*
    @Test
    public void testUpdateUserStatus() throws Exception {
    	when(ctx.sessionAttribute("user_id")).thenReturn(6);
    	when(ctx.formParam("user_type")).thenReturn("test2");
    	
    	UserController.updateUserType.handle(ctx);
    	
        verify(ctx).status(200);

    	System.out.println(ctx);
    }
    */
    
    @After
    public void cleanup() {
        System.out.println("After");
        
    }

    @AfterClass
    public static void envCleanup() {
        System.out.println("After Class");
       
    }


	
}