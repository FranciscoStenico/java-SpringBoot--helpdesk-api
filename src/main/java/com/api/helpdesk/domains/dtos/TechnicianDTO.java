package com.api.helpdesk.domains.dtos;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import com.api.helpdesk.domains.Technician;
import com.api.helpdesk.domains.enums.Profile;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotNull;

public class TechnicianDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    protected Integer id;
    @NotNull(message = "NAME is a required field")
    protected String name;
    @NotNull(message = "CPF is a required field")
    protected String cpf;
    @NotNull(message = "EMAIL is a required field")
    protected String email;
    @NotNull(message = "PASSWORD is a required field")
    protected String password;
    protected Set<Integer> profiles = new HashSet<>();

    @JsonFormat(pattern = "dd/MM/yyyy")
    protected LocalDate createdAt = LocalDate.now();

    public TechnicianDTO() {
        super();
        addProfiles(Profile.TECHNICIAN);
    }

    public TechnicianDTO(Technician instance) {
        this.id = instance.getId();
        this.name = instance.getName();
        this.cpf = instance.getCpf();
        this.email = instance.getEmail();
        this.password = instance.getPassword();
        this.profiles = instance.getProfiles().stream().map(x -> x.getCode()).collect(Collectors.toSet());
        this.createdAt = instance.getCreatedAt();
        addProfiles(Profile.TECHNICIAN);
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Profile> getProfiles() {
        return profiles.stream().map(x -> Profile.toEnum(x)).collect(Collectors.toSet());
    }

    public void addProfiles(Profile profile) {
        this.profiles.add(profile.getCode());
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

}
