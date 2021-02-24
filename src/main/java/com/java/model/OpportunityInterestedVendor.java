package com.java.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "opportunity_interested_vendor", schema = "vendordb")
public class OpportunityInterestedVendor {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Integer id;
	@Column
	private String noticeId;
	@Column
	private String entityId;
	@Column
	private Date activeDate ;
	
	
	
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNoticeId() {
		return noticeId;
	}
	public void setNoticeId(String noticeId) {
		this.noticeId = noticeId;
	}
	public String getEntityId() {
		return entityId;
	}
	public void setEntityId(String entityId) {
		this.entityId = entityId;
	}
	public Date getActiveDate() {
		return activeDate;
	}
	public void setActiveDate(Date activeDate) {
		this.activeDate = activeDate;
	}
	
	
	public OpportunityInterestedVendor() {}
	public OpportunityInterestedVendor(Integer id, String noticeId, String entityId, Date activeDate) {
		super();
		this.id = id;
		this.noticeId = noticeId;
		this.entityId = entityId;
		this.activeDate = activeDate;
	}
	
	
}
