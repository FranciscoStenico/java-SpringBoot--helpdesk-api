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

import com.api.helpdesk.domains.Technician;
import com.api.helpdesk.domains.dtos.TechnicianDTO;
import com.api.helpdesk.services.TechnicianService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/technicians")
public class TechnicianController {

    @Autowired
    private TechnicianService service;

    @PostMapping
    public ResponseEntity<TechnicianDTO> create(@Valid @RequestBody TechnicianDTO data) {
        Technician instance = service.create(data);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(instance.getId())
                .toUri();
        return ResponseEntity.created(uri).body(new TechnicianDTO(instance));
    }

    @GetMapping
    public ResponseEntity<List<TechnicianDTO>> findAll() {
        List<Technician> instanceList = this.service.list();
        List<TechnicianDTO> instanceListDTO = instanceList.stream().map(instance -> new TechnicianDTO(instance))
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(instanceListDTO);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<TechnicianDTO> findById(@PathVariable Integer id) {
        Technician instance = this.service.retrieve(id);
        return ResponseEntity.ok().body(new TechnicianDTO(instance));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<TechnicianDTO> update(@PathVariable Integer id, @Valid @RequestBody TechnicianDTO updates) {
        Technician updatedInstance = service.update(id, updates);
        return ResponseEntity.ok().body(new TechnicianDTO(updatedInstance));
    }

}
