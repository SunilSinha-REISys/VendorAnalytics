package com.java.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//@Data
//@AllArgsConstructor
//@NoArgsConstructor

@Entity
@Table(name = "contracts_data_aggregated_by_vendor" ,schema = "vendordb")
public class ContractsDataAggregatedByVendor {
	    @Id
		@GeneratedValue(strategy= GenerationType.IDENTITY)
		private Integer id;
	    @Column
	    private Integer vendorDunsNumber;
	    @Column
	    private String awardFiscalYear;
	    @Column
	    private String vendorName;
	    @Column
	    private String vendorAddress;
	    @Column
	    private String vendorAddressCity;
	    @Column
	    private String vendorAddressStateName;
	    @Column
	    private String vendorAddressZip;
	    @Column
	    private double totalObligatedAmount;
	    @Column
	    private int totalNumberOfActions;
	    
	
	
public ContractsDataAggregatedByVendor() {}



public ContractsDataAggregatedByVendor(Integer id, Integer vendorDunsNumber, String awardFiscalYear, String vendorName,
		String vendorAddress, String vendorAddressCity, String vendorAddressStateName, String vendorAddressZip,
		double totalObligatedAmount, int totalNumberOfActions) {
	super();
	this.id = id;
	this.vendorDunsNumber = vendorDunsNumber;
	this.awardFiscalYear = awardFiscalYear;
	this.vendorName = vendorName;
	this.vendorAddress = vendorAddress;
	this.vendorAddressCity = vendorAddressCity;
	this.vendorAddressStateName = vendorAddressStateName;
	this.vendorAddressZip = vendorAddressZip;
	this.totalObligatedAmount = totalObligatedAmount;
	this.totalNumberOfActions = totalNumberOfActions;
}



public Integer getId() {
	return id;
}



public void setId(Integer id) {
	this.id = id;
}



public Integer getVendorDunsNumber() {
	return vendorDunsNumber;
}



public void setVendorDunsNumber(Integer vendorDunsNumber) {
	this.vendorDunsNumber = vendorDunsNumber;
}



public String getAwardFiscalYear() {
	return awardFiscalYear;
}



public void setAwardFiscalYear(String awardFiscalYear) {
	this.awardFiscalYear = awardFiscalYear;
}



public String getVendorName() {
	return vendorName;
}



public void setVendorName(String vendorName) {
	this.vendorName = vendorName;
}



public String getVendorAddress() {
	return vendorAddress;
}



public void setVendorAddress(String vendorAddress) {
	this.vendorAddress = vendorAddress;
}



public String getVendorAddressCity() {
	return vendorAddressCity;
}



public void setVendorAddressCity(String vendorAddressCity) {
	this.vendorAddressCity = vendorAddressCity;
}



public String getVendorAddressStateName() {
	return vendorAddressStateName;
}



public void setVendorAddressStateName(String vendorAddressStateName) {
	this.vendorAddressStateName = vendorAddressStateName;
}



public String getVendorAddressZip() {
	return vendorAddressZip;
}



public void setVendorAddressZip(String vendorAddressZip) {
	this.vendorAddressZip = vendorAddressZip;
}



public double getTotalObligatedAmount() {
	return totalObligatedAmount;
}



public void setTotalObligatedAmount(double totalObligatedAmount) {
	this.totalObligatedAmount = totalObligatedAmount;
}



public int getTotalNumberOfActions() {
	return totalNumberOfActions;
}



public void setTotalNumberOfActions(int totalNumberOfActions) {
	this.totalNumberOfActions = totalNumberOfActions;
}	


}
