package com.java.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.Repository.ContractsTransactionDataRepository;
import com.java.service.ContractsTransactionDataService;

@Service(value = "contractsTransactionDataService")
public class ContractsTransactionDataServiceImp implements ContractsTransactionDataService {

	@Autowired
	private ContractsTransactionDataRepository contractsTransactionDataRepository;

	/*
	 * @Override public List<ContractsTransactionData>
	 * findByVendorDunsNumber(Integer id) {
	 * 
	 * System.out.println("------id------"+id); List<ContractsTransactionData> list
	 * = new ArrayList<>();
	 * contractsTransactionDataRepository.findByVendorDunsNumber(id).iterator().
	 * forEachRemaining(list ::add);
	 * 
	 * //return list;
	 * 
	 * return null ;
	 * 
	 * }
	 */

}