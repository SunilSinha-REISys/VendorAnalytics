package com.java.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.Repository.ContractsDataAggregatedByVendorNaicsPscRepository;
import com.java.model.ContractsDataAggregatedByVendorNaicsPsc;
import com.java.service.ContractsDataAggregatedByVendorNaicsPscService;

@Service(value = "contractsDataAggregatedByVendorNaicsPscService")
public class ContractsDataAggregatedByVendorNaicsPscImpl implements ContractsDataAggregatedByVendorNaicsPscService {
	@Autowired
	private ContractsDataAggregatedByVendorNaicsPscRepository vendorNaicsPscRepository;

	public List<ContractsDataAggregatedByVendorNaicsPsc> findAll() {
		List<ContractsDataAggregatedByVendorNaicsPsc> list = new ArrayList<>();
		vendorNaicsPscRepository.findAll().iterator().forEachRemaining(list::add);
		return list;
	}

	@Override
	public void delete(int id) {
		vendorNaicsPscRepository.deleteById(id);
	}

	/*
	 * @Override public ContractsDataAggregatedByVendorNaicsPscService
	 * save(ContractsDataAggregatedByVendorNaicsPscService vendorNaicsPsc) {
	 * 
	 * return vendorNaicsPscRepository.save(vendorNaicsPsc); }
	 */

	@Override
	public ContractsDataAggregatedByVendorNaicsPsc findById(int id) {
		Optional<ContractsDataAggregatedByVendorNaicsPsc> optionalVendorDetails = vendorNaicsPscRepository.findById(id);
		return optionalVendorDetails.isPresent() ? optionalVendorDetails.get() : null;
	}

	public List<ContractsDataAggregatedByVendorNaicsPsc> findAllByVendorDunsNumber(Integer id) {
		List<ContractsDataAggregatedByVendorNaicsPsc> results = new ArrayList<ContractsDataAggregatedByVendorNaicsPsc>();
		results = vendorNaicsPscRepository.findAllByVendorDunsNumber(id);

		return results;

		// return null ;
		// optionalVendorDetails.isPresent() ? optionalVendorDetails.get() : null;
	}

	@Override
	public ContractsDataAggregatedByVendorNaicsPsc update(ContractsDataAggregatedByVendorNaicsPsc vendorNaicsPsc) {
		ContractsDataAggregatedByVendorNaicsPsc vendorNaicsPsc_exist = findById(vendorNaicsPsc.getVendorDunsNumber());
		if (vendorNaicsPsc_exist != null) {

			vendorNaicsPscRepository.save(vendorNaicsPsc);
		}
		return vendorNaicsPsc;
	}

	@Override
	public ContractsDataAggregatedByVendorNaicsPsc save(ContractsDataAggregatedByVendorNaicsPsc vendorNaicsPsc) {
		return vendorNaicsPscRepository.save(vendorNaicsPsc);

	}

}
