package com.java.controller;

import java.math.BigDecimal;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
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

import com.java.Repository.AgencyReferenceDataRepository;
import com.java.Repository.ContractsDataAggregatedByVendorNaicsPscRepository;
import com.java.Repository.ContractsDataAggregatedByVendorRepository;
import com.java.Repository.ContractsTransactionDataRepository;
import com.java.Repository.DepartmentLogosRepository;
import com.java.Repository.NaicsCodeDataRepository;
import com.java.Repository.OpportunityInterestedVendorRepository;
import com.java.Repository.OpportunityVendorsPpRepository;
import com.java.Repository.ProductServiceCodeDataRepository;
import com.java.model.AgencyReferenceData;
import com.java.model.ContractsDataAggregatedByVendor;
import com.java.model.ContractsDataAggregatedByVendorNaicsPsc;
import com.java.model.ContractsTransactionData;
import com.java.model.DepartmentLogos;
import com.java.model.NaicsCodeData;
import com.java.model.OpportunityInterestedVendor;
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
	@Autowired
	public DepartmentLogosRepository departmentLogosRepository;

	@Autowired
	AgencyReferenceDataRepository agencyReferenceDataRepository;
	
	@Autowired
	ContractsDataAggregatedByVendorRepository contractsDataAggregatedByVendorRepository ;
	@Autowired
	public OpportunityInterestedVendorRepository opportunityInterestedVendorRepository ;

	public List<NaicsCodeData> naicsCodeDataList = new ArrayList<NaicsCodeData>();

	public List<NaicsCodeData> naicsCodeDataListAll = new ArrayList<NaicsCodeData>();

	public List<ProductServiceCodeData> productServiceCodeDataList = new ArrayList<ProductServiceCodeData>();
	public List<ProductServiceCodeData> pscCodeDataListAll = new ArrayList<ProductServiceCodeData>();

	public List<ContractsDataAggregatedByVendorNaicsPsc> vendorList = new ArrayList<ContractsDataAggregatedByVendorNaicsPsc>();

	public List<List<ContractsDataAggregatedByVendorNaicsPsc>> vendorNaicsPscListAll = new ArrayList<List<ContractsDataAggregatedByVendorNaicsPsc>>();

	public List<ContractsTransactionData> contractsTransactionDataList = new ArrayList<ContractsTransactionData>();
	
	public List<ContractsTransactionData> contractsTransactionDataPreviousList = new ArrayList<ContractsTransactionData>();
	

	public List<OpportunityVendorsPp> ratingList = new ArrayList<OpportunityVendorsPp>();
	public DepartmentLogos departmentLogos;
	public List<DepartmentLogos> departmentLogosList = new ArrayList<DepartmentLogos>();
	public Map<Integer, List<DepartmentLogos>> departmentLogosMap = new HashMap<Integer, List<DepartmentLogos>>();
	public  List<ContractsDataAggregatedByVendor> contractsDataAggregatedByVendorList =  new ArrayList<ContractsDataAggregatedByVendor>();
 	
	public  long  activeAwards  ;
	public  Long  contratctsAwardedCount  ;
	public  double contratctsAwarded ;
	public int  activeOpportunitiesCounter  = 0 ;
	BigDecimal totalAmt ;
	public  int contratctsReceivedStatus ;
	public  int contratctsAwardedStatus ;
	public  int activeAwardsStatus ;
	
	
	//public Map<String,Integer> contractInfromationUpDownStatus = new HashMap<>();
	
	//public Map<String, > ratingBelowMap = new HashMap<>();
	

	// all vendors
	// --------------------------------------------------------------------------------------------------------
	@GetMapping("/vendors")
	public ResponseEntity<Map<String, Object>> getVendors(@RequestParam(defaultValue = "0") Integer pageNo,
			@RequestParam(defaultValue = "10") Integer pageSize, @RequestParam(required = false) String title) {

		try {
			List<ContractsDataAggregatedByVendorNaicsPsc> vendorNaicsPscList = new ArrayList<ContractsDataAggregatedByVendorNaicsPsc>();
			Pageable paging = PageRequest.of(pageNo, pageSize);
			Page<ContractsDataAggregatedByVendorNaicsPsc> pageVendorNaicsPsc;
			pageVendorNaicsPsc = contractsDataAggregatedByVendorNaicsPscRepository.findAll(paging);
			vendorNaicsPscList = pageVendorNaicsPsc.getContent();

			// --------------------------------------------------------------------------------------------------------------
			vendorNaicsPscList.forEach(e -> {
			//	System.out.println("--------------------------------------------");
			//	System.out.println("vendors Id ----" + e.getVendorDunsNumber());
				departmentLogosList = new ArrayList<DepartmentLogos>();
				contractsTransactionDataList = contractsTransactionDataRepository
						.findByVendorDunsNumber(e.getVendorDunsNumber());
				/*
				 * List<String> organizationName = contractsTransactionDataList.stream()
				 * .map(ContractsTransactionData::getContractingDepartmentName).distinct()
				 * .collect(Collectors.toList()); organizationName.forEach(orgName -> {
				 * //System.out.println("orgName---------" + orgName); AgencyReferenceData
				 * organizationCode = agencyReferenceDataRepository
				 * .findByOrganizationNameAndType(orgName, "DEPARTMENT");
				 * System.out.println("organizationCode------" +
				 * organizationCode.getOrganizationCode()); Integer organizatinCode =
				 * Integer.valueOf(organizationCode.getOrganizationCode()); departmentLogos =
				 * departmentLogosRepository.findByFpdsCode(organizatinCode);
				 * //System.out.println("departmentLogos --------" + departmentLogos);
				 * departmentLogosList.add(departmentLogos); });
				 */
				// departmentLogosList.removeIf(Objects :: isNull);
				// departmentLogosList =
				// departmentLogosList.stream().distinct().collect(Collectors.toList());
				//System.out.println("departmentLogosList-------" + departmentLogosList);
				departmentLogosMap.put(e.getVendorDunsNumber(), departmentLogosList);
				// departmentLogosList = null ;

			});
			// -------------------------------------------------------------------------------------------------------------
			Map<String, Object> response = new HashMap<>();
			response.put("logos", departmentLogosMap);
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
			//vendorList = contractsDataAggregatedByVendorNaicsPscRepository.findByVendorDunsNumber(id);
			departmentLogosList = new ArrayList<DepartmentLogos>();
			// count total award contract transcation data
				 
	      //	--------------------------------------------UP and DOWN arrow logic implementation--------------------------------------------------------------------------	
			  LocalDate currentDate = LocalDate.now();
			  Date  today =  Date.valueOf(currentDate) ;
			  LocalDate previousYear = currentDate.minus(2, ChronoUnit.YEARS);
			  Date  firstPreviousYearDate =  Date.valueOf(previousYear) ;
			  LocalDate nextYear = previousYear.minus(1, ChronoUnit.YEARS); 
			  Date  secondPreviouYearDate =  Date.valueOf(nextYear) ;
		 	
			  
			//  System.out.println("current year ---"+ today  );
			//  System.out.println("previousYear ---"+ firstPreviousYearDate);
			//  System.out.println("nextYearDate ---"+  secondPreviouYearDate);
			  contractsTransactionDataList = contractsTransactionDataRepository.findByVendorDunsNumberAndStartDateEndDate(id,today,firstPreviousYearDate);  
			  
			  contractsTransactionDataPreviousList = contractsTransactionDataRepository.findByVendorDunsNumberAndStartDateEndDate(id,firstPreviousYearDate,secondPreviouYearDate);  
			  
			  long activeAwardsPrevious = contractsTransactionDataPreviousList.stream().filter(e ->  e.getUltimateCompletionDate()== null  ||  ( e.getUltimateCompletionDate().before(firstPreviousYearDate ) &&  e.getUltimateCompletionDate().after(secondPreviouYearDate)) ).count();
			  activeAwards = contractsTransactionDataList.stream().filter(e ->  e.getUltimateCompletionDate()== null  || today.before( e.getUltimateCompletionDate())  ).count();

			  long  activeAwardArrow = activeAwards - activeAwardsPrevious ;
			        if(activeAwardArrow > 0) {
			        	activeAwardsStatus =1;
			        	
			        }else {
			        	activeAwardsStatus =0;
			        }
			  
			        
			         DoubleSummaryStatistics obligateAmountPrevious =  	contractsTransactionDataPreviousList.stream().collect(Collectors.summarizingDouble(ContractsTransactionData::getDollarsObligated));
				     DoubleSummaryStatistics totalNumberOfActionsPrevious =  contractsTransactionDataPreviousList.stream().collect(Collectors.summarizingDouble(ContractsTransactionData::getNumberOfActions));
				     DoubleSummaryStatistics obligateAmount =  	contractsTransactionDataList.stream().collect(Collectors.summarizingDouble(ContractsTransactionData::getDollarsObligated));
    				 DoubleSummaryStatistics totalNumberOfActions =  	contractsTransactionDataList.stream().collect(Collectors.summarizingDouble(ContractsTransactionData::getNumberOfActions));
				    
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
				   
				
			          List<OpportunityInterestedVendor> opportunityInterestedVendor = opportunityInterestedVendorRepository.findByVendorDunsNumberAndStartDateEndDate(id.toString(),today,firstPreviousYearDate);
					
					  if(opportunityInterestedVendor != null) {
								 while (activeOpportunitiesCounter < opportunityInterestedVendor.size() ) {
									 	activeOpportunitiesCounter++ ;
								  
								 }
								 
						  }
			        
					  
			
					   List<OpportunityInterestedVendor> opportunityInterestedVendorPrevious = opportunityInterestedVendorRepository.findByVendorDunsNumberAndStartDateEndDate(id.toString(),firstPreviousYearDate,secondPreviouYearDate);  
						
						 int  activeOpportunitiesCounterPrevious = 0;
					   if(opportunityInterestedVendorPrevious != null) {
									 while (activeOpportunitiesCounterPrevious < opportunityInterestedVendorPrevious.size() ) {
										 activeOpportunitiesCounterPrevious++ ;
									  
									 }
									 
							  }
					  
			
			    long  activeOpportunitiesStatus = (long) (activeOpportunitiesCounter - activeOpportunitiesCounterPrevious) ;
		        if(activeOpportunitiesStatus > 0) {
		        	activeOpportunitiesStatus=1;
		        }else {
		        	  activeOpportunitiesStatus=0;
		         }
			 
			 List<String> organizationName = contractsTransactionDataList.stream()
					.map(ContractsTransactionData::getContractingDepartmentName).distinct()
					.collect(Collectors.toList());
			organizationName.forEach(orgName -> {
				//System.out.println("orgName---------" + orgName);
				AgencyReferenceData organizationCode = agencyReferenceDataRepository
						.findByOrganizationNameAndType(orgName, "DEPARTMENT");
				//System.out.println("organizationCode------" + organizationCode.getOrganizationCode());
				Integer organizatinCode = Integer.valueOf(organizationCode.getOrganizationCode());
				departmentLogos = departmentLogosRepository.findByFpdsCode(organizatinCode);
				//System.out.println("departmentLogos --------" + departmentLogos);
				departmentLogosList.add(departmentLogos);
			});
			
			departmentLogosList = departmentLogosList.stream().distinct().collect(Collectors.toList());
			
			
			/*
			 * String organizationName = contractsTransactionDataList.stream()
			 * .map(ContractsTransactionData::getContractingDepartmentName).findFirst().
			 * orElse(null); AgencyReferenceData organizationCode =
			 * agencyReferenceDataRepository
			 * .findByOrganizationNameAndType(organizationName, "DEPARTMENT"); Integer
			 * organizatinCode = Integer.valueOf(organizationCode.getOrganizationCode());
			 * departmentLogos = departmentLogosRepository.findByFpdsCode(organizatinCode);
			 */
		
			// System.out.println("-----------organizationCode-------------" + departmentLogos.getName());
			 List<String> contractAgencyName =contractsTransactionDataList.stream().filter(e ->e.getVendorDunsNumber().equals(id)).map(ContractsTransactionData::getContractingAgencyName).collect(Collectors.toList());
			 contractAgencyName = contractAgencyName.stream().filter( distinctByKey(p ->p.toString()) ).collect(Collectors.toList());
			 
			
			   totalAmt = new BigDecimal(Double.toString(obligateAmount.getSum()));
				double contratctsAwarded =       totalNumberOfActions.getSum() ;

			// generate Response
			contractsTransactionDataList = contractsTransactionDataList.stream().limit(1).collect(Collectors.toList());
			Map<String, Object> response = new HashMap<>();
			response.put("vendors", contractsTransactionDataList);
			response.put("activeAwardsWith", departmentLogosList);
			//response.put("totalAward", countTotalAwards.get(id));
			response.put("agencyName", contractAgencyName);
			response.put("contratctsReceived",totalAmt);
			 response.put("contratctsAwarded", contratctsAwarded);
		   // response.put("contratctsAwarded", contratctsAwardedCount);
		    response.put("activeAwards", activeAwards);
		    response.put("activeOpportunities", activeOpportunitiesCounter );
		    response.put("activeAwardsStatus", activeAwardsStatus );
		    response.put("contratctsReceivedStatus", contratctsReceivedStatus );
		    response.put("contratctsAwardedStatus", contratctsAwardedStatus );
		    response.put("activeOpportunitiesStatus", activeOpportunitiesStatus);
	    
			contractsTransactionDataList = null;
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
			
            Double avgRating = opportunityVendorsPpRepository.findAverageByVendorDunsNumber(vendorDunsNumberStr);
			ratingList = opportunityVendorsPpRepository.findByEntityId(vendorDunsNumberStr);
	
			// generate Response
			Map<String, Object> response = new HashMap<>();
			response.put("rating", ratingList);
			response.put("avgRating", avgRating);
			ratingList = null;
			return new ResponseEntity<>(response, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// naics and psc top 5
	// ---------------------------------------------------------------------

	@GetMapping("/vendor/top/{id}")
	public ResponseEntity<Map<String, Object>> getNAICSPSC(@PathVariable Integer id) {
		
		

		try {
			vendorList = null;
			vendorList = contractsDataAggregatedByVendorNaicsPscRepository.findByVendorDunsNumber(id);
			// fetching naics data to vendor

			List<String> naicsCodeList = vendorList.parallelStream()
					.map(ContractsDataAggregatedByVendorNaicsPsc::getNaicsCode).distinct().collect(Collectors.toList());
			naicsCodeList.forEach(naicsCode -> {
				naicsCodeDataList = naicsCodeDataRepository.findByNaicsCode(naicsCode);
				naicsCodeDataListAll.addAll(naicsCodeDataList);
				naicsCodeDataListAll = naicsCodeDataListAll.stream().filter(distinctByKey(n -> n.getNaicsCode()))
						.limit(5).collect(Collectors.toList());
			});

			// ProductServiceCodeData
			List<String> productServiceCodeList = vendorList.parallelStream()
					.map(ContractsDataAggregatedByVendorNaicsPsc::getProductOrServiceCode).distinct()
					.collect(Collectors.toList());
			productServiceCodeList.forEach(productServiceCode -> {
				productServiceCodeDataList = productServiceCodeDataRepository.findByPscCode(productServiceCode);
				pscCodeDataListAll.addAll(productServiceCodeDataList);
				pscCodeDataListAll = pscCodeDataListAll.stream().filter(distinctByKey(p -> p.getPscCode())).limit(5)
						.collect(Collectors.toList());

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

	
	// contracts  detials
	@GetMapping("/vendor/contracts/{id}")
	public ResponseEntity<Map<String, Object>> getContractsDetials(@PathVariable Integer id) {

		try {
			vendorList = null;
			vendorList = contractsDataAggregatedByVendorNaicsPscRepository.findByVendorDunsNumber(id);
			
	
			Map<String, Object> response = new HashMap<>();

			response.put("naics", naicsCodeDataListAll);
			response.put("psc", pscCodeDataListAll);
			vendorList = null;
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
}

	
//------------------------Contract Information  based on vendor id , start date and end date-------------------------
	
	
	@GetMapping("/contractInformation/{id}")
	public ResponseEntity<Map<String, Object>> getVendorDateWise(@PathVariable Integer id, @RequestParam(name="start", required = false)  String startDate,@RequestParam(name="end", required = false)  String endDate) {
			
		try {
			
        //  System.out.println(id+"---startDate-------"+startDate+"----endDate----"+endDate);
	      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy"); 
          LocalDate start_date = LocalDate.parse(startDate.trim() , formatter); 
          LocalDate end_date = LocalDate.parse(endDate.trim() , formatter); 
          
          Date datestart = Date.valueOf(start_date);
          Date dateend = Date.valueOf(end_date);
          
       	 
		  LocalDate previousYear = start_date.minus(1, ChronoUnit.YEARS); 
		  Date  previousYearDate =  Date.valueOf(previousYear) ;
	 	
		  
	      //  System.out.println("datestart ---"+ datestart  );
		  //System.out.println("dateend ---"+ dateend);
		   //System.out.println("previousYearDate ---"+  previousYearDate);
		
          
          
      		
			vendorList = null;
			//vendorList = contractsDataAggregatedByVendorNaicsPscRepository.findByVendorDunsNumber(id);
			departmentLogosList = new ArrayList<DepartmentLogos>();
			// count total award contract transcation data
			// contractsTransactionDataList = contractsTransactionDataRepository.findByVendorDunsNumber(id);
			
			contractsTransactionDataList = contractsTransactionDataRepository.findByVendorDunsNumberAndStartDateEndDate(id,datestart,dateend);
			
		    DoubleSummaryStatistics obligateAmount =  	contractsTransactionDataList.stream().collect(Collectors.summarizingDouble(ContractsTransactionData::getDollarsObligated));
		
		    DoubleSummaryStatistics totalNumberOfActions =  	contractsTransactionDataList.stream().collect(Collectors.summarizingDouble(ContractsTransactionData::getNumberOfActions));
			
		  activeAwards = contractsTransactionDataList.stream().filter(e ->  e.getUltimateCompletionDate()== null  ||  ( e.getUltimateCompletionDate().before(datestart) &&  e.getUltimateCompletionDate().after(dateend)) ).count();
		
		List<OpportunityInterestedVendor> opportunityInterestedVendor = opportunityInterestedVendorRepository.findByVendorDunsNumberAndStartDateEndDate(id.toString(),datestart,dateend);
					
			  if(opportunityInterestedVendor != null) {
						 while (activeOpportunitiesCounter < opportunityInterestedVendor.size() ) {
							 	activeOpportunitiesCounter++ ;
						  
						 }
						 
				  }
		
		      //--------------------------------------------UP and DOWN arrow logic implementation------------------------------------------		
			
			  
			  contractsTransactionDataPreviousList = contractsTransactionDataRepository.findByVendorDunsNumberAndStartDateEndDate(id,previousYearDate,datestart);  
				
			  
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
				        List<OpportunityInterestedVendor> opportunityInterestedVendorPrevious = opportunityInterestedVendorRepository.findByVendorDunsNumberAndStartDateEndDate(id.toString(),previousYearDate,datestart);  
						
						 int  activeOpportunitiesCounterPrevious = 0;
					   if(opportunityInterestedVendorPrevious != null) {
									 while (activeOpportunitiesCounterPrevious < opportunityInterestedVendorPrevious.size() ) {
										 activeOpportunitiesCounterPrevious++ ;
									  
									 }
									 
							  }
					  
			
			    long  activeOpportunitiesStatus = (long) (activeOpportunitiesCounter - activeOpportunitiesCounterPrevious) ;
		        if(activeOpportunitiesStatus > 0) {
		        	activeOpportunitiesStatus=1;
		        }else {
		        	  activeOpportunitiesStatus=0;
		         }
			 //----------------------------------------------------------------------------------------------------------------------------------------------------- 
			  List<String> organizationName = contractsTransactionDataList.stream()
						.map(ContractsTransactionData::getContractingDepartmentName).distinct()
						.collect(Collectors.toList());
				organizationName.forEach(orgName -> {
					//System.out.println("orgName---------" + orgName);
					AgencyReferenceData organizationCode = agencyReferenceDataRepository
							.findByOrganizationNameAndType(orgName, "DEPARTMENT");
					//System.out.println("organizationCode------" + organizationCode.getOrganizationCode());
					Integer organizatinCode = Integer.valueOf(organizationCode.getOrganizationCode());
					departmentLogos = departmentLogosRepository.findByFpdsCode(organizatinCode);
					//System.out.println("departmentLogos --------" + departmentLogos);
					departmentLogosList.add(departmentLogos);
				});
				
				departmentLogosList = departmentLogosList.stream().distinct().collect(Collectors.toList());
			
			  List<String> contractAgencyName =
			  contractsTransactionDataList.stream().filter(e ->
			  e.getVendorDunsNumber().equals(id)).map(ContractsTransactionData::
			  getContractingAgencyName).collect(Collectors.toList()); contractAgencyName =
			  contractAgencyName.stream().filter( distinctByKey(p ->p.toString())
			  ).collect(Collectors.toList());
				
			totalAmt = new BigDecimal(Double.toString(obligateAmount.getSum()));
			double contratctsAwarded = totalNumberOfActions.getSum() ;
				
			// generate Response
			contractsTransactionDataList = contractsTransactionDataList.stream().limit(1).collect(Collectors.toList());
			Map<String, Object> response = new HashMap<>();
			response.put("vendors", contractsTransactionDataList);
			response.put("activeAwardsWith", departmentLogosList);
			//response.put("totalAward", countTotalAwards.get(id));
			//response.put("agencyName", contractAgencyName);
			response.put("contratctsReceived",totalAmt);
		    response.put("contratctsAwarded", contratctsAwarded);
		    response.put("activeAwards", activeAwards);
		    response.put("activeOpportunities", activeOpportunitiesCounter );
		    response.put("activeAwardsStatus", activeAwardsStatus );
		    response.put("contratctsReceivedStatus", contratctsReceivedStatus );
		    response.put("contratctsAwardedStatus", contratctsAwardedStatus );
		    response.put("activeOpportunitiesStatus", activeOpportunitiesStatus);
		    
			contractsTransactionDataList = null;
			return new ResponseEntity<>(response, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
//----------------------------------------------------------------------------------------------------------------	
	
	
	
	// Utility function
	public static <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor) {
		Map<Object, Boolean> map = new ConcurrentHashMap<>();
		return t -> map.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
	}
	
	 // Function tot convert String to Date 
    public static LocalDate 
    getDateFromString(String string, DateTimeFormatter format) 
    { 
  
        // Convert the String to Date in the specified format 
        LocalDate date = LocalDate.parse(string, format); 
  
        // return the converted date 
        return date; 
    } 
  
   
}


 class FormattingDate {
	   public static Date StringToDate(String dob) throws ParseException {
	      //Instantiating the SimpleDateFormat class
	      SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
	      //Parsing the given String to Date object
	      Date date = (Date) formatter.parse(dob);
	      System.out.println("Date object value: "+date);
	      return date;
	   }
	   
 }