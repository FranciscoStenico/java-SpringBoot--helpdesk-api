package com.api.helpdesk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.helpdesk.domains.User;

public interface UserRepository extends JpaRepository<User, Integer> {
    
    

}
