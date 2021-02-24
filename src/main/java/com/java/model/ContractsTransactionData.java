package com.java.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonInclude;

@Entity
@Table(name = "contracts_transaction_data" ,schema = "vendordb")
public class ContractsTransactionData {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private  long awardIdvId	;
	@Column
	private String agencyCode;
	@Column
	private String piid;
	@Column
	private String modificationNumber	;
	@Column
	private Integer  transactionNumber;	
	@Column
	private Integer vendorDunsNumber;
	@Column
	private String vendorName;
	 @Column
	 private Integer naicsCode;
	
	 @Column
	  private String naicsDescription;
	
	 @Column
	 private String  productOrServiceCode;
	 
	 @Column
	 private String productOrServiceDescription;
	 
	 @Column
	 private String  dateSigned ;
	 @Column
	 private String contractingDepartmentName ;
	 @Column
	 private String contractingAgencyName;
	 @Column
	 private String fundingDepartmentName ;
	 @Column
	 private String fundingAgencyName ;
	 @Column
	 private String contractDescription ;
	 @Column
	 private String programAcronym ;
	 @Column
	 private String contractPricingDescription ;
	 @Column
	 private String awardIdvStatus ;	
	 @Column
	 private String awardIdvType ; 
	 @Column
	 private String typeOfAgreement;	
	 @Column
	 private String typeOfSetAsideDescription ;	
	 @Column
	 private String organizationalType	;
		
	 @Column
	 private String nationalInterestDescription;
		
	 @Column
	 private String vendorExceptiionDesc;
		
    @Column
    private String vendorAddressLine_1;
    @Column
    private String vendorAddressCity;
    @Column
    private String vendorAddressStateName;
    @Column
    private String vendorAddressZipCode;
   
    @Column
    private double dollarsObligated;
    @Column
    private  Integer numberOfActions;
    
    @Column
    private Date ultimateCompletionDate ;
    
    
    @JsonInclude()
    @Transient
    private double averageRating;
    
    public long getAwardIdvId() {
		return awardIdvId;
	}
	public void setAwardIdvId(long awardIdvId) {
		this.awardIdvId = awardIdvId;
	}
	public String getAgencyCode() {
		return agencyCode;
	}
	public void setAgencyCode(String agencyCode) {
		this.agencyCode = agencyCode;
	}
	public String getPiid() {
		return piid;
	}
	public void setPiid(String piid) {
		this.piid = piid;
	}
	public String getModificationNumber() {
		return modificationNumber;
	}
	public void setModificationNumber(String modificationNumber) {
		this.modificationNumber = modificationNumber;
	}
	public Integer getTransactionNumber() {
		return transactionNumber;
	}
	public void setTransactionNumber(Integer transactionNumber) {
		this.transactionNumber = transactionNumber;
	}
	public Integer getVendorDunsNumber() {
		return vendorDunsNumber;
	}
	public void setVendorDunsNumber(Integer vendorDunsNumber) {
		this.vendorDunsNumber = vendorDunsNumber;
	}
	public String getVendorName() {
		return vendorName;
	}
	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}
	public Integer getNaicsCode() {
		return naicsCode;
	}
	public void setNaicsCode(Integer naicsCode) {
		this.naicsCode = naicsCode;
	}
	public String getNaicsDescription() {
		return naicsDescription;
	}
	public void setNaicsDescription(String naicsDescription) {
		this.naicsDescription = naicsDescription;
	}
	public String getProductOrServiceCode() {
		return productOrServiceCode;
	}
	public void setProductOrServiceCode(String productOrServiceCode) {
		this.productOrServiceCode = productOrServiceCode;
	}
	public String getProductOrServiceDescription() {
		return productOrServiceDescription;
	}
	public void setProductOrServiceDescription(String productOrServiceDescription) {
		this.productOrServiceDescription = productOrServiceDescription;
	}
	public String getDateSigned() {
		return dateSigned;
	}
	public void setDateSigned(String dateSigned) {
		this.dateSigned = dateSigned;
	}
	public String getContractingDepartmentName() {
		return contractingDepartmentName;
	}
	public void setContractingDepartmentName(String contractingDepartmentName) {
		this.contractingDepartmentName = contractingDepartmentName;
	}
	public String getContractingAgencyName() {
		return contractingAgencyName;
	}
	public void setContractingAgencyName(String contractingAgencyName) {
		this.contractingAgencyName = contractingAgencyName;
	}
	public String getFundingDepartmentName() {
		return fundingDepartmentName;
	}
	public void setFundingDepartmentName(String fundingDepartmentName) {
		this.fundingDepartmentName = fundingDepartmentName;
	}
	public String getFundingAgencyName() {
		return fundingAgencyName;
	}
	public void setFundingAgencyName(String fundingAgencyName) {
		this.fundingAgencyName = fundingAgencyName;
	}
	public String getContractDescription() {
		return contractDescription;
	}
	public void setContractDescription(String contractDescription) {
		this.contractDescription = contractDescription;
	}
	public String getProgramAcronym() {
		return programAcronym;
	}
	public void setProgramAcronym(String programAcronym) {
		this.programAcronym = programAcronym;
	}
	public String getContractPricingDescription() {
		return contractPricingDescription;
	}
	public void setContractPricingDescription(String contractPricingDescription) {
		this.contractPricingDescription = contractPricingDescription;
	}
	public String getAwardIdvStatus() {
		return awardIdvStatus;
	}
	public void setAwardIdvStatus(String awardIdvStatus) {
		this.awardIdvStatus = awardIdvStatus;
	}
	public String getAwardIdvType() {
		return awardIdvType;
	}
	public void setAwardIdvType(String awardIdvType) {
		this.awardIdvType = awardIdvType;
	}
	public String getTypeOfAgreement() {
		return typeOfAgreement;
	}
	public void setTypeOfAgreement(String typeOfAgreement) {
		this.typeOfAgreement = typeOfAgreement;
	}
	public String getTypeOfSetAsideDescription() {
		return typeOfSetAsideDescription;
	}
	public void setTypeOfSetAsideDescription(String typeOfSetAsideDescription) {
		this.typeOfSetAsideDescription = typeOfSetAsideDescription;
	}
	public String getOrganizationalType() {
		return organizationalType;
	}
	public void setOrganizationalType(String organizationalType) {
		this.organizationalType = organizationalType;
	}
	public String getNationalInterestDescription() {
		return nationalInterestDescription;
	}
	public void setNationalInterestDescription(String nationalInterestDescription) {
		this.nationalInterestDescription = nationalInterestDescription;
	}
	public String getVendorExceptiionDesc() {
		return vendorExceptiionDesc;
	}
	public void setVendorExceptiionDesc(String vendorExceptiionDesc) {
		this.vendorExceptiionDesc = vendorExceptiionDesc;
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
	public double getDollarsObligated() {
		return dollarsObligated;
	}
	public void setDollarsObligated(double dollarsObligated) {
		this.dollarsObligated = dollarsObligated;
	}
	public Integer getNumberOfActions() {
		return numberOfActions;
	}
	public void setNumberOfActions(Integer numberOfActions) {
		this.numberOfActions = numberOfActions;
	}
	
	
	public Date getUltimateCompletionDate() {
		return ultimateCompletionDate;
	}
	public void setUltimateCompletionDate(Date ultimateCompletionDate) {
		this.ultimateCompletionDate = ultimateCompletionDate;
	}
	public ContractsTransactionData() {}
	public ContractsTransactionData(long awardIdvId, String agencyCode, String piid, String modificationNumber,
			Integer transactionNumber, Integer vendorDunsNumber, String vendorName, Integer naicsCode,
			String naicsDescription, String productOrServiceCode, String productOrServiceDescription, String dateSigned,
			String contractingDepartmentName, String contractingAgencyName, String fundingDepartmentName,
			String fundingAgencyName, String contractDescription, String programAcronym,
			String contractPricingDescription, String awardIdvStatus, String awardIdvType, String typeOfAgreement,
			String typeOfSetAsideDescription, String organizationalType, String nationalInterestDescription,
			String vendorExceptiionDesc, String vendorAddressLine_1, String vendorAddressCity,
			String vendorAddressStateName, String vendorAddressZipCode, double dollarsObligated,
			Integer numberOfActions, double averageRating ,Date ultimateCompletionDate) {
		super();
		this.awardIdvId = awardIdvId;
		this.agencyCode = agencyCode;
		this.piid = piid;
		this.modificationNumber = modificationNumber;
		this.transactionNumber = transactionNumber;
		this.vendorDunsNumber = vendorDunsNumber;
		this.vendorName = vendorName;
		this.naicsCode = naicsCode;
		this.naicsDescription = naicsDescription;
		this.productOrServiceCode = productOrServiceCode;
		this.productOrServiceDescription = productOrServiceDescription;
		this.dateSigned = dateSigned;
		this.contractingDepartmentName = contractingDepartmentName;
		this.contractingAgencyName = contractingAgencyName;
		this.fundingDepartmentName = fundingDepartmentName;
		this.fundingAgencyName = fundingAgencyName;
		this.contractDescription = contractDescription;
		this.programAcronym = programAcronym;
		this.contractPricingDescription = contractPricingDescription;
		this.awardIdvStatus = awardIdvStatus;
		this.awardIdvType = awardIdvType;
		this.typeOfAgreement = typeOfAgreement;
		this.typeOfSetAsideDescription = typeOfSetAsideDescription;
		this.organizationalType = organizationalType;
		this.nationalInterestDescription = nationalInterestDescription;
		this.vendorExceptiionDesc = vendorExceptiionDesc;
		this.vendorAddressLine_1 = vendorAddressLine_1;
		this.vendorAddressCity = vendorAddressCity;
		this.vendorAddressStateName = vendorAddressStateName;
		this.vendorAddressZipCode = vendorAddressZipCode;
		this.dollarsObligated = dollarsObligated;
		this.numberOfActions = numberOfActions;
		this.averageRating = averageRating;
		this.ultimateCompletionDate = ultimateCompletionDate ;
	}
	public double getAverageRating() {
		return averageRating;
	}
	public void setAverageRating(double averageRating) {
		this.averageRating = averageRating;
	}
	
	
	
	
	
	/*
	 * @Column private String pragencyCode;
	 * 
	 * @Column private double totalObligatedAmount;
	 * 
	 * @Column private Integer totalNumberOfActions;
	 */
    
    
    
    
}