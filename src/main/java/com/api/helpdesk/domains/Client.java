package com.api.helpdesk.domains;

import java.util.ArrayList;

import java.util.List;
import java.util.stream.Collectors;

import com.api.helpdesk.domains.dtos.ClientDTO;
import com.api.helpdesk.domains.enums.Profile;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class Client extends User {

    private static final long serialVersionUID = 1L;

    @JsonIgnore
    @OneToMany(mappedBy = "client")
    private List<Call> calls = new ArrayList<>();

    public Client() {
        super();
        addProfile(Profile.CLIENT);
    }

    public Client(Integer id, String name, String cpf, String email, String password) {
        super(id, name, cpf, email, password);
        addProfile(Profile.CLIENT);
    }

    public Client(ClientDTO data) {
        this.id = data.getId();
        this.name = data.getName();
        this.cpf = data.getCpf();
        this.email = data.getEmail();
        this.password = data.getPassword();
        this.profiles = data.getProfiles().stream().map(x -> x.getCode()).collect(Collectors.toSet());
        this.createdAt = data.getCreatedAt();
        addProfile(Profile.CLIENT);
    }

    public List<Call> getCalls() {
        return calls;
    }

    public void setCalls(List<Call> calls) {
        this.calls = calls;
    }

}
