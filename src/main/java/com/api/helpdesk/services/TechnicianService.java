package com.api.helpdesk.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.helpdesk.domains.Technician;
import com.api.helpdesk.domains.User;
import com.api.helpdesk.domains.dtos.TechnicianDTO;
import com.api.helpdesk.errors.AppError;
import com.api.helpdesk.repositories.TechnicianRepository;
import com.api.helpdesk.repositories.UserRepository;

@Service
public class TechnicianService {

    @Autowired
    private TechnicianRepository repository;
    @Autowired
    private UserRepository userRepository;

    public Technician create(TechnicianDTO data) {
        data.setId(null);
        validate(data);
        Technician newTechnician = new Technician(data);
        return repository.save(newTechnician);
    }

    public List<Technician> list() {
        return this.repository.findAll();
    }

    public Technician retrieve(Integer id) {
        Optional<Technician> instance = this.repository.findById(id);
        return instance.orElseThrow(
                () -> new AppError("Technician not found", 404, "Object not found"));
    }

    public void validate(TechnicianDTO data) {
        Optional<User> instance = userRepository.findByEmail(data.getEmail());
        if (instance.isPresent() && instance.get().getId() != data.getId()) {
            throw new AppError("This Email is already being used", 400, "Data violation");
        }

        instance = userRepository.findByCpf(data.getCpf());
        if (instance.isPresent() && instance.get().getId() != data.getId()) {
            throw new AppError("CPF already registered", 400, "Data violation");
        }
    }

}
