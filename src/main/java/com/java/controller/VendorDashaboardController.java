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
import com.java.service.ContractsDataAggregatedByVendorNaicsPscService;

@RestController
@RequestMapping("/api/vendors")

@CrossOrigin(origins = "*", maxAge = 3600)
public class VendorDashaboardController {
	@Autowired
	private ContractsDataAggregatedByVendorNaicsPscRepository vendorNaicsPscDao;
	 @Autowired
	 private ContractsDataAggregatedByVendorNaicsPscService vendorNaicsPscService;
	 
	 @Autowired
	 NaicsCodeDataRepository naicsCodeDataRepository ;
	 
	 @Autowired
	 ContractsTransactionDataRepository contractsTransactionDataRepository ;
		 
	 @Autowired
    public ProductServiceCodeDataRepository productServiceCodeDataRepository ;
	 
	 
	 @Autowired
	 public OpportunityVendorsPpRepository opportunityVendorsPpRepository ;
	 
	 public  List<NaicsCodeData>  naicsCodeDataAllList = new ArrayList<NaicsCodeData>();
	 
	 //public  List<NaicsCodeData>  naicsCodeDataAllList1 = new ArrayList<NaicsCodeData>();
	 
	 public  List<ProductServiceCodeData> productServiceCodeDataList = new ArrayList<ProductServiceCodeData>();
	
	 public   List<ContractsDataAggregatedByVendorNaicsPsc> vendorNaicsPscList = new ArrayList<ContractsDataAggregatedByVendorNaicsPsc>();
	 
	 public   List<ContractsTransactionData> contractsTransactionDataList = new ArrayList<ContractsTransactionData>();
	 
	 public   List<OpportunityVendorsPp> opportunityVendorsPpList = new ArrayList<OpportunityVendorsPp>();
	 
	
	 @GetMapping("/{id}")
	    public ResponseEntity<Map<String, Object>> getVendorDetails(@PathVariable Integer id){
		 
		   try {
	
		   // fetching vendor  data in order to duns number
		      vendorNaicsPscList = vendorNaicsPscService.findAllByVendorDunsNumber(id) ;
		      
		      
		      
		     
			   
			   
			  // vendorNaicsPscList = ContractsDataAggregatedByVendorNaicsPscRepository.findByVendorDunsNumber(id) ;
		      
		         
		      //vendorNaicsPscList.stream().map(e -> e.getNaicsCode()).distinct().forEach(e -> System.out.print(e));
		   
		      
		      
		      
		      
		      
		      // fetching naics data to vendor
				  List<String> naicsCodeList= vendorNaicsPscList.parallelStream().map(
				  ContractsDataAggregatedByVendorNaicsPsc::getNaicsCode).distinct().collect(Collectors.toList()); 
				  naicsCodeDataAllList =naicsCodeDataRepository.findAll(); 
				  naicsCodeList.forEach(naicsCode -> {
				  naicsCodeDataAllList = naicsCodeDataAllList.stream().filter(e ->
				  e.getNaicsCode().equals(naicsCode)).filter( distinctByKey(p ->
				  p.getNaicsCode()) ).collect(Collectors.toList());
				  
				  });
				 
				 
		    //  ProductServiceCodeData  
			  productServiceCodeDataList = productServiceCodeDataRepository.findAll();
			  List<String> productServiceCodeList= vendorNaicsPscList.parallelStream().map(
			  ContractsDataAggregatedByVendorNaicsPsc::getProductOrServiceCode).distinct().
			  collect(Collectors.toList());
			  productServiceCodeList.forEach(productServiceCode -> {
			  productServiceCodeDataList = productServiceCodeDataList.stream().filter(e ->
			  e.getPscCode().equals(productServiceCode)).filter( distinctByKey(p ->
			  p.getPscCode()) ).collect(Collectors.toList());
			  
			  });
			  
			  // contract transcation data
			     contractsTransactionDataList = contractsTransactionDataRepository.findByVendorDunsNumber(id) ;
			     Map<Integer, Long> countTotalAwards=contractsTransactionDataList.stream().collect(Collectors.groupingBy(ContractsTransactionData::getVendorDunsNumber,Collectors.counting()));
				 List<String>  ContractAgencyName =  contractsTransactionDataList.stream().filter(e -> e.getVendorDunsNumber().equals(id)).map(ContractsTransactionData::getContractingAgencyName).collect(Collectors.toList());   
				 ContractAgencyName = ContractAgencyName.stream().filter( distinctByKey(p ->p.toString()) ).collect(Collectors.toList());
				
				// rating
				 
				 
				  String  vendorDunsNumberStr =Integer.toString(id); 
				  opportunityVendorsPpList=opportunityVendorsPpRepository.findByEntityId(vendorDunsNumberStr) ;
				 
				 // generate Response
				 Map<String, Object> response = new HashMap<>();
				 response.put("vendors", vendorNaicsPscList);
			  	 response.put("naics", naicsCodeDataAllList);
			     response.put("psc", productServiceCodeDataList);
			     response.put("totalAward",countTotalAwards.get(id));
			     response.put("agencyName", ContractAgencyName);
			     response.put("rating", opportunityVendorsPpList);
			 
			   return new ResponseEntity<>(response, HttpStatus.OK);
			   
			 } catch (Exception e) {
				return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
			}
	    }
		
	   
	   
	   
		@GetMapping("")
		public ResponseEntity<Map<String, Object>> getVendors(@RequestParam(defaultValue = "0") Integer pageNo,
				@RequestParam(defaultValue = "10") Integer pageSize, @RequestParam(required = false) String title) {

			try {
				List<ContractsDataAggregatedByVendorNaicsPsc> vendorNaicsPscList = new ArrayList<ContractsDataAggregatedByVendorNaicsPsc>();
				Pageable paging = PageRequest.of(pageNo, pageSize);

				Page<ContractsDataAggregatedByVendorNaicsPsc> pageVendorNaicsPsc;

				pageVendorNaicsPsc = vendorNaicsPscDao.findAll(paging);

				vendorNaicsPscList = pageVendorNaicsPsc.getContent();
				
				
				   //OpportunityVendorsPp
				
			      //List<Integer> vendorDunsNumberList =	vendorNaicsPscList.stream().map(e->e.getVendorDunsNumber()).collect(Collectors.toList()) ;
				
			      // System.out.println("-------------vendorDunsNumberList---------------------------"+vendorDunsNumberList);
			      
			     //   8016958
			      
			      //1307495  835551474 794571448
			      
			    // System.out.println("---------11--opportunityVendorsPpList---------------"+opportunityVendorsPpRepository.findByEntityId("794571448"));
					
			      
				
				/*
				 * for(Integer vendorDunsNumber :vendorDunsNumberList) { String
				 * vendorDunsNumberStr =Integer.toString(vendorDunsNumber); //
				 * System.out.println("-----------vendorDunsNumberStr---------------"+
				 * vendorDunsNumberStr);
				 * 
				 * //
				 * opportunityVendorsPpRepository.findByEntityId(vendorDunsNumberStr).forEach(e-
				 * > System.out.print("  "+ e.getRating()+ "----------"+
				 * e.getPpCategoryCode()));
				 * 
				 * opportunityVendorsPpList=opportunityVendorsPpRepository.findByEntityId(
				 * vendorDunsNumberStr) ;
				 * 
				 * }
				 */
				 
			     // System.out.println("-----------1307495---------------");
			        
			     // opportunityVendorsPpList.stream().filter(e -> e.getEntityId().equals("1307495")).forEach(b-> System.out.println(b));
			      
			    //  opportunityVendorsPpList =opportunityVendorsPpRepository.findByEntityId("1307495") ;
			    
			        
			    //  System.out.println("-----------opportunityVendorsPpList---------------"+opportunityVendorsPpList);
				
                  
				Map<String, Object> response = new HashMap<>();
				response.put("vendors", vendorNaicsPscList);
				response.put("currentPage", pageVendorNaicsPsc.getNumber());
				response.put("totalItems", pageVendorNaicsPsc.getTotalElements());
				response.put("totalPages", pageVendorNaicsPsc.getTotalPages());
				//response.put("rating", opportunityVendorsPpList);
				
				
                    
				vendorNaicsPscList= null ;
				opportunityVendorsPpList = null ;
				return new ResponseEntity<>(response, HttpStatus.OK);
			} catch (Exception e) {
				return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
	   
	   
	    //Utility function
	    public static <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor) 
	    {
	        Map<Object, Boolean> map = new ConcurrentHashMap<>();
	        return t -> map.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
	    }
	
	   
	   
}
