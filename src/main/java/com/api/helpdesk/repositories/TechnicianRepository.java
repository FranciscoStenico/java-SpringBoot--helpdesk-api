package com.api.helpdesk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.helpdesk.domains.Technician;

public interface TechnicianRepository extends JpaRepository<Technician, Integer> {

    

}
