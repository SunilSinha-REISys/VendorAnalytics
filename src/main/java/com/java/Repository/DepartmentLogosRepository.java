package com.java.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.java.model.DepartmentLogos;
@Repository
public interface DepartmentLogosRepository extends  JpaRepository<DepartmentLogos,Integer> {

	
	DepartmentLogos findByFpdsCode(Integer fpdsCode) ;
}
