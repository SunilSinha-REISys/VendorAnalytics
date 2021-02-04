package com.java.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.java.model.ContractsDataAggregatedByVendorNaicsPsc;

@Repository
public interface ContractsDataAggregatedByVendorNaicsPscRepository extends JpaRepository<ContractsDataAggregatedByVendorNaicsPsc, Integer> {   

	List<ContractsDataAggregatedByVendorNaicsPsc> findAllByVendorDunsNumber(Integer vendorDunsNumber);
	

	List<ContractsDataAggregatedByVendorNaicsPsc> findByVendorDunsNumber(Integer vendorDunsNumber);
	
	// List<ContractsDataAggregatedByVendorNaicsPsc> findByProductOrServiceCode(String vendorDunsNumber);
	 
	 @Query(value = "SELECT * FROM vendordb.CONTRACTS_AGGREGATE_DUNS(:vendorDunsNumber);", nativeQuery = true)
	 List<ContractsDataAggregatedByVendorNaicsPsc> findContractsAggreratedDetils( @Param("vendorDunsNumber") Integer vendorDunsNumber);
	 
	 
}

