package com.api.helpdesk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.helpdesk.domains.Client;
import com.api.helpdesk.domains.dtos.ClientDTO;

public interface ClientRepository extends JpaRepository<Client, Integer> {
    
    Client save(ClientDTO data);

}
