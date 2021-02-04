package com.java.Repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.java.model.AgencyReferenceData;

public interface AgencyReferenceDataRepository extends JpaRepository<AgencyReferenceData,Integer> {

	Page<AgencyReferenceData> findByParentOrgKey(String parentOrgKey,Pageable paging);
	List<AgencyReferenceData> findByOrganizationCode(String agencyCode);
	
	List<AgencyReferenceData> findByParentOrgKey(String parentOrgKey);

}
