package com.api.helpdesk.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.helpdesk.domains.Technician;
import com.api.helpdesk.domains.dtos.TechnicianDTO;
import com.api.helpdesk.errors.AppError;
import com.api.helpdesk.repositories.TechnicianRepository;

@Service
public class TechnicianService {
    
    @Autowired
    private TechnicianRepository repository;
    
    public Technician create(TechnicianDTO data) {
        data.setId(null);
        Technician newTechnician = new Technician(data);
        return repository.save(newTechnician);
    }

    public List<Technician> list() {
        return this.repository.findAll();
    }

    public Technician retrieve(Integer id) {
        Optional<Technician> instance = this.repository.findById(id);
        return instance.orElseThrow(
                () -> new AppError("Technician not found", 404));
    }

}
