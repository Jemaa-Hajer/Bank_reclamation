package com.HJ.BankReclamation.dtos;

import jakarta.validation.constraints.*;

public class ClientDto {
    private Integer idC;

    @NotBlank(message = "Name is required")
    @Size(min = 2, max = 100)
    private String name;

    @NotBlank(message = "Surname is required")
    @Size(min = 2, max = 100)
    private String surname;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    @Size(max = 100)
    private String email;

    @Size(max = 100, message = "Mobile number max length 100")
    private String mobile;

    @Size(max = 100)
    private String numC;

    @NotBlank(message = "Password is required")
    @Size(min = 8, max = 100)
    private String pwd;

    private String status;

    // Getters and setters
    public Integer getIdC() { return idC; }
    public void setIdC(Integer idC) { this.idC = idC; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getSurname() { return surname; }
    public void setSurname(String surname) { this.surname = surname; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getMobile() { return mobile; }
    public void setMobile(String mobile) { this.mobile = mobile; }
    public String getNumC() { return numC; }
    public void setNumC(String numC) { this.numC = numC; }
    public String getPwd() { return pwd; }
    public void setPwd(String pwd) { this.pwd = pwd; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
