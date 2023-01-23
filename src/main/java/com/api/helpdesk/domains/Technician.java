package com.api.helpdesk.domains;

import java.util.List;
import java.util.ArrayList;

public class Technician extends Person {

    private List<Call> calls = new ArrayList<>();

    public Technician() {
        super();
    }

    public Technician(Integer id, String name, String cpf, String email, String password) {
        super(id, name, cpf, email, password);
    }

    public List<Call> getCalls() {
        return calls;
    }

    public void setCalls(List<Call> calls) {
        this.calls = calls;
    }

}
