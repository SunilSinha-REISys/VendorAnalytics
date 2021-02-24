package com.java.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name ="agency_reference_data",schema = "vendordb")
public class AgencyReferenceData {
        @Id
	    @GeneratedValue(strategy= GenerationType.IDENTITY)
	    private Integer orgKey;
	    @Column
	    private String organizationName;
	    @Column
	    private String organizationCode;
	    @Column
	    private String type;
	    @Column
	    private String parentOrgKey;
	    @Column
	    private double fullParentPath;
	    @Column
	    private String fullParentPathName;
	    @Column
	    private String logo;
	    
	    
		public Integer getOrgKey() {
			return orgKey;
		}
		public void setOrgKey(Integer orgKey) {
			this.orgKey = orgKey;
		}
		public String getOrganizationName() {
			return organizationName;
		}
		public void setOrganizationName(String organizationName) {
			this.organizationName = organizationName;
		}
		public String getOrganizationCode() {
			return organizationCode;
		}
		public void setOrganizationCode(String organizationCode) {
			this.organizationCode = organizationCode;
		}
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}
		public String getParentOrgKey() {
			return parentOrgKey;
		}
		public void setParentOrgKey(String parentOrgKey) {
			this.parentOrgKey = parentOrgKey;
		}
		public double getFullParentPath() {
			return fullParentPath;
		}
		public void setFullParentPath(double fullParentPath) {
			this.fullParentPath = fullParentPath;
		}
		public String getFullParentPathName() {
			return fullParentPathName;
		}
		public void setFullParentPathName(String fullParentPathName) {
			this.fullParentPathName = fullParentPathName;
		}
		public String getLogo() {
			return logo;
		}
		public void setLogo(String logo) {
			this.logo = logo;
		}
		
		
		public AgencyReferenceData() {}
		public AgencyReferenceData(Integer orgKey, String organizationName, String organizationCode, String type,
				String parentOrgKey, double fullParentPath, String fullParentPathName, String logo) {
			super();
			this.orgKey = orgKey;
			this.organizationName = organizationName;
			this.organizationCode = organizationCode;
			this.type = type;
			this.parentOrgKey = parentOrgKey;
			this.fullParentPath = fullParentPath;
			this.fullParentPathName = fullParentPathName;
			this.logo = logo;
		}
	    
	    
	
}
