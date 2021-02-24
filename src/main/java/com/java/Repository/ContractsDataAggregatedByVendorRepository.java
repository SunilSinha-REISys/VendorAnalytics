package com.java.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.java.model.ContractsDataAggregatedByVendor;
import com.java.model.ContractsTransactionData;

public interface ContractsDataAggregatedByVendorRepository  extends  JpaRepository<ContractsDataAggregatedByVendor,Integer>{
	
	public List<ContractsDataAggregatedByVendor> findByVendorDunsNumber(Integer vendorDunsNumber);
}
