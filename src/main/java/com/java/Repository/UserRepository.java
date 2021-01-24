package com.java.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.java.model.User;

@Repository
public interface UserRepository  extends JpaRepository<User, Integer> {   

    User findByUsername(String username);
}
