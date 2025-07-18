package com.HJ.BankReclamation.dtos;

public class ClientResponseDto {
    private Integer idC;
    private String name;
    private String surname;
    private String email;
    private String token;

    public Integer getIdC() { return idC; }
    public void setIdC(Integer idC) { this.idC = idC; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getSurname() { return surname; }
    public void setSurname(String surname) { this.surname = surname; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }
}
