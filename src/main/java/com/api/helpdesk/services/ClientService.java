package com.api.helpdesk.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.helpdesk.domains.Client;
import com.api.helpdesk.domains.User;
import com.api.helpdesk.domains.dtos.ClientDTO;
import com.api.helpdesk.errors.AppError;
import com.api.helpdesk.repositories.ClientRepository;
import com.api.helpdesk.repositories.UserRepository;

@Service
public class ClientService {

    @Autowired
    ClientRepository repository;
    @Autowired
    UserRepository userRepository;

    public Client create(ClientDTO data) {
        data.setId(null);
        this.validate(data);
        Client newEntity = new Client(data);
        return this.repository.save(newEntity);
    }

    public List<Client> list() {
        return repository.findAll();
    }

    public Client retrieve(Integer id) {
        Optional<Client> retrievedEntity = this.repository.findById(id);
        return retrievedEntity.orElseThrow(
                () -> new AppError("Client not found", 404, "Entity not found"));
    }

    public Client update(Integer id, ClientDTO updates) {
        updates.setId(id);
        Client oldEntity = this.retrieve(id);
        this.validate(updates);
        oldEntity = new Client(updates);
        return this.repository.save(oldEntity);
    }

    public void destroy(Integer id) {
        Client entityToBeDeleted = this.retrieve(id);
        if (entityToBeDeleted.getCalls().size() > 0) {
            throw new AppError(
                    "Client has active calls and cannot be deleted",
                    409,
                    "Data Integrity Violation");
        }
        this.repository.delete(entityToBeDeleted);
    }

    public void validate(ClientDTO data) {
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
