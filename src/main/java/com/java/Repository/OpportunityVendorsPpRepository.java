package com.java.Repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.java.model.OpportunityVendorsPp;

public interface OpportunityVendorsPpRepository extends JpaRepository<OpportunityVendorsPp,Integer> {
	
	List<OpportunityVendorsPp> findAllByEntityId(String vendorDunsNumber);
	
	List<OpportunityVendorsPp> findByEntityId(String vendorDunsNumber);

	
	@Query(value = "SELECT AVG(rating) FROM vendordb.opportunity_vendors_pp u WHERE u.entity_id = :vendorDunsNumber  AND pp_category_code !='REGULATORY_RATING'  GROUP BY  entity_id ", nativeQuery = true)
	  Double findAverageByVendorDunsNumber(@Param("vendorDunsNumber") String vendorDunsNumber); 
	
	
	
}
