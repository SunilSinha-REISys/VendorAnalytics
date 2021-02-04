package com.java.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.java.model.AgencyReferenceData;
import com.java.model.ContractsTransactionData;

@Repository
public interface ContractsTransactionDataRepository extends JpaRepository<ContractsTransactionData, Long> {   

	public List<ContractsTransactionData> findByVendorDunsNumber(Integer vendorDunsNumber);
	
	public List<ContractsTransactionData> findByAgencyCode(String agencyCode);
	
	Page<List<ContractsTransactionData>> findByAgencyCode(String agencyCode,Pageable paging);

	

	
}
