package com.api.helpdesk.domains;

import java.util.List;
import java.util.stream.Collectors;

import com.api.helpdesk.domains.dtos.TechnicianDTO;
import com.api.helpdesk.domains.enums.Profile;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import java.util.ArrayList;

@Entity
public class Technician extends User {

    private static final long serialVersionUID = 1L;

    @JsonIgnore
    @OneToMany(mappedBy = "technician")
    private List<Call> calls = new ArrayList<>();

    public Technician() {
        super();
        addProfile(Profile.TECHNICIAN);
        addProfile(Profile.ADMIN);
    }

    public Technician(Integer id, String name, String cpf, String email, String password) {
        super(id, name, cpf, email, password);
        addProfile(Profile.TECHNICIAN);
        addProfile(Profile.ADMIN);
    }

    public Technician(TechnicianDTO data) {
        this.id = data.getId();
        this.name = data.getName();
        this.cpf = data.getCpf();
        this.email = data.getEmail();
        this.password = data.getPassword();
        this.profiles = data.getProfiles().stream().map(x -> x.getCode()).collect(Collectors.toSet());
        this.createdAt = data.getCreatedAt();
    }

    public List<Call> getCalls() {
        return calls;
    }

    public void setCalls(List<Call> calls) {
        this.calls = calls;
    }

}
