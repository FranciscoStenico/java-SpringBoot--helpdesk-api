package com.api.helpdesk.controllers;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.api.helpdesk.domains.Technician;
import com.api.helpdesk.domains.dtos.TechnicianDTO;
import com.api.helpdesk.services.TechnicianService;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/technicians")
public class TechnicianController {

    @Autowired
    private TechnicianService service;

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping
    public ResponseEntity<TechnicianDTO> create(@Valid @RequestBody TechnicianDTO data) {
        Technician newEntity = service.create(data);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newEntity.getId())
                .toUri();
        return ResponseEntity.created(uri).body(new TechnicianDTO(newEntity));
    }

    @GetMapping
    public ResponseEntity<List<TechnicianDTO>> findAll() {
        List<Technician> entityList = this.service.list();
        List<TechnicianDTO> DTOEntitiesList = entityList
                .stream()
                .map(instance -> new TechnicianDTO(instance))
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(DTOEntitiesList);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<TechnicianDTO> findById(@PathVariable Integer id) {
        Technician retrievedEntity = this.service.retrieve(id);
        return ResponseEntity.ok().body(new TechnicianDTO(retrievedEntity));
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PutMapping(value = "/{id}")
    public ResponseEntity<TechnicianDTO> update(@PathVariable Integer id, @Valid @RequestBody TechnicianDTO updates) {
        Technician updatedEntity = this.service.update(id, updates);
        return ResponseEntity.ok().body(new TechnicianDTO(updatedEntity));
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        this.service.destroy(id);
        return ResponseEntity.noContent().build();
    }

}
