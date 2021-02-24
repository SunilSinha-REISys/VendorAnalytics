package com.java.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name ="small_business_gaol",schema = "vendordb")
public class SmallBusinessGoal {
	
	    @Id
	    @GeneratedValue(strategy= GenerationType.IDENTITY)
	    private Integer id;
	    @Column
	    private String organizationCode;
	    @Column
	    private double targetAmount;
	    
	   public SmallBusinessGoal(){}
	   public SmallBusinessGoal(Integer id, String organizationCode, double targetAmount) {
		super();
		this.id = id;
		this.organizationCode = organizationCode;
		this.targetAmount = targetAmount;
	   }


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getOrganizationCode() {
		return organizationCode;
	}

	public void setOrganizationCode(String organizationCode) {
		this.organizationCode = organizationCode;
	}

	public double getTargetAmount() {
		return targetAmount;
	}

	public void setTargetAmount(double targetAmount) {
		this.targetAmount = targetAmount;
	} 
	   
	
}
