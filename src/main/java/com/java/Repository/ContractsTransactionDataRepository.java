package com.java.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.java.model.ContractsTransactionData;

@Repository
public interface ContractsTransactionDataRepository extends JpaRepository<ContractsTransactionData, Long> {   

	public List<ContractsTransactionData> findByVendorDunsNumber(Integer vendorDunsNumber);
	
}
