package com.java.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.java.model.NaicsCodeData;
@Repository
public interface NaicsCodeDataRepository extends JpaRepository<NaicsCodeData, Integer> {   


}
