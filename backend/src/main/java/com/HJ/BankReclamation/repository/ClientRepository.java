package com.HJ.BankReclamation.repository;

import com.HJ.BankReclamation.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client,Integer> {
    Client findByEmail(String email);
}
