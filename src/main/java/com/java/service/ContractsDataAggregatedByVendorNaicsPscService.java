package com.java.service;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.java.model.ContractsDataAggregatedByVendorNaicsPsc;

public interface ContractsDataAggregatedByVendorNaicsPscService {

	ContractsDataAggregatedByVendorNaicsPsc save( ContractsDataAggregatedByVendorNaicsPsc vendorNaicsPsc);
	
	    List<ContractsDataAggregatedByVendorNaicsPsc> findAll();
	    
	    void delete(int id);

	   // VendorNaicsPsc findOne(String username);

	    ContractsDataAggregatedByVendorNaicsPsc findById(int id);
	       List <ContractsDataAggregatedByVendorNaicsPsc> findAllByVendorDunsNumber(Integer id);
	    
	   // VendorNaicsPsc findAllByVendorDunsNumber(int id);
	    
	       ContractsDataAggregatedByVendorNaicsPsc update(ContractsDataAggregatedByVendorNaicsPsc vendorNaicsPsc);
}
