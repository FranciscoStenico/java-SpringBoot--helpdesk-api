package com.api.helpdesk.domains;

import java.util.ArrayList;

import java.util.List;

public class Client extends User {

    private List<Call> calls = new ArrayList<>();

    public Client() {
        super();
    }

    public Client(Integer id, String name, String cpf, String email, String password) {
        super(id, name, cpf, email, password);
    }

    public List<Call> getCalls() {
        return calls;
    }

    public void setCalls(List<Call> calls) {
        this.calls = calls;
    }

}
