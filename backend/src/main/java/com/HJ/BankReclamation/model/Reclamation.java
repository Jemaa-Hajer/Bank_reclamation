package com.HJ.BankReclamation.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;
@Entity
@Table(name = "reclamation")
public class Reclamation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idR")
    private Integer idR;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idC", nullable = false)
    private Client client;

    @NotBlank(message = "Description is required")
    @Column(name = "descp", nullable = false, columnDefinition = "TEXT")
    private String descp;

    @NotBlank(message = "Object is required")
    @Size(max = 100, message = "Object can be at most 100 characters")
    @Column(name = "object", nullable = false, length = 100)
    private String object;

    @NotBlank(message = "Service is required")
    @Size(max = 100, message = "Service can be at most 100 characters")
    @Column(name = "service", nullable = false, length = 100)
    private String service;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, columnDefinition = "ENUM('pending','in progress','resolved','rejected')")
    private ReclamationStatus status;

    public Integer getIdR() {
        return idR;
    }

    public void setIdR(Integer idR) {
        this.idR = idR;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public String getDescp() {
        return descp;
    }

    public void setDescp(String descp) {
        this.descp = descp;
    }

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public ReclamationStatus getStatus() {
        return status;
    }

    public void setStatus(ReclamationStatus status) {
        this.status = status;
    }

    public LocalDateTime getDateC() {
        return dateC;
    }

    public void setDateC(LocalDateTime dateC) {
        this.dateC = dateC;
    }

    @Column(name = "dateC", nullable = false)
    private LocalDateTime dateC;
}
