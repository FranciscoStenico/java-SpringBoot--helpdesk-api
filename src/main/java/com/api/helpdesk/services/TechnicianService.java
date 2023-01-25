package com.api.helpdesk.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.helpdesk.domains.Technician;
import com.api.helpdesk.repositories.TechnicianRepository;

@Service
public class TechnicianService {

    @Autowired
    private TechnicianRepository repository;

    public Technician retrieve(Integer id) {
        Optional<Technician> instance = this.repository.findById(id);
        return instance.orElse(null);
    }
}
