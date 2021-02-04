package com.java.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.java.model.ProductServiceCodeData;

public interface ProductServiceCodeDataRepository extends JpaRepository<ProductServiceCodeData,Integer>{
	
	 public  List<ProductServiceCodeData>	findByPscCode(String pscCode) ;

}
