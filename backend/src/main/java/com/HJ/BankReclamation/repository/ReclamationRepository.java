package com.HJ.BankReclamation.repository;

import com.HJ.BankReclamation.model.Reclamation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReclamationRepository  extends JpaRepository<Reclamation, Integer> {
}
