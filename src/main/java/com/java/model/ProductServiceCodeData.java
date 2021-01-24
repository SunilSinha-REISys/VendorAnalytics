package com.java.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/*@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor*/


@Entity
@Table(name ="product_service_code_data",schema = "vendordb")

public class ProductServiceCodeData {

	    @Id
	    @GeneratedValue(strategy= GenerationType.IDENTITY)
	    private Integer pscId;
	    @Column
	    private String pscCode;
	    @Column
	    private String pscName;
	    @Column
	    private String activeInd;
	    @Column
	    private String pscFullName;
	    @Column
	    private String pscInclude;
	    @Column
	    private String pscExclude;
	    @Column
	    private String pscNote;
	    @Column
	    private String activeStartDate;
	    @Column
	    private String activeEndDate;
	    
	  //  @Column(name = "transactionCreatedDate", nullable = false, updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	   
	  //  @Column(columnDefinition = "TIMESTAMP DEFAULT NULL")
	    
	    @Column
	    private String createdDate;
	    @Column
		private String createdUser;
		    @Column
	    private String updatedDate;
		@Column
		private String updatedUser;
	    @Column
	    private String parentPscCode;
	    
	    @Column
	    private String pscLevel_1;
	    @Column
	    private String pscLevel_1_Category;
	    @Column
	    private String psc_Level_2;
	    @Column
	    private String psc_Level_2_Category;
        @Column
	    private String pscCodeDescription;
       
        
        
		public Integer getPscId() {
			return pscId;
		}
		
		public void setPscId(Integer pscId) {
			this.pscId = pscId;
		}
		public String getPscCode() {
			return pscCode;
		}
		public void setPscCode(String pscCode) {
			this.pscCode = pscCode;
		}
		public String getPscName() {
			return pscName;
		}
		public void setPscName(String pscName) {
			this.pscName = pscName;
		}
		public String getActiveInd() {
			return activeInd;
		}
		public void setActiveInd(String activeInd) {
			this.activeInd = activeInd;
		}
		public String getPscFullName() {
			return pscFullName;
		}
		public void setPscFullName(String pscFullName) {
			this.pscFullName = pscFullName;
		}
		public String getPscInclude() {
			return pscInclude;
		}
		public void setPscInclude(String pscInclude) {
			this.pscInclude = pscInclude;
		}
		public String getPscExclude() {
			return pscExclude;
		}
		public void setPscExclude(String pscExclude) {
			this.pscExclude = pscExclude;
		}
		public String getPscNote() {
			return pscNote;
		}
		public void setPscNote(String pscNote) {
			this.pscNote = pscNote;
		}
		public String getActiveStartDate() {
			return activeStartDate;
		}
		public void setActiveStartDate(String activeStartDate) {
			this.activeStartDate = activeStartDate;
		}
		public String getActiveEndDate() {
			return activeEndDate;
		}
		public void setActiveEndDate(String activeEndDate) {
			this.activeEndDate = activeEndDate;
		}
		public String getCreatedDate() {
			return createdDate;
		}
		public void setCreatedDate(String createdDate) {
			this.createdDate = createdDate;
		}
		public String getCreatedUser() {
			return createdUser;
		}
		public void setCreatedUser(String createdUser) {
			this.createdUser = createdUser;
		}
		public String getUpdatedDate() {
			return updatedDate;
		}
		public void setUpdatedDate(String updatedDate) {
			this.updatedDate = updatedDate;
		}
		public String getUpdatedUser() {
			return updatedUser;
		}
		public void setUpdatedUser(String updatedUser) {
			this.updatedUser = updatedUser;
		}
		public String getParentPscCode() {
			return parentPscCode;
		}
		public void setParentPscCode(String parentPscCode) {
			this.parentPscCode = parentPscCode;
		}
		public String getPscLevel_1() {
			return pscLevel_1;
		}
		public void setPscLevel_1(String pscLevel_1) {
			this.pscLevel_1 = pscLevel_1;
		}
		public String getPscLevel_1_Category() {
			return pscLevel_1_Category;
		}
		public void setPscLevel_1_Category(String pscLevel_1_Category) {
			this.pscLevel_1_Category = pscLevel_1_Category;
		}
		public String getPsc_Level_2() {
			return psc_Level_2;
		}
		public void setPsc_Level_2(String psc_Level_2) {
			this.psc_Level_2 = psc_Level_2;
		}
		public String getPsc_Level_2_Category() {
			return psc_Level_2_Category;
		}
		public void setPsc_Level_2_Category(String psc_Level_2_Category) {
			this.psc_Level_2_Category = psc_Level_2_Category;
		}
		public String getPscCodeDescription() {
			return pscCodeDescription;
		}
		public void setPscCodeDescription(String pscCodeDescription) {
			this.pscCodeDescription = pscCodeDescription;
		}
		
		public ProductServiceCodeData() {}
		
		
		public ProductServiceCodeData(Integer pscId, String pscCode, String pscName, String activeInd,
				String pscFullName, String pscInclude, String pscExclude, String pscNote, String activeStartDate,
				String activeEndDate, String createdDate, String createdUser, String updatedDate, String updatedUser,
				String parentPscCode, String pscLevel_1, String pscLevel_1_Category, String psc_Level_2,
				String psc_Level_2_Category, String pscCodeDescription, String vendorAddressCity) {
			super();
			this.pscId = pscId;
			this.pscCode = pscCode;
			this.pscName = pscName;
			this.activeInd = activeInd;
			this.pscFullName = pscFullName;
			this.pscInclude = pscInclude;
			this.pscExclude = pscExclude;
			this.pscNote = pscNote;
			this.activeStartDate = activeStartDate;
			this.activeEndDate = activeEndDate;
			this.createdDate = createdDate;
			this.createdUser = createdUser;
			this.updatedDate = updatedDate;
			this.updatedUser = updatedUser;
			this.parentPscCode = parentPscCode;
			this.pscLevel_1 = pscLevel_1;
			this.pscLevel_1_Category = pscLevel_1_Category;
			this.psc_Level_2 = psc_Level_2;
			this.psc_Level_2_Category = psc_Level_2_Category;
			this.pscCodeDescription = pscCodeDescription;
			
		}
		
		
	
       
    
        
        
}