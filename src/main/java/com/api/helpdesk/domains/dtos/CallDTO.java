package com.api.helpdesk.domains.dtos;

import java.io.Serializable;
import java.time.LocalDate;

import com.api.helpdesk.domains.Call;
import com.fasterxml.jackson.annotation.JsonFormat;

public class CallDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate createdAt = LocalDate.now();
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate closedAt;
    private Integer priority;
    private Integer status;
    private String title;
    private String remarks;
    
    private Integer technician;
    private String technicianName;
    
    private Integer client;
    private String clientName;

    public CallDTO() {
        super();
    }

    public CallDTO(Call entity) {
        this.id = entity.getId();
        this.createdAt = entity.getCreatedAt();
        this.closedAt = entity.getClosedAt();
        this.priority = entity.getPriority().getCode();
        this.status = entity.getStatus().getCode();
        this.title = entity.getTitle();
        this.remarks = entity.getRemarks();
        this.technician = entity.getTechnician().getId();
        this.client = entity.getClient().getId();
        this.technicianName = entity.getTechnician().getName();
        this.clientName = entity.getClient().getName();
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

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDate getClosedAt() {
        return closedAt;
    }

    public void setClosedAt(LocalDate closedAt) {
        this.closedAt = closedAt;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Integer getTechnician() {
        return technician;
    }

    public void setTechnician(Integer technician) {
        this.technician = technician;
    }

    public Integer getClient() {
        return client;
    }

    public void setClient(Integer client) {
        this.client = client;
    }

    public String getTechnicianName() {
        return technicianName;
    }

    public void setTechnicianName(String technicianName) {
        this.technicianName = technicianName;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

}
