package models;

import models.PostgreSQLEnumType;
import javax.persistence.*;

import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import java.util.Set;

@Entity
@Table(name = "users")
public class Users {

	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "user_id",  columnDefinition="serial primary key")
    private int user_id;
	
	@Column(name = "first_name")
    private String firstName;
    
	@Column(name = "last_name")
    private String lastName;
	
	@Column(name = "email")
    private String email;
    
	@Column(name = "password")
    private String password;
	
	@Column(name = "address")
    private String address;
	
	@Column(name = "user_type")
    private String user_type;
	
	
    

	public Users() {
		
	}
	


	public int getUser_id() {
		return user_id;
	}


	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}



	public String getUser_type() {
		return user_type;
	}



	public void setUser_type(String user_type) {
		this.user_type = user_type;
	}





    
	
	
    
    
	
}
