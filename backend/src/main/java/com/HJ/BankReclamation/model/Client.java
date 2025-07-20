package com.HJ.BankReclamation.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "client")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idC")
    private Integer idC;



    @NotBlank(message = "First name is required")
    @Size(min = 2, max = 100)
    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @NotBlank(message = "Last name is required")
    @Size(min = 2, max = 100)
    @Column(name = "surname", nullable = false, length = 100)
    private String surname;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    @Size(max = 100)
    @Column(name = "email", nullable = false, length = 100)
    private String email;

    @Size(max = 100, message = "Mobile number max length 100")
    @Column(name = "mobile", nullable = false, length = 100)
    private String mobile;

    @Size(max = 100)
    @Column(name = "numC", nullable = false, length = 100)
    private String numC;

    @Column(name = "dateC", nullable = false)
    private LocalDateTime dateC;

    @NotBlank(message = "Password is required")
    @Size(min = 8, max = 100)
    @Column(name = "pwd", nullable = false, length = 100)
    private String pwd;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, columnDefinition = "ENUM('active','inactive')")
    private ClientStatus status;

    public Integer getIdC() {
        return idC;
    }

    public void setIdC(Integer idC) {
        this.idC = idC;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getNumC() {
        return numC;
    }

    public void setNumC(String numC) {
        this.numC = numC;
    }

    public LocalDateTime getDateC() {
        return dateC;
    }

    public void setDateC(LocalDateTime dateC) {
        this.dateC = dateC;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public ClientStatus getStatus() {
        return status;
    }

    public void setStatus(ClientStatus status) {
        this.status = status;
    }
}
