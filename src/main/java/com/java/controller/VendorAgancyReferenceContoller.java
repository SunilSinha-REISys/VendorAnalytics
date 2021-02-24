package com.java.controller;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Date;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
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
import com.java.Repository.OpportunityInterestedVendorRepository;
import com.java.Repository.OpportunityVendorsPpRepository;
import com.java.Repository.SmallBusinessGoalRepository;
import com.java.model.AgencyReferenceData;
import com.java.model.ContractsTransactionData;
import com.java.model.NaicsCodeData;
import com.java.model.OpportunityInterestedVendor;
import com.java.model.SmallBusinessGoal;

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
	OpportunityVendorsPpRepository opportunityVendorsPpRepository;

	@Autowired
	SmallBusinessGoalRepository smallBusinessGoalRepository;

	@Autowired
	public OpportunityInterestedVendorRepository opportunityInterestedVendorRepository ;
	
	public List<AgencyReferenceData> agencyReferenceDataList = new ArrayList<AgencyReferenceData>();
	public List<AgencyReferenceData> agencyOrganizationCodeList = new ArrayList<AgencyReferenceData>();

	public List<ArrayList<ContractsTransactionData>> contractsTransactionDataListOfList = new ArrayList<ArrayList<ContractsTransactionData>>();

	public Map<String, List<ContractsTransactionData>> contractsTransactionDataMap;
	public List<ContractsTransactionData> contractsTransactionDataList;
	public Page<List<ContractsTransactionData>> contractsTransactionDataPage;
	public List<String> vendorsList = new ArrayList<String>();
	public List<String> vendorsCountList = new ArrayList<String>();
	public List<AgencyReferenceData> organizationDataList = new ArrayList<AgencyReferenceData>();
	public Map<String, Double> ratingBelowMap = new HashMap<>();

	public List<ContractsTransactionData> contractsList;

	public List<List<ContractsTransactionData>> contractsAverageRatingDetailList;
	public SmallBusinessGoal smallBusinessGoal;

	List<String> naics = new ArrayList<String>();
	List<Integer> naicsCodeList = new ArrayList<Integer>();
	List<String> psc = new ArrayList<String>();

	ArrayList<String> naicsmapList = new ArrayList<String>();
	ArrayList<String> pscmapList = new ArrayList<String>();

	public Map<Integer, String> result1;
	public Long uniqueVendors;
	public Map<String, Long> contratctsAwardedSum;
	public Long contratctsAwardedCount;
	public double contratctsAwarded;

	BigDecimal totalAmt = new BigDecimal(0);
	BigDecimal targetAmt = new BigDecimal(0);
	BigDecimal ratingAvg = new BigDecimal(0);

	double targetAcheivedPercent = 0.0;
	public long activeAwards;
	public long activeOpportunities;
	public int  activeOpportunitiesCounter  = 0 ;
	public Map<String, Double> avgRating = new HashMap<String, Double>();
	double averageRatingResult = 0.0;

	List<Map<String, Double>> steps = new ArrayList<>();
	Set<ContractsTransactionData> contractsTransactionSet = new HashSet<ContractsTransactionData>();

	List<Map<String, Double>> avgRatingList;
	public List<NaicsCodeData> naicsCodeDataList = new ArrayList<NaicsCodeData>();
	public List<ContractsTransactionData> contractsTransactionList = new ArrayList<ContractsTransactionData>();;
	public List<List<ContractsTransactionData>> contractsTransactionListofList;
	
	public List<ContractsTransactionData> contractsTransactionListAll = new ArrayList<ContractsTransactionData>();;
	public ContractsTransactionData contractsTransactionData = new ContractsTransactionData();
	public Map<String ,String> pscmap;
	public Map<Integer,String> naicsmap;
	public List<Integer> vendorsDunsNumberList;
	public List<Double> avrgTotal;
	private static DecimalFormat df = new DecimalFormat("0.00");
	int count = 0;
	
	public Map<String, String> subOfficeMap = new HashMap<>();
	public List<ContractsTransactionData> contractsTransactionDataPreviousList = new ArrayList<ContractsTransactionData>();
	public  int contratctsReceivedStatus ;
	public  int contratctsAwardedStatus ;
	public  int activeAwardsStatus ;
	public  int uniqueVendorStatus ;
	public  int activeOpportunityStatus ;
	public  int averageRatingStatus ;
	public List<Integer> vendorsDunsNumberPreviousList;
	public int  activeOpportunitiesPreviousCounter ;
	public Map<String, Double> avgRatingPrevious = new HashMap<String, Double>();
	
	 public Map<String,Long> opportunitiesCounter  = new HashMap<>();
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
			ContractsTransactionData checkContractsTransactionIsEmpty = contractsTransactionDataRepository
					.findOneByAgencyConde(agencyCode);
			if (checkContractsTransactionIsEmpty == null) {

				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
			
		

			if (checkContractsTransactionIsEmpty != null) {
		 	
				//contractsTransactionDataList = contractsTransactionDataRepository.findByAgencyCode(agencyCode);
				//  sub organization or office or sub-tire-----------------------------------------------
				
				
				  String organizationName =checkContractsTransactionIsEmpty.getContractingDepartmentName() ;
				 
				  List<AgencyReferenceData> organizationNameList =agencyReferenceDataRepository.findByOrganizationName( organizationName ) ;
				
				    for (AgencyReferenceData agencyRefData : organizationNameList) {
				    	subOfficeMap.put(agencyRefData.getOrganizationCode(), agencyRefData.getOrganizationName());
				    }
				  
				   // subOfficeMap.forEach((a,b)-> System.out.println(a+"----------"+b));
				    
				  //----------------------------------------------------------------------------------------------
				      LocalDate currentDate = LocalDate.now();
					  Date  today =  Date.valueOf(currentDate) ;
					  LocalDate previousYear = currentDate.minus(2, ChronoUnit.YEARS);
					  Date  firstPreviousYearDate =  Date.valueOf(previousYear) ;
					  LocalDate nextYear = previousYear.minus(1, ChronoUnit.YEARS); 
					  Date  secondPreviouYearDate =  Date.valueOf(nextYear) ;
				    
						//  System.out.println("current year ---"+ today  );
						//  System.out.println("previousYear ---"+ firstPreviousYearDate);
						//  System.out.println("nextYearDate ---"+  secondPreviouYearDate);
						 
						  contractsTransactionDataList = contractsTransactionDataRepository.findByAgencyCodeAndStartDateEndDate(agencyCode,firstPreviousYearDate,today);
						  contractsTransactionDataPreviousList = contractsTransactionDataRepository.findByAgencyCodeAndStartDateEndDate(agencyCode,secondPreviouYearDate,firstPreviousYearDate);
				    
				  //-----------------------------------------------------------------------------------------------  
				
				// Active Award based on agency code---------------------------
				//Date today = Date.valueOf(LocalDate.now());
						  
						 //   System.out.println("----------1---------");
						  
						  long activeAwardsPrevious = contractsTransactionDataPreviousList.stream().filter(e ->  e.getUltimateCompletionDate()== null  ||  ( e.getUltimateCompletionDate().before(firstPreviousYearDate ) &&  e.getUltimateCompletionDate().after(secondPreviouYearDate)) ).count();
						  activeAwards = contractsTransactionDataList.stream().filter(e ->  e.getUltimateCompletionDate()== null  ||  ( e.getUltimateCompletionDate().before(today) &&  e.getUltimateCompletionDate().after(firstPreviousYearDate)) ).count();
										  
						  long  activeAwardArrow = activeAwards - activeAwardsPrevious ;
					        if(activeAwardArrow > 0) {
					        	activeAwardsStatus =1;
					        	
					        }else {
					        	activeAwardsStatus =0;
					        }
						  
					      // System.out.println("----------2---------"+contractsTransactionDataList.size());
					        
						  //activeAwards = contractsTransactionDataList.stream().filter(e -> e.getUltimateCompletionDate() == null || today.after(e.getUltimateCompletionDate())).count();

				vendorsList = contractsTransactionDataList.stream()
						.sorted(Comparator.comparingDouble(ContractsTransactionData::getDollarsObligated).reversed())
						.map(e -> e.getVendorName()).distinct().limit(5).collect(Collectors.toList());
				naicsmap = contractsTransactionDataList.stream()
						.sorted(Comparator.comparingDouble(ContractsTransactionData::getDollarsObligated).reversed())
						.limit(5)
						.collect(Collectors.toMap(ContractsTransactionData::getNaicsCode,
								ContractsTransactionData::getNaicsDescription, // key = name, value = val
								(oldValue, newValue) -> oldValue, // if same key, take the old key
								LinkedHashMap::new // returns a LinkedHashMap, keep order
						));
				
			//	System.out.println("----------3---------");

				naicsmapList = new ArrayList<String>();
				naicsmap.forEach((key, value) -> {
					naicsmapList.add(key + "-" + value);
				});
				pscmap = contractsTransactionDataList.stream()
						.sorted(Comparator.comparingDouble(ContractsTransactionData::getDollarsObligated).reversed())
						.distinct().limit(5)
						.collect(Collectors.toMap(ContractsTransactionData::getProductOrServiceCode,
								ContractsTransactionData::getProductOrServiceDescription, // key = name, value =
																							// websites
								(oldValue, newValue) -> oldValue, // if same key, take the old key
								LinkedHashMap::new // returns a LinkedHashMap, keep order
						));

				pscmapList = new ArrayList<String>();
				pscmap.forEach((key, value) -> {
					pscmapList.add(key + "-" + value);
				});

			//	System.out.println("----------4---------");
				
				vendorsCountList = contractsTransactionDataList.stream().map(e -> e.getVendorName()).distinct().collect(Collectors.toList());

				uniqueVendors = vendorsCountList.stream().count();
				
				List<String> vendorsCountPreviousList = contractsTransactionDataPreviousList.stream().map(e -> e.getVendorName()).distinct().collect(Collectors.toList());
	
				long uniqueVendorsPrevious = vendorsCountPreviousList.stream().count();
				
				  long  uniqueVendorArrow = (long) (uniqueVendors - uniqueVendorsPrevious) ;
			        if(uniqueVendorArrow > 0) {
			        	uniqueVendorStatus=1;
			        	
			        }else {
			        	uniqueVendorStatus=0;
			        }
				
			      //  System.out.println("----------5---------");
				/*
				 * contratctsAwarded = contractsTransactionDataList.stream()
				 * .collect(Collectors.summingDouble(ContractsTransactionData::
				 * getDollarsObligated)); contratctsAwardedCount =
				 * contractsTransactionDataList.stream()
				 * .collect(Collectors.summingLong(ContractsTransactionData::getNumberOfActions)
				 * );
				 */
				
				     DoubleSummaryStatistics obligateAmountPrevious =  	contractsTransactionDataPreviousList.stream().collect(Collectors.summarizingDouble(ContractsTransactionData::getDollarsObligated));
				     DoubleSummaryStatistics totalNumberOfActionsPrevious =  contractsTransactionDataPreviousList.stream().collect(Collectors.summarizingDouble(ContractsTransactionData::getNumberOfActions));
				     DoubleSummaryStatistics obligateAmount =  	contractsTransactionDataList.stream().collect(Collectors.summarizingDouble(ContractsTransactionData::getDollarsObligated));
 				     DoubleSummaryStatistics totalNumberOfActions =  	contractsTransactionDataList.stream().collect(Collectors.summarizingDouble(ContractsTransactionData::getNumberOfActions));
				    
 				  //  System.out.println("----------6---------");
 				     
				    long  contratctsReceivedArrow = (long) (obligateAmount.getSum() - obligateAmountPrevious.getSum()) ;
			        if(contratctsReceivedArrow > 0) {
			        	contratctsReceivedStatus=1;
			        	
			        }else {
			        	contratctsReceivedStatus=0;
			        }
			        
			        long  contratctsAwardedArrow = (long) (totalNumberOfActions.getSum() - totalNumberOfActionsPrevious.getSum()) ;
			        if(contratctsAwardedArrow > 0) {
			        	 contratctsAwardedStatus=1;
			        }else {
			        	   contratctsAwardedStatus=0;
			         }
				   
			    	 totalAmt = new BigDecimal(Double.toString(obligateAmount.getSum()));
					 contratctsAwarded =       totalNumberOfActions.getSum() ;
					 organizationDataList = agencyReferenceDataRepository.findByOrganizationCode(agencyCode).stream().limit(1).collect(Collectors.toList());
					 vendorsDunsNumberList = contractsTransactionDataList.stream().map(e -> e.getVendorDunsNumber()).distinct().collect(Collectors.toList());
					 vendorsDunsNumberPreviousList = contractsTransactionDataPreviousList.stream().map(e -> e.getVendorDunsNumber()).distinct().collect(Collectors.toList());
				
					// System.out.println("----------7---------");
					 
					// System.out.println("---------vendorsDunsNumberListvendorsDunsNumberList-------"+vendorsDunsNumberList.size());
					// System.out.println("--------vendorsDunsNumberPreviousList-------"+vendorsDunsNumberPreviousList.size());
					 
				// active opportunities ---------------------------------------------------------------------------------------------------
				
				 vendorsDunsNumberList.forEach((Integer vendorsDunNumber) -> {
					// System.out.println("------------------"+vendorsDunNumber) ;
			
					 Double avgRatingVal = opportunityVendorsPpRepository.findAverageByVendorDunsNumber(vendorsDunNumber.toString());

						if (avgRatingVal != null) {
							avgRating.put(vendorsDunNumber.toString(), avgRatingVal);
						}
					 
					 //OpportunityInterestedVendor opportunityInterestedVendor   = null; 
						/*
						 * OpportunityInterestedVendor opportunityInterestedVendor =
						 * opportunityInterestedVendorRepository.findByEntityId(vendorsDunNumber.
						 * toString()); if(opportunityInterestedVendor != null) {
						 * 
						 * activeOpportunitiesCounter++ ; }
						 */
				 });
				 
			
				// System.out.println("----------8---------");
				 
			    vendorsDunsNumberPreviousList.forEach((Integer vendorsDunNumber) -> {
				// System.out.println("-------vendorsDunsNumberPreviousList-----------"+vendorsDunNumber) ;
				
				 
        		Double avgRatingVal = opportunityVendorsPpRepository
						.findAverageByVendorDunsNumber(vendorsDunNumber.toString());

				if (avgRatingVal != null) {
					avgRatingPrevious.put(vendorsDunNumber.toString(), avgRatingVal);
				}
				 
				
					/*
					 * OpportunityInterestedVendor opportunityInterestedVendor =
					 * opportunityInterestedVendorRepository.findByEntityId(vendorsDunNumber.
					 * toString());
					 * 
					 * //OpportunityInterestedVendor opportunityInterestedVendor = null ;
					 * if(opportunityInterestedVendor != null) {
					 * 
					 * activeOpportunitiesPreviousCounter++ ; }
					 */
			 });
			 	 
			    activeOpportunitiesCounter = vendorsDunsNumberList.size();
			    activeOpportunitiesPreviousCounter=vendorsDunsNumberPreviousList.size();
			
			 long  activeOpportunitiesArrow = (long) ( activeOpportunitiesCounter - activeOpportunitiesPreviousCounter) ;
		        if(activeOpportunitiesArrow > 0) {
		        	activeOpportunityStatus=1;
		        }else {
		        	activeOpportunityStatus=0;
		         }
			
		      //  System.out.println("----------9---------");
		//	---------------------------------------------------------------------------------------------------------	
		
				
	
				for (Map.Entry<String, Double> entry : avgRating.entrySet()) {
					// System.out.println(entry.getKey() + ":" + entry.getValue());
					if (entry.getValue() < 2.5) {
						ratingAvg = BigDecimal.valueOf(entry.getValue());
						ratingAvg = ratingAvg.setScale(2, RoundingMode.HALF_UP);
						ratingBelowMap.put(entry.getKey(), ratingAvg.doubleValue());
					}
				}

			}
		//	System.out.println("----------10---------");
		
			contractsTransactionSet =  new HashSet<ContractsTransactionData>();
			ratingBelowMap.forEach((dunsNumber, avgRating) -> {

				Integer dunsNumberInt = null;
				dunsNumberInt = Integer.parseInt(dunsNumber);
				contractsList = contractsTransactionDataRepository
						.findByVendorDunsNumberAndAgencyCode(dunsNumberInt, agencyCode).stream()
						.collect(Collectors.toList());

				if (!contractsList.isEmpty()) {
					contractsList.get(0).setAverageRating(avgRating);

					contractsTransactionList = Optional.ofNullable(contractsList).map(Collection::stream)
							.orElseGet(Stream::empty).collect(Collectors.toList());

					for (ContractsTransactionData c : contractsTransactionList) {
						 contractsTransactionSet.add(c);
					}
				}
			});
			
			
			//System.out.println("----------11---------");

			List<Double> averageRating = avgRating.entrySet().stream().map(e -> e.getValue()).collect(Collectors.toList());
			averageRatingResult = averageRating.stream().mapToDouble(a -> a).average().getAsDouble();
			
			List<Double> averageRatingPrevious = avgRatingPrevious.entrySet().stream().map(e -> e.getValue()).collect(Collectors.toList());
		
			double averageRatingPreviousResult = averageRatingPrevious.stream().mapToDouble(a -> a).average().getAsDouble();
			
			double  averageRatingArrow =  averageRatingResult - averageRatingPreviousResult ;
		        if(averageRatingArrow > 0) {
		        	averageRatingStatus=1;
		        }else {
		        	averageRatingStatus=0;
		         }
			
		     //   System.out.println("----------12---------");
			smallBusinessGoal = smallBusinessGoalRepository.findByOrganizationCode(agencyCode);
			targetAmt = new BigDecimal(Double.toString(smallBusinessGoal.getTargetAmount()));

			//totalAmt = new BigDecimal(Double.toString(contratctsAwarded));
		
			targetAcheivedPercent = (contratctsAwarded / smallBusinessGoal.getTargetAmount()) * 100;

			Map<String, Object> response = new HashMap<>();
			response.put("vendors", vendorsList);
			response.put("naics", naicsmapList);
			response.put("psc", pscmapList);
			response.put("organization", organizationDataList);
			response.put("averageVendorRating", df.format(averageRatingResult));
			response.put("contractsTransaction", contractsTransactionSet);
			response.put("targetAmount", targetAmt);
			response.put("targetAcheivedPercent", df.format(targetAcheivedPercent));
			response.put("uniqueVendors", uniqueVendors);
			//response.put("contratctsAwarded", totalAmt);
			response.put("contratctsReceived",totalAmt);
		    response.put("contratctsAwarded", contratctsAwarded);
			//response.put("contratctsAwardedCount", contratctsAwardedCount);
			response.put("activeAwards", activeAwards);
			response.put("activeOpportunities", activeOpportunitiesCounter);
			response.put("contratctsReceivedStatus", contratctsReceivedStatus);
			response.put("contratctsAwardedStatus", contratctsAwardedStatus);
			response.put("activeAwardsStatus", activeAwardsStatus);
			response.put("uniqueVendorStatus", uniqueVendorStatus);
			response.put("activeOpportunityStatus", activeOpportunityStatus);
			response.put("averageRatingStatus", averageRatingStatus);
			response.put("subOffice", subOfficeMap);
			
			
			// response.put("currentPage", contractsTransactionDataPage.getNumber());
			// response.put("totalItems",contractsTransactionDataPage.getTotalElements());
			// response.put("totalPages", contractsTransactionDataPage.getTotalPages());

			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {

			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	
	//------------------------Contract Information  based on vendor id , start date and end date-------------------------
	//------------------------Contract Information  based on vendor id , start date and end date-------------------------
	
	
	@GetMapping("/contractInformation/{agencyCodeId}")
	public ResponseEntity<Map<String, Object>> getVendorDateWise(@PathVariable Integer agencyCodeId , @RequestParam(name="start", required = false)  String startDate,@RequestParam(name="end", required = false)  String endDate) {
			
		try {
			String agencyCode = agencyCodeId.toString();
			ContractsTransactionData checkContractsTransactionIsEmpty = contractsTransactionDataRepository.findOneByAgencyConde(agencyCode);
			if (checkContractsTransactionIsEmpty == null) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
			
			if (checkContractsTransactionIsEmpty != null) {
				//  System.out.println(agencyCodeId+"---startDate-------"+startDate+"----endDate----"+endDate);
			      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy"); 
		          LocalDate start_date = LocalDate.parse(startDate.trim() , formatter); 
		          LocalDate end_date = LocalDate.parse(endDate.trim() , formatter); 
		          
		          Date datestart = Date.valueOf(start_date);
		          Date dateend = Date.valueOf(end_date);
		          
		          LocalDate previousYear = start_date.minus(1, ChronoUnit.DAYS); 
				  Date  previousYearDate =  Date.valueOf(previousYear) ;
		          
		         contractsTransactionDataList = contractsTransactionDataRepository.findByAgencyCodeAndStartDateEndDate(agencyCode,datestart,dateend);
		        
		         vendorsCountList = contractsTransactionDataList.stream().map(e -> e.getVendorName()).distinct().collect(Collectors.toList());
		         uniqueVendors = vendorsCountList.stream().count();
		         DoubleSummaryStatistics obligateAmount =  	contractsTransactionDataList.stream().collect(Collectors.summarizingDouble(ContractsTransactionData::getDollarsObligated));
				 DoubleSummaryStatistics totalNumberOfActions =  contractsTransactionDataList.stream().collect(Collectors.summarizingDouble(ContractsTransactionData::getNumberOfActions));
				 activeAwards = contractsTransactionDataList.stream().filter(e ->  e.getUltimateCompletionDate()== null  ||  ( e.getUltimateCompletionDate().before(datestart) &&  e.getUltimateCompletionDate().after(dateend)) ).count();
				
				 vendorsDunsNumberList = contractsTransactionDataList.stream().map(e -> e.getVendorDunsNumber()).distinct().collect(Collectors.toList());
					
				// System.out.println("----------vendorsDunsNumberList------------"+vendorsDunsNumberList.size());
				 
				 activeOpportunitiesCounter =vendorsDunsNumberList.size() ;
				 
				    // active opportunities ---------------------------------------------------------------------------------------------------
						
						// System.out.println("------------1-------------------");
							/*
							 * vendorsDunsNumberList.forEach((Integer vendorsDunNumber) -> {
							 * 
							 * OpportunityInterestedVendor opportunityInterestedVendor =
							 * opportunityInterestedVendorRepository.findByEntityId(vendorsDunNumber.
							 * toString()); if(opportunityInterestedVendor != null) {
							 * 
							 * activeOpportunitiesCounter++ ; } });
							 */
		
				     // System.out.println("------------2------------------");
			 totalAmt = new BigDecimal(Double.toString(obligateAmount.getSum()));
			 contratctsAwarded =       totalNumberOfActions.getSum() ; 
			
			//--------------------------------------------UP and DOWN arrow logic implementation------------------------------------------		
			
			  
			//  contractsTransactionDataPreviousList = contractsTransactionDataRepository.findByVendorDunsNumberAndStartDateEndDate(id,previousYearDate,datestart);  
				
			
			contractsTransactionDataPreviousList = contractsTransactionDataRepository.findByAgencyCodeAndStartDateEndDate(agencyCode,previousYearDate,datestart); 
			
			// System.out.println("-------------3----------------"+contractsTransactionDataPreviousList.size());
			
			 
			 
			 vendorsDunsNumberPreviousList = contractsTransactionDataPreviousList.stream().map(e -> e.getVendorDunsNumber()).distinct().collect(Collectors.toList());
			
			  
			  DoubleSummaryStatistics obligateAmountPrevious =  	contractsTransactionDataPreviousList.stream().collect(Collectors.summarizingDouble(ContractsTransactionData::getDollarsObligated));
			  DoubleSummaryStatistics totalNumberOfActionsPrevious =  contractsTransactionDataPreviousList.stream().collect(Collectors.summarizingDouble(ContractsTransactionData::getNumberOfActions));
			      
			    long  contratctsReceivedArrow = (long) (obligateAmount.getSum() - obligateAmountPrevious.getSum()) ;
		        if(contratctsReceivedArrow > 0) {
		        	contratctsReceivedStatus=1;
		        	
		        }else {
		        	contratctsReceivedStatus=0;
		        }
		        
		        long  contratctsAwardedArrow = (long) (totalNumberOfActions.getSum() - totalNumberOfActionsPrevious.getSum()) ;
		        if(contratctsAwardedArrow > 0) {
		        	 contratctsAwardedStatus=1;
		        }else {
		        	   contratctsAwardedStatus=0;
		         }
			  
		          long activeAwardsPrevious = contractsTransactionDataPreviousList.stream().filter(e ->  e.getUltimateCompletionDate()== null  ||  ( e.getUltimateCompletionDate().before(previousYearDate ) &&  e.getUltimateCompletionDate().after(datestart)) ).count();
			
				  long  activeAwardArrow = activeAwards - activeAwardsPrevious ;
				        if(activeAwardArrow > 0) {
				        	activeAwardsStatus =1;
				        	
				        }else {
				        	activeAwardsStatus =0;
				        }
				        
				
				        
						/*
						 * System.out.println("----------vendorsDunsNumberPreviousList------------"+
						 * vendorsDunsNumberPreviousList.size());
						 * vendorsDunsNumberPreviousList.forEach((Integer vendorsDunNumber) -> {
						 * 
						 * OpportunityInterestedVendor opportunityInterestedVendor =
						 * opportunityInterestedVendorRepository.findByEntityId(vendorsDunNumber.
						 * toString());
						 * 
						 * 
						 * if(opportunityInterestedVendor != null) {
						 * 
						 * activeOpportunitiesPreviousCounter++ ; } });
						 */
				        
				        activeOpportunitiesPreviousCounter =   vendorsDunsNumberPreviousList.size(); 
			    long  activeOpportunitiesStatus = (long) (activeOpportunitiesCounter - activeOpportunitiesPreviousCounter) ;
		        if(activeOpportunitiesStatus > 0) {
		        	activeOpportunitiesStatus=1;
		        }else {
		        	  activeOpportunitiesStatus=0;
		         }
			 //----------------------------------------------------------------------------------------------------------------------------------------------------- 
		
		}
			
			
			
			// generate Response
			//contractsTransactionDataList = contractsTransactionDataList.stream().limit(1).collect(Collectors.toList());
			Map<String, Object> response = new HashMap<>();
			//response.put("vendors", contractsTransactionDataList);
			//response.put("activeAwardsWith", departmentLogosList);
			//response.put("totalAward", countTotalAwards.get(id));
			//response.put("agencyName", contractAgencyName);
			response.put("contratctsReceived",totalAmt);
		    response.put("contratctsAwarded", contratctsAwarded);
		    response.put("activeAwards", activeAwards);
		    response.put("activeOpportunities", activeOpportunitiesCounter );
		    response.put("uniqueVendors", uniqueVendors);
			response.put("contratctsReceivedStatus", contratctsReceivedStatus);
			response.put("contratctsAwardedStatus", contratctsAwardedStatus);
			response.put("activeAwardsStatus", activeAwardsStatus);
			response.put("uniqueVendorStatus", uniqueVendorStatus);
			response.put("activeOpportunityStatus", activeOpportunityStatus);
			contractsTransactionDataList = null;
			return new ResponseEntity<>(response, HttpStatus.OK);
		
			
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	//----------------------------------------------------------------------------------------------------------------	
		
		
// TOP 5 NAICS PSC and VENDOR-------------------------------------------------------------------------------------------		
	
		
		
		@GetMapping("/topNaicsPsc/{agencyCodeId}")
		public ResponseEntity<Map<String, Object>> getTopNaicsPscDateWise(@PathVariable Integer agencyCodeId , @RequestParam(name="start", required = false)  String startDate,@RequestParam(name="end", required = false)  String endDate) {
				
			try {
				String agencyCode = agencyCodeId.toString();
				ContractsTransactionData checkContractsTransactionIsEmpty = contractsTransactionDataRepository.findOneByAgencyConde(agencyCode);
				if (checkContractsTransactionIsEmpty == null) {
					return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
				}
				
				if (checkContractsTransactionIsEmpty != null) {
					//  System.out.println(agencyCodeId+"---startDate-------"+startDate+"----endDate----"+endDate);
				      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy"); 
			          LocalDate start_date = LocalDate.parse(startDate.trim() , formatter); 
			          LocalDate end_date = LocalDate.parse(endDate.trim() , formatter); 
			          
			          Date datestart = Date.valueOf(start_date);
			          Date dateend = Date.valueOf(end_date);
			         contractsTransactionDataList = contractsTransactionDataRepository.findByAgencyCodeAndStartDateEndDate(agencyCode,datestart,dateend);
			         vendorsList = contractsTransactionDataList.stream()
								.sorted(Comparator.comparingDouble(ContractsTransactionData::getDollarsObligated).reversed())
								.map(e -> e.getVendorName()).distinct().limit(5).collect(Collectors.toList());
						naicsmap = contractsTransactionDataList.stream()
								.sorted(Comparator.comparingDouble(ContractsTransactionData::getDollarsObligated).reversed())
								.limit(5)
								.collect(Collectors.toMap(ContractsTransactionData::getNaicsCode,
										ContractsTransactionData::getNaicsDescription, // key = name, value = val
										(oldValue, newValue) -> oldValue, // if same key, take the old key
										LinkedHashMap::new // returns a LinkedHashMap, keep order
								));

						naicsmapList = new ArrayList<String>();
						naicsmap.forEach((key, value) -> {naicsmapList.add(key + "-" + value);});
						pscmap = contractsTransactionDataList.stream()
								.sorted(Comparator.comparingDouble(ContractsTransactionData::getDollarsObligated).reversed())
								.distinct().limit(5)
								.collect(Collectors.toMap(ContractsTransactionData::getProductOrServiceCode,
										ContractsTransactionData::getProductOrServiceDescription, // key = name, value =
																									// websites
										(oldValue, newValue) -> oldValue, // if same key, take the old key
										LinkedHashMap::new // returns a LinkedHashMap, keep order
								));

						pscmapList = new ArrayList<String>();
						pscmap.forEach((key, value) -> {pscmapList.add(key + "-" + value);});
			         
			    
				}
				
				// generate Response
			
				Map<String, Object> response = new HashMap<>();
				response.put("vendors", vendorsList);
				response.put("naics", naicsmapList);
				response.put("psc", pscmapList);
				contractsTransactionDataList = null;
				return new ResponseEntity<>(response, HttpStatus.OK);
			
				
			} catch (Exception e) {
				return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
			
		
	// Contracts that need attention below 2.5 rating -----------------------------------------------------------------------
		
		
		@GetMapping("/contractsAttention/{agencyCodeId}")
		public ResponseEntity<Map<String, Object>> getVendorBelowRatingDateWise(@PathVariable Integer agencyCodeId , @RequestParam(name="start", required = false)  String startDate,@RequestParam(name="end", required = false)  String endDate) {
				
			try {
				String agencyCode = agencyCodeId.toString();
				ContractsTransactionData checkContractsTransactionIsEmpty = contractsTransactionDataRepository.findOneByAgencyConde(agencyCode);
				if (checkContractsTransactionIsEmpty == null) {
					return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
				}
				
				if (checkContractsTransactionIsEmpty != null) {
					//  System.out.println(agencyCodeId+"---startDate-------"+startDate+"----endDate----"+endDate);
				      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy"); 
			          LocalDate start_date = LocalDate.parse(startDate.trim() , formatter); 
			          LocalDate end_date = LocalDate.parse(endDate.trim() , formatter); 
			          
			          Date datestart = Date.valueOf(start_date);
			          Date dateend = Date.valueOf(end_date);
			         contractsTransactionDataList = contractsTransactionDataRepository.findByAgencyCodeAndStartDateEndDate(agencyCode,datestart,dateend);
					 vendorsDunsNumberList = contractsTransactionDataList.stream().map(e -> e.getVendorDunsNumber()).distinct().collect(Collectors.toList());
					 vendorsDunsNumberList.forEach((Integer vendorsDunNumber) -> {
							
						 
		            		Double avgRatingVal = opportunityVendorsPpRepository
									.findAverageByVendorDunsNumber(vendorsDunNumber.toString());

							if (avgRatingVal != null) {
								avgRating.put(vendorsDunNumber.toString(), avgRatingVal);
							}

						});

						for (Map.Entry<String, Double> entry : avgRating.entrySet()) {
							// System.out.println(entry.getKey() + ":" + entry.getValue());
							if (entry.getValue() < 2.5) {
								ratingAvg = BigDecimal.valueOf(entry.getValue());
								ratingAvg = ratingAvg.setScale(2, RoundingMode.HALF_UP);
								ratingBelowMap.put(entry.getKey(), ratingAvg.doubleValue());
							}
						}

					}
					contractsTransactionSet =  new HashSet<ContractsTransactionData>();
					ratingBelowMap.forEach((dunsNumber, avgRating) -> {

						Integer dunsNumberInt = null;
						dunsNumberInt = Integer.parseInt(dunsNumber);
						contractsList = contractsTransactionDataRepository
								.findByVendorDunsNumberAndAgencyCode(dunsNumberInt, agencyCode).stream()
								.collect(Collectors.toList());

						if (!contractsList.isEmpty()) {
							contractsList.get(0).setAverageRating(avgRating);

							contractsTransactionList = Optional.ofNullable(contractsList).map(Collection::stream)
									.orElseGet(Stream::empty).collect(Collectors.toList());

							for (ContractsTransactionData c : contractsTransactionList) {
								 contractsTransactionSet.add(c);
							}
						}
					});

					List<Double> averageRating = avgRating.entrySet().stream().map(e -> e.getValue())
							.collect(Collectors.toList());
					averageRatingResult = averageRating.stream().mapToDouble(a -> a).average().getAsDouble();

					 
			      
				// generate Response
				//contractsTransactionDataList = contractsTransactionDataList.stream().limit(1).collect(Collectors.toList());
				Map<String, Object> response = new HashMap<>();
				response.put("averageVendorRating", df.format(averageRatingResult));
				response.put("contractsTransaction", contractsTransactionSet);
			    contractsTransactionDataList = null;
				return new ResponseEntity<>(response, HttpStatus.OK);
			
				
			} catch (Exception e) {
				return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		
// average rating and active awards------------------------------------------------------------------------------------------------------------------------------	
	

		@GetMapping("/averageRating/{agencyCodeId}")
		public ResponseEntity<Map<String, Object>> getAverageRatingActiveAwardsDateWise(@PathVariable Integer agencyCodeId , @RequestParam(name="start", required = false)  String startDate,@RequestParam(name="end", required = false)  String endDate) {
				
			try {
				String agencyCode = agencyCodeId.toString();
				ContractsTransactionData checkContractsTransactionIsEmpty = contractsTransactionDataRepository.findOneByAgencyConde(agencyCode);
				if (checkContractsTransactionIsEmpty == null) {
					return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
				}
				
				if (checkContractsTransactionIsEmpty != null) {
					//  System.out.println(agencyCodeId+"---startDate-------"+startDate+"----endDate----"+endDate);
				      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy"); 
			          LocalDate start_date = LocalDate.parse(startDate.trim() , formatter); 
			          LocalDate end_date = LocalDate.parse(endDate.trim() , formatter); 
			          
			          Date datestart = Date.valueOf(start_date);
			          Date dateend = Date.valueOf(end_date);
			          LocalDate previousYear = start_date.minus(1, ChronoUnit.YEARS); 
					  Date  previousYearDate =  Date.valueOf(previousYear) ;
			          
			          
			      	contractsTransactionDataPreviousList = contractsTransactionDataRepository.findByAgencyCodeAndStartDateEndDate(agencyCode,previousYearDate,datestart); 
				    contractsTransactionDataList = contractsTransactionDataRepository.findByAgencyCodeAndStartDateEndDate(agencyCode,datestart,dateend);
			     	 activeAwards = contractsTransactionDataList.stream().filter(e ->  e.getUltimateCompletionDate()== null  ||  ( e.getUltimateCompletionDate().before(datestart) &&  e.getUltimateCompletionDate().after(dateend)) ).count();
					 vendorsDunsNumberList = contractsTransactionDataList.stream().map(e -> e.getVendorDunsNumber()).distinct().collect(Collectors.toList());
					
					 vendorsDunsNumberPreviousList = contractsTransactionDataPreviousList.stream().map(e -> e.getVendorDunsNumber()).distinct().collect(Collectors.toList());
						
					 
					 vendorsDunsNumberList.forEach((Integer vendorsDunNumber) -> {
							
						 
		            		Double avgRatingVal = opportunityVendorsPpRepository
									.findAverageByVendorDunsNumber(vendorsDunNumber.toString());

							if (avgRatingVal != null) {
								avgRating.put(vendorsDunNumber.toString(), avgRatingVal);
							}

						});
				
				
				List<Double> averageRating = avgRating.entrySet().stream().map(e -> e.getValue()).collect(Collectors.toList());
				averageRatingResult = averageRating.stream().mapToDouble(a -> a).average().getAsDouble();
				
				vendorsDunsNumberPreviousList.forEach((Integer vendorsDunNumber) -> {
					// System.out.println("-------vendorsDunsNumberPreviousList-----------"+vendorsDunNumber) ;
					 
	        		Double avgRatingVal = opportunityVendorsPpRepository.findAverageByVendorDunsNumber(vendorsDunNumber.toString());

					if (avgRatingVal != null) {
						avgRatingPrevious.put(vendorsDunNumber.toString(), avgRatingVal);
					}
					
				  });
			
					List<Double> averageRatingPrevious = avgRatingPrevious.entrySet().stream().map(e -> e.getValue()).collect(Collectors.toList());
					double averageRatingPreviousResult = averageRatingPrevious.stream().mapToDouble(a -> a).average().getAsDouble();
					double  averageRatingArrow =  averageRatingResult - averageRatingPreviousResult ;
			        if(averageRatingArrow > 0) {
			        	averageRatingStatus=1;
			        }else {
			        	averageRatingStatus=0;
			         }
				
			        
			        long activeAwardsPrevious = contractsTransactionDataPreviousList.stream().filter(e ->  e.getUltimateCompletionDate()== null  ||  ( e.getUltimateCompletionDate().before(previousYearDate ) &&  e.getUltimateCompletionDate().after(datestart)) ).count();
					
					  long  activeAwardArrow = activeAwards - activeAwardsPrevious ;
					        if(activeAwardArrow > 0) {
					        	activeAwardsStatus =1;
					        	
					        }else {
					        	activeAwardsStatus =0;
					        }
			
			}

				// generate Response
				//contractsTransactionDataList = contractsTransactionDataList.stream().limit(1).collect(Collectors.toList());
				Map<String, Object> response = new HashMap<>();
				
			    response.put("averageVendorRating", df.format(averageRatingResult));
			    response.put("activeAwards", activeAwards);
			    response.put("activeAwardsStatus", activeAwardsStatus);
			    response.put("averageRatingStatus", averageRatingStatus);
				contractsTransactionDataList = null;
				return new ResponseEntity<>(response, HttpStatus.OK);
			
				
			} catch (Exception e) {
				return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}		

	
//-----------year and quarterly half yearly agency details-------------------------------------------------------------------------------------------------------------------------------------		
		
		@SuppressWarnings("unchecked")
		@GetMapping("/agencyInformation/{agencyCodeId}")
		public ResponseEntity<Map<String, Object>> getContractTrasactionYearWise(@PathVariable Integer agencyCodeId , @RequestParam(name="year", required = false)  String year,@RequestParam(name="quarter", required = false)  String quarter) {

			try {
				String agencyCode = agencyCodeId.toString();
				ContractsTransactionData checkContractsTransactionIsEmpty = contractsTransactionDataRepository.findOneByAgencyConde(agencyCode);
				if (checkContractsTransactionIsEmpty == null) {

					return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
				}
				
			

				if (checkContractsTransactionIsEmpty != null) {
					String startDate =  null ;
					String endDate   =  null ;
					
					String condition =  quarter.trim() ;
					
					switch (condition) {
			        case "q1":
			        	
			        	startDate = "01-01-"+year.trim();
			        	endDate   = "31-03-"+year.trim();
			          
			            break;
			        case "q2":
			        	startDate = "01-01-"+year.trim();
			        	endDate   = "30-06-"+year.trim();
			            break;
			        case "q3":
			        	startDate = "01-01-"+year.trim();
			        	endDate   = "30-09-"+year.trim();
			            break;
			        default:
			        	startDate = "01-01-"+year.trim();
			        	endDate   = "31-12-"+year.trim();
			            break;
			       }
					
					
				//	System.out.println("-----startDate------"+startDate);
				//	System.out.println("---------endDate----------"+endDate);
					
					  DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy"); 
			          LocalDate start_date = LocalDate.parse(startDate.trim() , formatter); 
			          LocalDate end_date = LocalDate.parse(endDate.trim() , formatter); 
			          
			          Date datestart = Date.valueOf(start_date);
			          Date dateend = Date.valueOf(end_date);
			          
			       //   System.out.println("-----startDate------"+datestart);
					//  System.out.println("---------endDate----------"+dateend);
						
			         contractsTransactionDataList = contractsTransactionDataRepository.findByAgencyCodeAndStartDateEndDate(agencyCode,datestart,dateend);
			
				
			         //	System.out.println("-----------1--------------");
			 	
					//contractsTransactionDataList = contractsTransactionDataRepository.findByAgencyCode(agencyCode);
					//  sub organization or office or sub-tire-----------------------------------------------
					  String organizationName =checkContractsTransactionIsEmpty.getContractingDepartmentName() ;
					 
					  List<AgencyReferenceData> organizationNameList =agencyReferenceDataRepository.findByOrganizationName( organizationName ) ;
					
					    for (AgencyReferenceData agencyRefData : organizationNameList) {
					    	subOfficeMap.put(agencyRefData.getOrganizationCode(), agencyRefData.getOrganizationName());
					    }
					  
					   // subOfficeMap.forEach((a,b)-> System.out.println(a+"----------"+b));
					  //  System.out.println("-----------2--------------");
					
					// Active Award based on agency code---------------------------
				//	Date today = Date.valueOf(LocalDate.now());
				//	activeAwards = contractsTransactionDataList.stream().filter(e -> e.getUltimateCompletionDate() == null || today.after(e.getUltimateCompletionDate())).count();

					 	 activeAwards = contractsTransactionDataList.stream().filter(e ->  e.getUltimateCompletionDate()== null  ||  ( e.getUltimateCompletionDate().before(datestart) &&  e.getUltimateCompletionDate().after(dateend)) ).count();
							  
					    
					vendorsList = contractsTransactionDataList.stream()
							.sorted(Comparator.comparingDouble(ContractsTransactionData::getDollarsObligated).reversed())
							.map(e -> e.getVendorName()).distinct().limit(5).collect(Collectors.toList());
					
				//	System.out.println("-----------3--------------");
					naicsmap = contractsTransactionDataList.stream()
							.sorted(Comparator.comparingDouble(ContractsTransactionData::getDollarsObligated).reversed())
							.limit(5)
							.collect(Collectors.toMap(ContractsTransactionData::getNaicsCode,
									ContractsTransactionData::getNaicsDescription, // key = name, value = val
									(oldValue, newValue) -> oldValue, // if same key, take the old key
									LinkedHashMap::new // returns a LinkedHashMap, keep order
							));
					
					//System.out.println("-----------4--------------");

					naicsmapList = new ArrayList<String>();
					naicsmap.forEach((key, value) -> {
						naicsmapList.add(key + "-" + value);
					});
					pscmap = contractsTransactionDataList.stream()
							.sorted(Comparator.comparingDouble(ContractsTransactionData::getDollarsObligated).reversed())
							.distinct().limit(5)
							.collect(Collectors.toMap(ContractsTransactionData::getProductOrServiceCode,
									ContractsTransactionData::getProductOrServiceDescription, // key = name, value =
																								// websites
									(oldValue, newValue) -> oldValue, // if same key, take the old key
									LinkedHashMap::new // returns a LinkedHashMap, keep order
							));

					pscmapList = new ArrayList<String>();
					pscmap.forEach(( key, value) -> {
						pscmapList.add(key + "-" + value);
					});

					//System.out.println("-----------5--------------");
					vendorsCountList = contractsTransactionDataList.stream().map(e -> e.getVendorName()).distinct()
							.collect(Collectors.toList());

					uniqueVendors = vendorsCountList.stream().count();
					
					contratctsAwarded = contractsTransactionDataList.stream()
							.collect(Collectors.summingDouble(ContractsTransactionData::getDollarsObligated));
					contratctsAwardedCount = contractsTransactionDataList.stream()
							.collect(Collectors.summingLong(ContractsTransactionData::getNumberOfActions));
					organizationDataList = agencyReferenceDataRepository.findByOrganizationCode(agencyCode).stream()
							.limit(1).collect(Collectors.toList());
					vendorsDunsNumberList = contractsTransactionDataList.stream().map(e -> e.getVendorDunsNumber()).distinct().collect(Collectors.toList());
					
					//System.out.println("-----------6-------------");
				// active opportunities ---------------------------------------------------------------------------------------------------
					
					 vendorsDunsNumberList.forEach((Integer vendorsDunNumber) -> {
				
						 OpportunityInterestedVendor opportunityInterestedVendor = opportunityInterestedVendorRepository.findByEntityId(vendorsDunNumber.toString());
							 if(opportunityInterestedVendor != null) {
								 
							  activeOpportunitiesCounter++ ;
						  }
					 });
			//	---------------------------------------------------------------------------------------------------------	
			
					// System.out.println("-----------7-------------");
					 vendorsDunsNumberList.forEach((Integer vendorsDunNumber) -> {
						
				 
	            		Double avgRatingVal = opportunityVendorsPpRepository
								.findAverageByVendorDunsNumber(vendorsDunNumber.toString());

						if (avgRatingVal != null) {
							avgRating.put(vendorsDunNumber.toString(), avgRatingVal);
						}

					});
					 
					// System.out.println("-----------8--------------");

					for (Map.Entry<String, Double> entry : avgRating.entrySet()) {
						// System.out.println(entry.getKey() + ":" + entry.getValue());
						if (entry.getValue() < 2.5) {
							ratingAvg = BigDecimal.valueOf(entry.getValue());
							ratingAvg = ratingAvg.setScale(2, RoundingMode.HALF_UP);
							ratingBelowMap.put(entry.getKey(), ratingAvg.doubleValue());
						}
					}

				}
				
				//System.out.println("-----------9--------------");
				contractsTransactionSet =  new HashSet<ContractsTransactionData>();
				ratingBelowMap.forEach((dunsNumber, avgRating) -> {

					Integer dunsNumberInt = null;
					dunsNumberInt = Integer.parseInt(dunsNumber);
					contractsList = contractsTransactionDataRepository
							.findByVendorDunsNumberAndAgencyCode(dunsNumberInt, agencyCode).stream()
							.collect(Collectors.toList());

					if (!contractsList.isEmpty()) {
						contractsList.get(0).setAverageRating(avgRating);

						contractsTransactionList = Optional.ofNullable(contractsList).map(Collection::stream)
								.orElseGet(Stream::empty).collect(Collectors.toList());

						for (ContractsTransactionData c : contractsTransactionList) {
							 contractsTransactionSet.add(c);
						}
					}
				});
                
				//System.out.println("-----------10--------------");
				List<Double> averageRating = avgRating.entrySet().stream().map(e -> e.getValue())
						.collect(Collectors.toList());
				averageRatingResult = averageRating.stream().mapToDouble(a -> a).average().getAsDouble();

				smallBusinessGoal = smallBusinessGoalRepository.findByOrganizationCode(agencyCode);
				targetAmt = new BigDecimal(Double.toString(smallBusinessGoal.getTargetAmount()));

				totalAmt = new BigDecimal(Double.toString(contratctsAwarded));

				targetAcheivedPercent = (contratctsAwarded / smallBusinessGoal.getTargetAmount()) * 100;

			//	System.out.println("-----------11--------------");
				Map<String, Object> response = new HashMap<>();
				response.put("vendors", vendorsList);
				response.put("naics", naicsmapList);
				response.put("psc", pscmapList);
				response.put("organization", organizationDataList);
				response.put("averageVendorRating", df.format(averageRatingResult));
				response.put("contractsTransaction", contractsTransactionSet);
				response.put("targetAmount", targetAmt);
				response.put("targetAcheivedPercent", df.format(targetAcheivedPercent));
				response.put("uniqueVendors", uniqueVendors);
				response.put("contratctsAwarded", totalAmt);
				response.put("contratctsAwardedCount", contratctsAwardedCount);
				response.put("activeAwards", activeAwards);
				response.put("activeOpportunities", activeOpportunitiesCounter);
				response.put("subOffice", subOfficeMap);
				response.put("contratctsReceivedStatus", contratctsReceivedStatus);
				response.put("contratctsAwardedStatus", contratctsAwardedStatus);
				response.put("activeAwardsStatus", activeAwardsStatus);
				response.put("uniqueVendorStatus", uniqueVendorStatus);
				response.put("activeOpportunityStatus", activeOpportunityStatus);
				response.put("averageRatingStatus", averageRatingStatus);
				
				
				// response.put("currentPage", contractsTransactionDataPage.getNumber());
				// response.put("totalItems",contractsTransactionDataPage.getTotalElements());
				// response.put("totalPages", contractsTransactionDataPage.getTotalPages());

				return new ResponseEntity<>(response, HttpStatus.OK);
			} catch (Exception e) {

				e.printStackTrace();
				return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}		
		
		
		
		
//---------------------------------------------------------------------------------------------------------------------------------	
		
	
	
	
	

	// Utility function
	public static <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor) {
		Map<Object, Boolean> map = new ConcurrentHashMap<>();
		return t -> map.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
	}

	public static <T> Set<T> findDuplicateBySetAdd(List<T> list) {

		Set<T> items = new HashSet<>();
		return list.stream().filter(n -> !items.add(n)) // Set.add() returns false if the element was already in the
														// set.
				.collect(Collectors.toSet());

	}

	public static <T> Set<T> findDuplicateByGrouping(List<T> list) {

		return list.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting())) // create a map
																										// {1=1, 2=1,
																										// 3=2, 4=2,
																										// 5=1, 7=1,
																										// 9=2}
				.entrySet().stream() // Map -> Stream
				.filter(m -> m.getValue() > 1) // if map value > 1, duplicate element
				.map(Map.Entry::getKey).collect(Collectors.toSet());

	}

}
