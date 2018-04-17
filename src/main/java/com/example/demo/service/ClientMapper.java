package com.example.demo.service;

import com.example.demo.dto.ClientDTO;
import com.example.demo.entity.Client;
import org.springframework.stereotype.Component;

@Component
public class ClientMapper {
    public ClientDTO map(Client entity) {
        ClientDTO dto = new ClientDTO();
        dto.setNom(entity.getNom());
        dto.setPrenom(entity.getPrenom());
        return dto;
    }
}
