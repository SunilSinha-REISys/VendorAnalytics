package com.java.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.java.model.SmallBusinessGoal;

public interface SmallBusinessGoalRepository extends JpaRepository<SmallBusinessGoal, Integer>{

	 SmallBusinessGoal findByOrganizationCode(String agencyCode) ;
	

}
