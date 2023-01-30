package com.api.helpdesk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.helpdesk.domains.Technician;
import com.api.helpdesk.domains.dtos.TechnicianDTO;

public interface TechnicianRepository extends JpaRepository<Technician, Integer> {

    Technician save(TechnicianDTO data);

    

}
