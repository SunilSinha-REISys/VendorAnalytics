package com.java.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "opportunity_vendors_pp" ,schema = "vendordb")
public class OpportunityVendorsPp {
	
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer vendorPpId;
    
    @Column
    private String entityId;
    
    @Column
    private String ppCategoryCode;
    
    @Column
    private Integer rating;

	public Integer getVendorPpId() {
		return vendorPpId;
	}

	public void setVendorPpId(Integer vendorPpId) {
		this.vendorPpId = vendorPpId;
	}

	public String getEntityId() {
		return entityId;
	}

	public void setEntityId(String entityId) {
		this.entityId = entityId;
	}

	public String getPpCategoryCode() {
		return ppCategoryCode;
	}

	public void setPpCategoryCode(String ppCategoryCode) {
		this.ppCategoryCode = ppCategoryCode;
	}

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	
	public OpportunityVendorsPp() {}
	
	public OpportunityVendorsPp(Integer vendorPpId,String entityId, String ppCategoryCode, Integer rating) {
		super();
		this.vendorPpId = vendorPpId;
		this.entityId = entityId;
		this.ppCategoryCode = ppCategoryCode;
		this.rating = rating;
	}
    
    
    

}