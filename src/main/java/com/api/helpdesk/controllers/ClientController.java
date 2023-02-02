package com.api.helpdesk.controllers;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.api.helpdesk.domains.Client;
import com.api.helpdesk.domains.dtos.ClientDTO;
import com.api.helpdesk.services.ClientService;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/clients")
public class ClientController {

    @Autowired
    private ClientService service;

    @PostMapping()
    public ResponseEntity<ClientDTO> postMethodName(@Valid @RequestBody ClientDTO data) {
        Client newEntity = service.create(data);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newEntity.getId())
                .toUri();
        return ResponseEntity.created(uri).body(new ClientDTO(newEntity));
    }

    @GetMapping
    public ResponseEntity<List<ClientDTO>> findAll() {
        List<Client> entitiesList = this.service.list();
        List<ClientDTO> DTOEntitiesList = entitiesList
                .stream()
                .map(entity -> new ClientDTO(entity))
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(DTOEntitiesList);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ClientDTO> findOne(@PathVariable Integer id) {
        Client retrievedEntity = this.service.retrieve(id);
        return ResponseEntity.ok().body(new ClientDTO(retrievedEntity));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ClientDTO> update(@PathVariable Integer id, @Valid @RequestBody ClientDTO updates) {
        Client updatedEntity = this.service.update(id, updates);
        return ResponseEntity.ok().body(new ClientDTO(updatedEntity));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        this.service.destroy(id);
        return ResponseEntity.noContent().build();
    }

}
