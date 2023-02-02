package com.api.helpdesk.controllers;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.api.helpdesk.domains.Call;
import com.api.helpdesk.domains.dtos.CallDTO;
import com.api.helpdesk.services.CallService;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/calls")
public class CallController {

    @Autowired
    CallService service;

    @PostMapping
    public ResponseEntity<CallDTO> create(@Valid @RequestBody CallDTO data) {
        Call newEntity = this.service.create(data);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newEntity.getId())
                .toUri();
        return ResponseEntity.created(uri).body(new CallDTO(newEntity));
    }

    @GetMapping
    public ResponseEntity<List<CallDTO>> findAll() {
        List<Call> entitiesList = this.service.list();
        List<CallDTO> DTOEntitiesList = entitiesList
                .stream()
                .map(entity -> new CallDTO(entity))
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(DTOEntitiesList);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<CallDTO> findById(@PathVariable Integer id) {
        Call retrievedEntity = this.service.retrieve(id);
        return ResponseEntity.ok().body(new CallDTO(retrievedEntity));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<CallDTO> update(@PathVariable Integer id, @Valid @RequestBody CallDTO updates) {
        Call updatedEntity = this.service.update(id, updates);
        return ResponseEntity.ok().body(new CallDTO(updatedEntity));
    }

}
