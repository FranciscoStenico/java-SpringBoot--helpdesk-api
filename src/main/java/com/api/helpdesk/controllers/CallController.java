package com.api.helpdesk.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.helpdesk.domains.Call;
import com.api.helpdesk.domains.dtos.CallDTO;
import com.api.helpdesk.services.CallService;

@RestController
@RequestMapping(value = "/calls")
public class CallController {

    @Autowired
    CallService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<CallDTO> findById(@PathVariable Integer id) {
        Call retrievedEntity = this.service.retrieve(id);
        return ResponseEntity.ok().body(new CallDTO(retrievedEntity));
    }
}
