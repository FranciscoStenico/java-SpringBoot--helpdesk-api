package com.api.helpdesk.services;

import java.util.Optional;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.helpdesk.domains.Call;
import com.api.helpdesk.domains.Client;
import com.api.helpdesk.domains.Technician;
import com.api.helpdesk.domains.dtos.CallDTO;
import com.api.helpdesk.domains.enums.Priority;
import com.api.helpdesk.domains.enums.Status;
import com.api.helpdesk.errors.AppError;
import com.api.helpdesk.repositories.CallRepository;

@Service
public class CallService {

    @Autowired
    CallRepository repository;
    @Autowired
    ClientService clientService;
    @Autowired
    TechnicianService technicianService;

    public Call create(CallDTO data) {
        return this.repository.save(this.createOrUpdate(data));
    }

    public List<Call> list() {
        return this.repository.findAll();
    }

    public Call retrieve(Integer id) {
        Optional<Call> retrievedEntity = this.repository.findById(id);
        return retrievedEntity.orElseThrow(
                () -> new AppError("This call cannot be found", 404, "Entity not found"));
    }

    public Call update(Integer id, CallDTO updates) {
        updates.setId(id);
        Call oldEntity = this.retrieve(id);
        oldEntity = this.createOrUpdate(updates);
        return this.repository.save(oldEntity);
    }

    public Call createOrUpdate(CallDTO data) {
        Technician retrievedTechnician = this.technicianService.retrieve(data.getTechnician());
        Client retrievedClient = this.clientService.retrieve(data.getClient());

        Call entity = new Call();
        if (data.getId() != null) {
            entity.setId(data.getId());
        }
        entity.setTechnician(retrievedTechnician);
        entity.setClient(retrievedClient);
        entity.setPriority(Priority.toEnum(data.getPriority()));
        entity.setStatus(Status.toEnum(data.getStatus()));
        entity.setTitle(data.getTitle());
        entity.setRemarks(data.getRemarks());

        if (entity.getStatus().getCode().equals(2)) {
            entity.setClosedAt(LocalDate.now());
        }

        return entity;
    }

}
