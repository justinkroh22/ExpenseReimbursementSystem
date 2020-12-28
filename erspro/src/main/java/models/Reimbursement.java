package models;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "reimbursement")
public class Reimbursement {

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "reimbursement_id",  columnDefinition="serial primary key")
	private int reimbursement_id;
	
	@Column(name = "user_id")
	private int user_id;
	
	@Column(name = "status")
	private String status;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "amount")
	private float amount;
	
	
	public Reimbursement() {
		
	}


	public int getReimbursement_id() {
		return reimbursement_id;
	}


	public void setReimbursement_id(int reimbursement_id) {
		this.reimbursement_id = reimbursement_id;
	}


	public int getUser_id() {
		return user_id;
	}


	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public float getAmount() {
		return amount;
	}


	public void setAmount(float amount) {
		this.amount = amount;
	}
	
	
	
	
}
