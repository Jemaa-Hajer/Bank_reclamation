package com.HJ.BankReclamation.service;

import com.HJ.BankReclamation.configuration.JwtUtil;
import com.HJ.BankReclamation.dtos.ClientDto;
import com.HJ.BankReclamation.dtos.ClientResponseDto;
import com.HJ.BankReclamation.dtos.LoginRequestDto;
import com.HJ.BankReclamation.model.Client;
import com.HJ.BankReclamation.model.ClientStatus;
import com.HJ.BankReclamation.repository.ClientRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final ClientRepository clientRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;


    public AuthService(ClientRepository clientRepository, BCryptPasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.clientRepository = clientRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    public void signup(ClientDto clientDto) {
        if (clientRepository.findByEmail(clientDto.getEmail()) != null) {
            throw new IllegalArgumentException("Email already exists");
        }
        Client client = new Client();
        client.setName(clientDto.getName());
        client.setSurname(clientDto.getSurname());
        client.setEmail(clientDto.getEmail());
        client.setMobile(clientDto.getMobile());
        client.setNumC(clientDto.getNumC());
        client.setPwd(passwordEncoder.encode(clientDto.getPwd()));
        client.setStatus(ClientStatus.active);
        client.setDateC(java.time.LocalDateTime.now());
        clientRepository.save(client);
    }

    public ClientResponseDto login (LoginRequestDto loginRequestDto){
        Client client= clientRepository.findByEmail(loginRequestDto.getEmail());
        if (client==null){
            throw new IllegalArgumentException(("Invalid Email or password"));
        }
        if (client.getStatus() != ClientStatus.active){
            throw new IllegalArgumentException(("Account is inactive"));
        }
        if (!passwordEncoder.matches(loginRequestDto.getPassword(), client.getPwd())){
            throw new IllegalArgumentException(("Invalid Email or password"));
        }
        ClientResponseDto response = new ClientResponseDto();
        response.setIdC(client.getIdC());
        response.setEmail(client.getEmail());
        response.setName(client.getName());
        response.setSurname(client.getSurname());
        response.setToken(jwtUtil.generateToken(client.getIdC()));
        return response;
    }
}
