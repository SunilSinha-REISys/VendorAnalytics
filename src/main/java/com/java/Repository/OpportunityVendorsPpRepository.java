package com.java.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.java.model.OpportunityVendorsPp;

public interface OpportunityVendorsPpRepository extends JpaRepository<OpportunityVendorsPp,Integer> {
	
	List<OpportunityVendorsPp> findAllByEntityId(String vendorDunsNumber);
	
	List<OpportunityVendorsPp> findByEntityId(String vendorDunsNumber);
	
	

}
