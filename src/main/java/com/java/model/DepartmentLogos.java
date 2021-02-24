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
@Data

@Entity
@Table(name ="department_logos",schema = "vendordb")
public class DepartmentLogos {
	 @Id
		@GeneratedValue(strategy= GenerationType.IDENTITY)
		private Integer id;
	 	@Column
	    private Integer fpdsCode;
	    @Column
	    private String name;
	    @Column
	    private String shortName;
	    @Column
	    private String logoUrl;
	    
	    
	    
	    public Integer getId() {
			return id;
		}
		public void setId(Integer id) {
			this.id = id;
		}
		public Integer getFpdsCode() {
			return fpdsCode;
		}
		public void setFpdsCode(Integer fpdsCode) {
			this.fpdsCode = fpdsCode;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getShortName() {
			return shortName;
		}
		public void setShortName(String shortName) {
			this.shortName = shortName;
		}
		public String getLogoUrl() {
			return logoUrl;
		}
		public void setLogoUrl(String logoUrl) {
			this.logoUrl = logoUrl;
		}
		public DepartmentLogos() {}
		public DepartmentLogos(Integer id, Integer fpdsCode, String name, String shortName, String logoUrl) {
			super();
			this.id = id;
			this.fpdsCode = fpdsCode;
			this.name = name;
			this.shortName = shortName;
			this.logoUrl = logoUrl;
		}

	    
	    
	    
}
