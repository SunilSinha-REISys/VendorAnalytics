package com.java.Repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.java.model.ContractsTransactionData;
import com.java.model.OpportunityInterestedVendor;
@Repository
public  interface OpportunityInterestedVendorRepository extends JpaRepository<OpportunityInterestedVendor,Integer> {
		
	@Query(value = " SELECT * FROM vendordb.opportunity_interested_vendor u WHERE u.entity_id = :entityId  AND ( u.active_date IS NULL OR u.active_date >= CURRENT_DATE )  ", nativeQuery = true)
	public List<OpportunityInterestedVendor> findByVendorDunsNumber( @Param("entityId") String entityId);
	
	@Query(value = " SELECT * FROM vendordb.opportunity_interested_vendor u WHERE u.entity_id = :entityId  AND ( u.active_date IS NULL OR u.active_date >= CURRENT_DATE ) limit 1 ", nativeQuery = true)
	public OpportunityInterestedVendor findByEntityId( @Param("entityId") String entityId);
	
	
	
	
	@Query(value = " SELECT * FROM vendordb.opportunity_interested_vendor u WHERE u.entity_id = :entityId  AND ( u.active_date IS NULL OR (u.active_date BETWEEN :startDate AND :endDate ) )  ", nativeQuery = true)
	public List<OpportunityInterestedVendor> findByVendorDunsNumberAndStartDateEndDate(@Param("entityId") String entityId, @Param("startDate") Date startDate, @Param("endDate") Date endDate );


	//@Query(value = " SELECT * FROM vendordb.contracts_transaction_data u WHERE u.vendor_duns_number= :vendorDunsNumber AND (to_date(substring(date_signed,1,10),'dd-mm-yyyy') , to_date(substring(date_signed,1,10),'dd-mm-yyyy')) OVERLAPS ( :startDate, :endDate) ", nativeQuery = true)
	//public List<ContractsTransactionData> findByVendorDunsNumberAndStartDateEndDate( @Param("vendorDunsNumber") Integer vendorDunsNumber ,  @Param("startDate") Date startDate, @Param("endDate") Date endDate );
	
	

}
