package com.HJ.BankReclamation.controller;

import com.HJ.BankReclamation.dtos.ReclamationDto;
import com.HJ.BankReclamation.dtos.ReclamationResponseDto;
import com.HJ.BankReclamation.service.ReclamationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
@RestController
@RequestMapping("api/reclamations")
public class ReclamationController {
    private  final ReclamationService reclamationService;


    public ReclamationController(ReclamationService reclamationService) {
        this.reclamationService = reclamationService;
    }

    @PostMapping("/add")
    public ResponseEntity<Map<String,String>> createReclamation(@Valid @RequestBody ReclamationDto reclamationDto, HttpServletRequest request){
        Integer idC = (Integer) request.getAttribute("idC");
        if (idC==null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        Map<String, String> response = reclamationService.createReclamation(reclamationDto, idC);
        return ResponseEntity.status(201).body(response);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<ReclamationResponseDto>> getReclamations(HttpServletRequest request){
        Integer idC =(Integer) request.getAttribute("idC");
        if (idC == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        List<ReclamationResponseDto> reclamations = reclamationService.getReclamationByClient(idC);
        return ResponseEntity.ok(reclamations);
    }

    @PutMapping("/update/{idR}")
    public ResponseEntity<Map<String,String>> updateReclamaton (@PathVariable Integer idR, @Valid @RequestBody ReclamationDto reclamationDto, HttpServletRequest request){
        Integer idC = (Integer) request.getAttribute("idC");
        if (idC==null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        Map<String, String> response = reclamationService.updateReclamation(idR, reclamationDto, idC);
        return ResponseEntity.ok(response);

    }

    @DeleteMapping("/delete/{idR}")
    public ResponseEntity<Map<String ,String>> deleteReclamation (@PathVariable Integer idR,HttpServletRequest request){
        Integer idC = (Integer) request.getAttribute("idC");
        if (idC == null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        Map<String,String> response= reclamationService.deleteReclamation( idR,idC);
        return ResponseEntity.ok(response);
    }
}
