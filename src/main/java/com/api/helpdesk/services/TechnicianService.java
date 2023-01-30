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

import jakarta.validation.Valid;

@Service
public class TechnicianService {

    @Autowired
    private TechnicianRepository repository;
    @Autowired
    private UserRepository userRepository;

    public Technician create(TechnicianDTO data) {
        data.setId(null);
        validate(data);
        Technician newEntity = new Technician(data);
        return repository.save(newEntity);
    }

    public List<Technician> list() {
        return this.repository.findAll();
    }

    public Technician retrieve(Integer id) {
        Optional<Technician> currentEntity = this.repository.findById(id);
        return currentEntity.orElseThrow(
                () -> new AppError("Technician not found", 404, "Object not found"));
    }

    public Technician update(Integer id, @Valid TechnicianDTO updates) {
        updates.setId(id);
        Technician oldEntity = retrieve(id);
        validate(updates);
        oldEntity = new Technician(updates);
        return repository.save(oldEntity);
    }

    public void delete(Integer id) {
        Technician currentEntity = retrieve(id);
        if (currentEntity.getCalls().size() > 0) {
            throw new AppError(
                    "Technician has active calls and cannot be deleted",
                    409,
                    "Data Integrity Violation");
        }
        repository.delete(currentEntity);
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
