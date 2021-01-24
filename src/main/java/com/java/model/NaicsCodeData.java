package com.java.model;

import java.sql.Timestamp;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "naics_code_data", schema = "vendordb")
public class NaicsCodeData {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long naicsId;
	@Column
	private String naicsCode;

	@Column
	private String naicsTitle;

	@Column
	private String activeInd;

	@Column
	private Integer sourceYear;

	@Column
	private Date activeStartDate;
	@Column
	private Date activeEndDate;
	@Column
	private Timestamp createdDate;
	@Column
	private String createdUser;
	@Column
	private Timestamp updatedDate;
	@Column
	private Integer naicsSize;
	@Column
	private String parentNaicsCode;

	public long getNaicsId() {
		return naicsId;
	}

	public void setNaicsId(long naicsId) {
		this.naicsId = naicsId;
	}

	public String getNaicsCode() {
		return naicsCode;
	}

	public void setNaicsCode(String naicsCode) {
		this.naicsCode = naicsCode;
	}

	public String getNaicsTitle() {
		return naicsTitle;
	}

	public void setNaicsTitle(String naicsTitle) {
		this.naicsTitle = naicsTitle;
	}

	public String getActiveInd() {
		return activeInd;
	}

	public void setActiveInd(String activeInd) {
		this.activeInd = activeInd;
	}

	public Integer getSourceYear() {
		return sourceYear;
	}

	public void setSourceYear(Integer sourceYear) {
		this.sourceYear = sourceYear;
	}

	public Date getActiveStartDate() {
		return activeStartDate;
	}

	public void setActiveStartDate(Date activeStartDate) {
		this.activeStartDate = activeStartDate;
	}

	public Date getActiveEndDate() {
		return activeEndDate;
	}

	public void setActiveEndDate(Date activeEndDate) {
		this.activeEndDate = activeEndDate;
	}

	public Timestamp getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}

	public String getCreatedUser() {
		return createdUser;
	}

	public void setCreatedUser(String createdUser) {
		this.createdUser = createdUser;
	}

	public Timestamp getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Timestamp updatedDate) {
		this.updatedDate = updatedDate;
	}

	public Integer getNaicsSize() {
		return naicsSize;
	}

	public void setNaicsSize(Integer naicsSize) {
		this.naicsSize = naicsSize;
	}

	public String getParentNaicsCode() {
		return parentNaicsCode;
	}

	public void setParentNaicsCode(String parentNaicsCode) {
		this.parentNaicsCode = parentNaicsCode;
	}

	public NaicsCodeData() {
	}

	public NaicsCodeData(long naicsId, String naicsCode, String naicsTitle, String activeInd, Integer sourceYear,
			Date activeStartDate, Date activeEndDate, Timestamp createdDate, String createdUser, Timestamp updatedDate,
			Integer naicsSize, String parentNaicsCode) {
		super();
		this.naicsId = naicsId;
		this.naicsCode = naicsCode;
		this.naicsTitle = naicsTitle;
		this.activeInd = activeInd;
		this.sourceYear = sourceYear;
		this.activeStartDate = activeStartDate;
		this.activeEndDate = activeEndDate;
		this.createdDate = createdDate;
		this.createdUser = createdUser;
		this.updatedDate = updatedDate;
		this.naicsSize = naicsSize;
		this.parentNaicsCode = parentNaicsCode;
	}

	/*
	 * naics_id INTEGER NOT NULL , naics_code VARCHAR Default NULL ,naics_title
	 * VARCHAR ( 150 ),active_ind VARCHAR,source_year integer NOT NULL ,
	 * active_start_date date,active_end_date date, created_date timestamp ,
	 * created_user VARCHAR ( 150 ) , updated_date timestamp , updated_user VARCHAR
	 * ( 150 ) , naics_size INTEGER ,parent_naics_code VARCHAR default null,
	 * CONSTRAINT "naics_code_data_pkey" PRIMARY KEY (naics_id)
	 */

}
