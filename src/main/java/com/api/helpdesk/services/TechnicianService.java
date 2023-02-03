package com.api.helpdesk.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
    @Autowired
    BCryptPasswordEncoder encoder;

    public Technician create(TechnicianDTO data) {
        data.setId(null);
        data.setPassword(encoder.encode(data.getPassword()));
        this.validate(data);
        Technician newEntity = new Technician(data);
        return this.repository.save(newEntity);
    }

    public List<Technician> list() {
        return this.repository.findAll();
    }

    public Technician retrieve(Integer id) {
        Optional<Technician> retrievedEntity = this.repository.findById(id);
        return retrievedEntity.orElseThrow(
                () -> new AppError("Technician not found", 404, "Entity not found"));
    }

    public Technician update(Integer id, TechnicianDTO updates) {
        updates.setId(id);
        Technician oldEntity = retrieve(id);
        this.validate(updates);
        oldEntity = new Technician(updates);
        return this.repository.save(oldEntity);
    }

    public void destroy(Integer id) {
        Technician entityToBeDeleted = this.retrieve(id);
        if (entityToBeDeleted.getCalls().size() > 0) {
            throw new AppError(
                    "Technician has active calls and cannot be deleted",
                    409,
                    "Data Integrity Violation");
        }
        this.repository.delete(entityToBeDeleted);
    }

    public void validate(TechnicianDTO data) {
        Optional<User> instance = this.userRepository.findByEmail(data.getEmail());
        if (instance.isPresent() && instance.get().getId() != data.getId()) {
            throw new AppError("This Email is already being used", 400, "Data violation");
        }

        instance = userRepository.findByCpf(data.getCpf());
        if (instance.isPresent() && instance.get().getId() != data.getId()) {
            throw new AppError("CPF already registered", 400, "Data violation");
        }
    }

}
