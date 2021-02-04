package com.java.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.java.Repository.ContractsDataAggregatedByVendorNaicsPscRepository;
import com.java.Repository.ContractsTransactionDataRepository;
import com.java.Repository.NaicsCodeDataRepository;
import com.java.Repository.OpportunityVendorsPpRepository;
import com.java.Repository.ProductServiceCodeDataRepository;
import com.java.model.ContractsDataAggregatedByVendorNaicsPsc;
import com.java.model.ContractsTransactionData;
import com.java.model.NaicsCodeData;
import com.java.model.OpportunityVendorsPp;
import com.java.model.ProductServiceCodeData;

@RestController
@RequestMapping("/api")

@CrossOrigin(origins = "*", maxAge = 3600)
public class VendorDashaboardController {
	@Autowired
	private ContractsDataAggregatedByVendorNaicsPscRepository contractsDataAggregatedByVendorNaicsPscRepository;
	@Autowired
	NaicsCodeDataRepository naicsCodeDataRepository;

	@Autowired
	ContractsTransactionDataRepository contractsTransactionDataRepository;

	@Autowired
	public ProductServiceCodeDataRepository productServiceCodeDataRepository;
	@Autowired
	public OpportunityVendorsPpRepository opportunityVendorsPpRepository;

	public List<NaicsCodeData> naicsCodeDataList = new ArrayList<NaicsCodeData>();

	public List<NaicsCodeData> naicsCodeDataListAll = new ArrayList<NaicsCodeData>();

	public List<ProductServiceCodeData> productServiceCodeDataList = new ArrayList<ProductServiceCodeData>();
	public List<ProductServiceCodeData> pscCodeDataListAll = new ArrayList<ProductServiceCodeData>();

	public List<ContractsDataAggregatedByVendorNaicsPsc> vendorList = new ArrayList<ContractsDataAggregatedByVendorNaicsPsc>();

	public List<List<ContractsDataAggregatedByVendorNaicsPsc>> vendorNaicsPscListAll = new ArrayList<List<ContractsDataAggregatedByVendorNaicsPsc>>();

	public List<ContractsTransactionData> contractsTransactionDataList = new ArrayList<ContractsTransactionData>();

	public List<OpportunityVendorsPp> ratingList = new ArrayList<OpportunityVendorsPp>();

	// all vendors
	//--------------------------------------------------------------------------------------------------------
	@GetMapping("/vendors")
	public ResponseEntity<Map<String, Object>> getVendors(@RequestParam(defaultValue = "0") Integer pageNo,
			@RequestParam(defaultValue = "10") Integer pageSize, @RequestParam(required = false) String title) {

		try {
			List<ContractsDataAggregatedByVendorNaicsPsc> vendorNaicsPscList = new ArrayList<ContractsDataAggregatedByVendorNaicsPsc>();
			Pageable paging = PageRequest.of(pageNo, pageSize);
			Page<ContractsDataAggregatedByVendorNaicsPsc> pageVendorNaicsPsc;
			pageVendorNaicsPsc = contractsDataAggregatedByVendorNaicsPscRepository.findAll(paging);
			vendorNaicsPscList = pageVendorNaicsPsc.getContent();
			Map<String, Object> response = new HashMap<>();
			response.put("vendors", vendorNaicsPscList);
			response.put("currentPage", pageVendorNaicsPsc.getNumber());
			response.put("totalItems", pageVendorNaicsPsc.getTotalElements());
			response.put("totalPages", pageVendorNaicsPsc.getTotalPages());
			vendorNaicsPscList = null;

			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// vendor based on duns
	// number--------------------------------------------------------------------------------------------
	@GetMapping("/vendor/{id}")
	public ResponseEntity<Map<String, Object>> getVendor(@PathVariable Integer id) {

		try {
			vendorList = null;
			vendorList = contractsDataAggregatedByVendorNaicsPscRepository.findByVendorDunsNumber(id);

			// count total award contract transcation data
			contractsTransactionDataList = contractsTransactionDataRepository.findByVendorDunsNumber(id);
			Map<Integer, Long> countTotalAwards = contractsTransactionDataList.stream().collect(
					Collectors.groupingBy(ContractsTransactionData::getVendorDunsNumber, Collectors.counting()));
			// List<String> contractAgencyName =
			// contractsTransactionDataList.stream().filter(e ->
			// e.getVendorDunsNumber().equals(id)).map(ContractsTransactionData::getContractingAgencyName).collect(Collectors.toList());
			// contractAgencyName = contractAgencyName.stream().filter( distinctByKey(p
			// ->p.toString()) ).collect(Collectors.toList());

			// generate Response
			vendorList = vendorList.stream().limit(1).collect(Collectors.toList());
			Map<String, Object> response = new HashMap<>();
			response.put("vendors", vendorList);
			// response.put("agencyName", contractAgencyName);
			response.put("totalAward", countTotalAwards.get(id));

			vendorList = null;
			return new ResponseEntity<>(response, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// rating list based on duns number
	// ---------------------------------------------------------------------------------------
	@GetMapping("/vendor/rating/{id}")
	public ResponseEntity<Map<String, Object>> getVendorRating(@PathVariable Integer id) {
		try {

			String vendorDunsNumberStr = Integer.toString(id);
			ratingList = opportunityVendorsPpRepository.findByEntityId(vendorDunsNumberStr);

			// generate Response
			Map<String, Object> response = new HashMap<>();
			response.put("rating", ratingList);
			ratingList = null;
			return new ResponseEntity<>(response, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	//naics and  psc top  5 ---------------------------------------------------------------------
	
	@GetMapping("/vendor/top/{id}")
	public ResponseEntity<Map<String, Object>> getNAICSPSC(@PathVariable Integer id) {

		try {
			vendorList = null ;
			   vendorList = contractsDataAggregatedByVendorNaicsPscRepository.findByVendorDunsNumber(id) ;
		      // fetching naics data to vendor
				
				  List<String> naicsCodeList= vendorList.parallelStream().map(
				  ContractsDataAggregatedByVendorNaicsPsc::getNaicsCode).distinct().collect(
				  Collectors.toList());
					  naicsCodeList.forEach(naicsCode ->{
					  naicsCodeDataList= naicsCodeDataRepository.findByNaicsCode(naicsCode) ;
					  naicsCodeDataListAll.addAll(naicsCodeDataList);
					  naicsCodeDataListAll =naicsCodeDataListAll.stream().filter( distinctByKey(n ->n.getNaicsCode())).limit(5).collect(Collectors.toList());
					  }) ;
					 
		      //  ProductServiceCodeData  
				  List<String> productServiceCodeList= vendorList.parallelStream().map(
				  ContractsDataAggregatedByVendorNaicsPsc::getProductOrServiceCode).distinct().
				  collect(Collectors.toList());
					  productServiceCodeList.forEach(productServiceCode -> {
					  productServiceCodeDataList = productServiceCodeDataRepository.findByPscCode(productServiceCode) ;
					  pscCodeDataListAll.addAll(productServiceCodeDataList); 
					  pscCodeDataListAll = pscCodeDataListAll.stream().filter( distinctByKey(p ->p.getPscCode())).limit(5).collect(Collectors.toList());
					  
					  });
					 
		
			
			Map<String, Object> response = new HashMap<>();
	
			  response.put("naics", naicsCodeDataListAll);
		      response.put("psc", pscCodeDataListAll);
			vendorList = null;
			return new ResponseEntity<>(response, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	

	// Utility function
	public static <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor) {
		Map<Object, Boolean> map = new ConcurrentHashMap<>();
		return t -> map.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
	}

}
