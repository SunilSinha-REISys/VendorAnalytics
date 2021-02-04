package com.java.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

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

}
