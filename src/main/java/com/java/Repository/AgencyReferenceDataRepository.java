package com.java.Repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.java.model.AgencyReferenceData;
import com.java.model.OpportunityInterestedVendor;

public interface AgencyReferenceDataRepository extends JpaRepository<AgencyReferenceData,Integer> {

	Page<AgencyReferenceData> findByParentOrgKey(String parentOrgKey,Pageable paging);
	List<AgencyReferenceData> findByOrganizationCode(String agencyCode);
	
	List<AgencyReferenceData> findByParentOrgKey(String parentOrgKey);
	
	
	@Query(value = " SELECT  * FROM vendordb.agency_reference_data where  parent_org_key IN(select CAST (org_key AS  varchar) "
			+ " FROM vendordb.agency_reference_data   where   organization_name = :organizationName AND parent_org_key= 'NULL') ", nativeQuery = true)
	public  List<AgencyReferenceData> findByOrganizationName(@Param("organizationName") String organizationName  ) ;

	
//	@Query(value = "SELECT * FROM vendordb.agency_reference_data u WHERE u.organization_name = :organizationName and u.type = :type", nativeQuery = true)
	AgencyReferenceData findByOrganizationNameAndType( @Param("organizationName") String organizationName, @Param("type") String type);

}
