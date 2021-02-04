package com.java.controller;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

import com.java.Repository.AgencyReferenceDataRepository;
import com.java.Repository.ContractsTransactionDataRepository;
import com.java.Repository.NaicsCodeDataRepository;
import com.java.Repository.OpportunityVendorsPpRepository;
import com.java.model.AgencyReferenceData;
import com.java.model.ContractsTransactionData;
import com.java.model.NaicsCodeData;
import com.java.model.OpportunityVendorsPp;
@RestController
@RequestMapping("/api/organization") 
@CrossOrigin(origins = "*", maxAge = 3600)
public class VendorAgancyReferenceContoller {

	@Autowired
	AgencyReferenceDataRepository agencyReferenceDataRepository;
	@Autowired
	ContractsTransactionDataRepository contractsTransactionDataRepository;
	@Autowired 
	NaicsCodeDataRepository naicsCodeDataRepository;
	@Autowired
	OpportunityVendorsPpRepository opportunityVendorsPpRepository ;

	public List<AgencyReferenceData> agencyReferenceDataList = new ArrayList<AgencyReferenceData>();
	public List<AgencyReferenceData> agencyOrganizationCodeList = new ArrayList<AgencyReferenceData>();

	public List<ArrayList<ContractsTransactionData>> contractsTransactionDataListOfList = new ArrayList<ArrayList<ContractsTransactionData>>();

	public Map<String, List<ContractsTransactionData>> contractsTransactionDataMap;
	public  List<ContractsTransactionData> contractsTransactionDataList;
	public  Page<List<ContractsTransactionData>> contractsTransactionDataPage;
	public List<String>  vendorsList =   new ArrayList<String>();
	public List<String>  vendorsCountList =   new ArrayList<String>();
	public List<AgencyReferenceData> organizationDataList = new ArrayList<AgencyReferenceData>();
	
	public List<ContractsTransactionData>   contractsList ;
	
	public List<List<ContractsTransactionData>>   contractsAverageRatingDetailList ;
	
	//public ContractsTransactionDataDTO contractsTransactionDataDTO  ;
//	public List<ContractsTransactionDataDTO> contractsTransactionDataDTOList ;

	
	List<String>  naics =   new ArrayList<String>();
	List<Integer>  naicsCodeList =   new ArrayList<Integer>();
	List<String>  psc =   new ArrayList<String>();
	
	 ArrayList<String> naicsmapList = new ArrayList<String>();
	 ArrayList<String> pscmapList = new ArrayList<String>();
	
	//public Map<Integer,String>  naics =  new HashMap<>();
	//public Map<String,String>psc =   new HashMap<>();
			
	public  Map<Integer, String> result1 ;
	public  Long  uniqueVendors ;  
	public  Map<String, Long> contratctsAwardedSum ;
	public  Long  contratctsAwardedCount  ;
	public  double contratctsAwarded ;
	BigDecimal totalAmt ;
	
	BigDecimal ratingAvg ;
	
	Map<String, Double> avgRating ;
	
	
	List<Map<String, Double>> steps = new ArrayList<>();
	
	List<Map<String, Double>>avgRatingList ;
	public  List<NaicsCodeData>  naicsCodeDataList = new ArrayList<NaicsCodeData>();
	public  List<ContractsTransactionData>  contractsTransactionList = new ArrayList<ContractsTransactionData>(); ;
	public  List<List<ContractsTransactionData>>  contractsTransactionListofList ;
	
	public  List<ContractsTransactionData>  contractsTransactionTest = new ArrayList<ContractsTransactionData>(); ;
	 public  ContractsTransactionData contractsTransactionData  = new ContractsTransactionData();
	public  Map pscmap ;
	public  Map naicsmap ;
	public  List<Integer>  vendorsDunsNumberList ;
	public  List<Double> avrgTotal  ;
	private static DecimalFormat df = new DecimalFormat("0.00");
	  int count  =0 ;
	@GetMapping("")
	public ResponseEntity<Map<String, Object>> getAgencyDepartment(@RequestParam(defaultValue = "0") Integer pageNo,
			@RequestParam(defaultValue = "10") Integer pageSize, @RequestParam(required = false) String title) {

		try {
			Pageable paging = PageRequest.of(pageNo, pageSize);
			Page<AgencyReferenceData> pageAgencyReferenceData;
			pageAgencyReferenceData = agencyReferenceDataRepository.findByParentOrgKey("NULL", paging);
			agencyReferenceDataList = pageAgencyReferenceData.getContent();
			Map<String, Object> response = new HashMap<>();
			response.put("organization", agencyReferenceDataList);
			response.put("currentPage", pageAgencyReferenceData.getNumber());
			response.put("totalItems", pageAgencyReferenceData.getTotalElements());
			response.put("totalPages", pageAgencyReferenceData.getTotalPages());
			agencyReferenceDataList = null;
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// get data from constract_transaction_data as given agency list which is being
	// coming from agency_reference_data

	
	

	@SuppressWarnings("unchecked")
	@GetMapping("/{agencyCodeId}")
	public ResponseEntity<Map<String, Object>> getContractTrasaction(@PathVariable String agencyCodeId,
			@RequestParam(defaultValue = "0") Integer pageNo, @RequestParam(defaultValue = "10") Integer pageSize,
			@RequestParam(required = false) String title) {

		try {

			Pageable paging = PageRequest.of(pageNo, pageSize);
		  	String agencyCode = agencyCodeId.toString();
			

			 // agencyOrganizationCodeList = agencyReferenceDataRepository.findByParentOrgKey(agencyCode);
			

			/*
			 * List<String> organizationList =
			 * agencyOrganizationCodeList.stream().filter(Objects::nonNull) .map(m ->
			 * m.getOrganizationCode()).collect(Collectors.toList());
			 * 
			 * organizationList = organizationList.stream().filter(v ->
			 * !v.equals("NULL")).map(s -> s) .collect(Collectors.toList());
			 */

		    //	organizationList.forEach((String agencyCode) -> {
			//	});
			
				List<ContractsTransactionData> checkContractsTransactionIsEmpty = contractsTransactionDataRepository
						.findByAgencyCode(agencyCode).stream().collect(Collectors.toList());

				boolean status = checkContractsTransactionIsEmpty.isEmpty();
				if (status != true) {
					contractsTransactionDataList = contractsTransactionDataRepository.findByAgencyCode(agencyCode) ;
					  vendorsList =  contractsTransactionDataList.stream().sorted(Comparator.comparingDouble(ContractsTransactionData::getDollarsObligated).reversed()).map(e -> e.getVendorName()).distinct() .limit(5).collect(Collectors.toList()) ;
					  
					  
					  naicsmap = contractsTransactionDataList.stream().sorted(Comparator.comparingDouble(ContractsTransactionData::getDollarsObligated).reversed()).limit(10)
				                  .collect(
				                        Collectors.toMap(
				                        		ContractsTransactionData::getNaicsCode, ContractsTransactionData::getNaicsDescription, // key = name, value = websites
				                                (oldValue, newValue) -> oldValue,       // if same key, take the old key
				                                LinkedHashMap::new                      // returns a LinkedHashMap, keep order
				                        ));
					  
					   naicsmapList = new ArrayList<String>();
					   naicsmap.forEach((key,value)->{naicsmapList.add(key+"-"+value);});
					   
					    
					   pscmap =contractsTransactionDataList.stream().sorted(Comparator.comparingDouble(ContractsTransactionData::getDollarsObligated).reversed()).distinct() .limit(10)
				                  .collect(
					                        Collectors.toMap(
					                        		ContractsTransactionData::getProductOrServiceCode, ContractsTransactionData::getProductOrServiceDescription, // key = name, value = websites
					                                (oldValue, newValue) -> oldValue,       // if same key, take the old key
					                                LinkedHashMap::new                      // returns a LinkedHashMap, keep order
					                        ));
					   pscmapList = new ArrayList<String>();
					   pscmap.forEach((key,value)->{pscmapList.add(key+"-"+value);});
					   
					   vendorsCountList = contractsTransactionDataList.stream().map(e -> e.getVendorName()).distinct().collect(Collectors.toList());
					   uniqueVendors = vendorsCountList.stream().count();
					   contratctsAwarded	 =contractsTransactionDataList.stream().collect(Collectors.summingDouble(ContractsTransactionData::getDollarsObligated));
					   contratctsAwardedCount = contractsTransactionDataList.stream().collect(Collectors.summingLong(ContractsTransactionData::getNumberOfActions));
					   organizationDataList = agencyReferenceDataRepository.findByOrganizationCode(agencyCode).stream().limit(1).collect(Collectors.toList());
					   
					   vendorsDunsNumberList = contractsTransactionDataList.stream().map(e -> e.getVendorDunsNumber()).distinct().collect(Collectors.toList());
					   vendorsDunsNumberList. forEach((Integer vendorsDunNumber) -> {
							  List< OpportunityVendorsPp > opportunityVendorsPpList =
							  opportunityVendorsPpRepository.findByEntityId(vendorsDunNumber.toString()).
							  stream(). filter( distinctByKey(p ->p.toString())
							  ).collect(Collectors.toList());
							  
							  
							   boolean avrgRatingStatus  = opportunityVendorsPpList.isEmpty() ;
							  if(avrgRatingStatus!=true) {
								
							  avgRating=opportunityVendorsPpList.stream().collect(Collectors.groupingBy(OpportunityVendorsPp::getEntityId, Collectors.averagingDouble(OpportunityVendorsPp::getRating)));
			    		       steps.add(avgRating);
							  }
							 
					   });
					   
					   
						
				}
				
				Map<String, Double> ratingAverageMap = new HashMap<>();
				steps.stream().forEach(map -> {
					ratingAverageMap.putAll(map.entrySet().stream()
				        .collect(Collectors.toMap(entry -> entry.getKey(), entry -> (Double) entry.getValue())));
				});
			
				
				
				// contracts that need attention --------contracts transaction details----------------
			
				Map<String, Double> ratingBelowMap = new HashMap<>();
				ratingAverageMap.forEach( (key,value) -> {
					      if(value  < 2.5) {
					    	  ratingAvg = BigDecimal.valueOf(value);
					    	  ratingAvg = ratingAvg.setScale(2, RoundingMode.HALF_UP);
					    	  ratingBelowMap.put(key,ratingAvg.doubleValue()); 
					      }
				
				});
				 
				ratingBelowMap.forEach( (dunsNumber,avgRating) -> {
					 Integer  dunsNumberInt  =  Integer.parseInt(dunsNumber);
					 contractsList = contractsTransactionDataRepository.findByVendorDunsNumber(dunsNumberInt).stream().limit(1).collect(Collectors.toList());
					 contractsList.get(0).setAverageRating(avgRating);
					  contractsTransactionList = Optional.ofNullable(contractsList)
					  .map(Collection::stream) .orElseGet(Stream::empty)
					  .collect(Collectors.toList());
					  contractsTransactionTest.addAll(contractsTransactionList) ;
				
				
				});
				
					
				List<Double>  averageRating = 	 ratingAverageMap.entrySet().stream().map(e-> e.getValue()).collect(Collectors.toList());
				Double  finalResult =  averageRating.stream().mapToDouble(a -> a).average().getAsDouble();
				
				
				//------------------------------------------------------------------------------------------
         		totalAmt = new BigDecimal(Double.toString(contratctsAwarded));
		    	Map<String, Object> response = new HashMap<>();
			    response.put("vendors",vendorsList);
			    response.put("naics",naicsmapList);
			    response.put("psc",pscmapList);
			    response.put("uniqueVendors",uniqueVendors);
			    response.put("contratctsAwarded",totalAmt);
			    response.put("contratctsAwardedCount", contratctsAwardedCount);
			    response.put("organization", organizationDataList);
			    response.put("averageVendorRating", df.format(finalResult));
			    response.put("contractsTransaction",contractsTransactionTest);
			   // response.put("contractsTransactionAvgRating", ratingBelowMap);
			    
			    
			    
			//  response.put("currentPage", contractsTransactionDataPage.getNumber());
			//  response.put("totalItems",contractsTransactionDataPage.getTotalElements());
			//  response.put("totalPages", contractsTransactionDataPage.getTotalPages());
			
			
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
