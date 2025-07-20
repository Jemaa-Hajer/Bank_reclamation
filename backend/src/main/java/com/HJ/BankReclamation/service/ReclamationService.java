package com.HJ.BankReclamation.service;

import com.HJ.BankReclamation.dtos.ReclamationDto;
import com.HJ.BankReclamation.dtos.ReclamationResponseDto;
import com.HJ.BankReclamation.model.Client;
import com.HJ.BankReclamation.model.Reclamation;
import com.HJ.BankReclamation.model.ReclamationStatus;
import com.HJ.BankReclamation.repository.ClientRepository;
import com.HJ.BankReclamation.repository.ReclamationRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ReclamationService {
    private final ClientRepository clientRepository;
    private final ReclamationRepository reclamationRepository;

    public ReclamationService(ClientRepository clientRepository, ReclamationRepository reclamationRepository) {
        this.reclamationRepository = reclamationRepository;
        this.clientRepository = clientRepository;
    }
    public Map<String,String> createReclamation (ReclamationDto reclamationDto, Integer idC){
        Client client = clientRepository.findById(idC)
                .orElseThrow(() -> new IllegalArgumentException("Client not found"));
        Reclamation reclamation = new Reclamation();
        reclamation.setObject(reclamationDto.getObject());
        reclamation.setService(reclamationDto.getService());
        reclamation.setDescp(reclamationDto.getDescp());
        reclamation.setDateC(LocalDateTime.now());
        reclamation.setStatus(ReclamationStatus.pending);
        reclamation.setClient(client);
        reclamationRepository.save(reclamation);
        Map<String, String> response =new HashMap<>();
        response.put("status","success");
        response.put("message","Reclamation created successfully");
        return response;
    }

    public List<ReclamationResponseDto> getReclamationByClient (Integer idC){
        Client client=clientRepository.findById(idC)
                .orElseThrow(() -> new IllegalArgumentException("Client not found"));
        return reclamationRepository.findByClientIdCAndDeletedFalse(idC).stream()
                .map(reclamation -> {
                    ReclamationResponseDto dto = new ReclamationResponseDto();
                    dto.setIdR(reclamation.getIdR());
                    dto.setObject(reclamation.getObject());
                    dto.setService(reclamation.getService());
                    dto.setDescp(reclamation.getDescp());
                    dto.setDateC(reclamation.getDateC());
                    dto.setStatus(reclamation.getStatus());
                    return dto;
                })
                .collect(Collectors.toList());
    }

    public Map<String, String> updateReclamation(Integer idR, @Valid ReclamationDto reclamationDto, Integer idC) {
        Client client=clientRepository.findById(idC)
                .orElseThrow(() -> new IllegalArgumentException("Client not found"));
        Reclamation reclamation= reclamationRepository.findByIdRAndClientIdCAndDeletedFalse(idR, idC)
                .orElseThrow(() -> new IllegalArgumentException("Reclamation not found or not owned by client"));
        if (reclamation.getStatus()!= ReclamationStatus.pending){
            throw new IllegalArgumentException("Only pending reclamations can be updated");
        }
        reclamation.setObject(reclamationDto.getObject());
        reclamation.setService(reclamationDto.getService());
        reclamation.setDescp(reclamationDto.getDescp());
        reclamation.setDateC(LocalDateTime.now());
        reclamationRepository.save(reclamation);
        Map<String, String> response =new HashMap<>();
        response.put("status","success");
        response.put("message","Reclamation updated successfully");
        return response;
    }

    public Map<String, String> deleteReclamation(Integer idR, Integer idC) {
        Reclamation reclamation=reclamationRepository.findByIdRAndClientIdCAndDeletedFalse(idR, idC)
                .orElseThrow(() -> new IllegalArgumentException("Reclamation not found or not owned by client"));
        reclamation.setDeleted(true);
        reclamationRepository.save(reclamation);
        Map<String, String> response =new HashMap<>();
        response.put("status","success");
        response.put("message","Reclamation deleted successfully");
        return response;
    }
}
