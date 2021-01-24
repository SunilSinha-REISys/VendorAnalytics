package com.java.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.java.model.ContractsDataAggregatedByVendorNaicsPsc;

@Repository
public interface ContractsDataAggregatedByVendorNaicsPscRepository extends JpaRepository<ContractsDataAggregatedByVendorNaicsPsc, Integer> {   

	List<ContractsDataAggregatedByVendorNaicsPsc> findAllByVendorDunsNumber(Integer vendorDunsNumber);
	
	
}

