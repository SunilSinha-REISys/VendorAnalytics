package com.java.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "contracts_data_aggregated_by_vendor_naics_psc" ,schema = "vendordb")

public class ContractsDataAggregatedByVendorNaicsPsc {
	    @Id
	    @GeneratedValue(strategy= GenerationType.IDENTITY)
	    private int vendorDunsNumber;
	   @Column
	    private String naicsCode;
	    @Column
	    private String productOrServiceCode;
	    @Column
	    private String vendorName;
	    @Column
	    private String naicsDescription;
	    @Column
	    private String productOrServiceDescription;
	    @Column
	    private String vendorAddressLine_1;
	    @Column
	    private String vendorAddressCity;
	    @Column
	    private String vendorAddressStateName;
	    @Column
	    private String vendorAddressZipCode;
	    @Column
	    private double totalObligatedAmount;
	    @Column
	    private int totalNumberOfActions;
		
	    
	    public ContractsDataAggregatedByVendorNaicsPsc() {}
	    
	   

		public ContractsDataAggregatedByVendorNaicsPsc(int vendorDunsNumber, String naicsCode,
				String productOrServiceCode, String vendorName, String naicsDescription,
				String productOrServiceDescription, String vendorAddressLine_1, String vendorAddressCity,
				String vendorAddressStateName, String vendorAddressZipCode, double totalObligatedAmount,
				int totalNumberOfActions) {
			super();
			this.vendorDunsNumber = vendorDunsNumber;
			this.naicsCode = naicsCode;
			this.productOrServiceCode = productOrServiceCode;
			this.vendorName = vendorName;
			this.naicsDescription = naicsDescription;
			this.productOrServiceDescription = productOrServiceDescription;
			this.vendorAddressLine_1 = vendorAddressLine_1;
			this.vendorAddressCity = vendorAddressCity;
			this.vendorAddressStateName = vendorAddressStateName;
			this.vendorAddressZipCode = vendorAddressZipCode;
			this.totalObligatedAmount = totalObligatedAmount;
			this.totalNumberOfActions = totalNumberOfActions;
		}



		public int getVendorDunsNumber() {
			return vendorDunsNumber;
		}
		public void setVendorDunsNumber(int vendorDunsNumber) {
			this.vendorDunsNumber = vendorDunsNumber;
		}
		public String getNaicsCode() {
			return naicsCode;
		}
		public void setNaicsCode(String naicsCode) {
			this.naicsCode = naicsCode;
		}
		public String getProductOrServiceCode() {
			return productOrServiceCode;
		}
		public void setProductOrServiceCode(String productOrServiceCode) {
			this.productOrServiceCode = productOrServiceCode;
		}
		public String getVendorName() {
			return vendorName;
		}
		public void setVendorName(String vendorName) {
			this.vendorName = vendorName;
		}
		public String getNaicsDescription() {
			return naicsDescription;
		}
		public void setNaicsDescription(String naicsDescription) {
			this.naicsDescription = naicsDescription;
		}
		public String getProductOrServiceDescription() {
			return productOrServiceDescription;
		}
		public void setProductOrServiceDescription(String productOrServiceDescription) {
			this.productOrServiceDescription = productOrServiceDescription;
		}
		public String getVendorAddressLine_1() {
			return vendorAddressLine_1;
		}
		public void setVendorAddressLine_1(String vendorAddressLine_1) {
			this.vendorAddressLine_1 = vendorAddressLine_1;
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
		public String getVendorAddressZipCode() {
			return vendorAddressZipCode;
		}
		public void setVendorAddressZipCode(String vendorAddressZipCode) {
			this.vendorAddressZipCode = vendorAddressZipCode;
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
