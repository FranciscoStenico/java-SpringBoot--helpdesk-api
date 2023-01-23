package com.api.helpdesk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.helpdesk.domains.Client;

public interface ClientRepository extends JpaRepository<Client, Integer> {
    
    

}
