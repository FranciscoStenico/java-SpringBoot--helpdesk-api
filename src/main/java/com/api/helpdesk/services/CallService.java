package com.api.helpdesk.services;

import java.util.Optional;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.helpdesk.domains.Call;
import com.api.helpdesk.errors.AppError;
import com.api.helpdesk.repositories.CallRepository;

@Service
public class CallService {

    @Autowired
    CallRepository repository;

    public List<Call> list() {
        return this.repository.findAll();
    }

    public Call retrieve(Integer id) {
        Optional<Call> retrievedEntity = this.repository.findById(id);
        return retrievedEntity.orElseThrow(
                () -> new AppError("This call cannot be found", 404, "Entity not found"));
    }

}
