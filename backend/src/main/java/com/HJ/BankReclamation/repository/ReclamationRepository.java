package com.HJ.BankReclamation.repository;

import com.HJ.BankReclamation.model.Reclamation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReclamationRepository  extends JpaRepository<Reclamation, Integer> {
    List<Reclamation> findByClientIdCAndDeletedFalse(Integer idC);

    Optional <Reclamation> findByIdRAndClientIdCAndDeletedFalse(Integer idR, Integer idC);
}
