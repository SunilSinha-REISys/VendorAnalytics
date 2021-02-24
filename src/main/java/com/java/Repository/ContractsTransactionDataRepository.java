package com.java.Repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.java.model.ContractsTransactionData;

@Repository
public interface ContractsTransactionDataRepository extends JpaRepository<ContractsTransactionData, Long> {   

	public List<ContractsTransactionData> findByVendorDunsNumber(Integer vendorDunsNumber);
	
	@Query(value = "SELECT * FROM vendordb.contracts_transaction_data u WHERE u.vendor_duns_number = :vendorDunsNumber AND u.agency_code = :agencyCode   limit 1 ", nativeQuery = true)
	public List<ContractsTransactionData> findByVendorDunsNumberAndAgencyCode(Integer vendorDunsNumber,String agencyCode);
	
	public List<ContractsTransactionData> findByAgencyCode(String agencyCode);
	
	Page<List<ContractsTransactionData>> findByAgencyCode(String agencyCode,Pageable paging);
	
	@Query(value = "SELECT * FROM vendordb.contracts_transaction_data u WHERE u.agency_code = :agencyCode limit 1 ", nativeQuery = true)
	public ContractsTransactionData findOneByAgencyConde(@Param("agencyCode") String agencyCode);
	
	
	@Query(value = " SELECT * FROM vendordb.contracts_transaction_data u WHERE u.vendor_duns_number= :vendorDunsNumber AND (to_date(substring(date_signed,1,10),'dd-mm-yyyy') , to_date(substring(date_signed,1,10),'dd-mm-yyyy')) OVERLAPS ( :startDate, :endDate) ", nativeQuery = true)
	public List<ContractsTransactionData> findByVendorDunsNumberAndStartDateEndDate( @Param("vendorDunsNumber") Integer vendorDunsNumber ,  @Param("startDate") Date startDate, @Param("endDate") Date endDate );
	
	
	

	@Query(value = " SELECT * FROM vendordb.contracts_transaction_data u WHERE u.agency_code= :agencyCode AND (to_date(substring(date_signed,1,10),'dd-mm-yyyy') , to_date(substring(date_signed,1,10),'dd-mm-yyyy')) OVERLAPS ( :startDate, :endDate) ", nativeQuery = true)
	public List<ContractsTransactionData> findByAgencyCodeAndStartDateEndDate( @Param("agencyCode") String agencyCode ,  @Param("startDate") Date startDate, @Param("endDate") Date endDate );
	

}
