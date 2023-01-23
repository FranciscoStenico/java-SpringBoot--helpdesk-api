package com.api.helpdesk.domains;

import java.util.ArrayList;

import java.util.List;

import com.api.helpdesk.domains.enums.Profile;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

@Entity(name = "clients")
public class Client extends User {
    private static final long serialVersionUID = 1L;

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

    public List<Call> getCalls() {
        return calls;
    }

    public void setCalls(List<Call> calls) {
        this.calls = calls;
    }

}
