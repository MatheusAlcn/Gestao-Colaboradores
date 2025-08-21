package com.gestao_de_colaboradores.service;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class colaboradorService {

    private final com.Gestao_Colaboradores.repository.colaboradorRepository repository;

    public colaboradorService(com.Gestao_Colaboradores.repository.colaboradorRepository repository) {
        this.repository = repository;
    }

    public List<com.gestao_colaboradores.entity.colaborador> findAll() {
        return repository.findAll();
    }

    public com.gestao_colaboradores.entity.colaborador save(com.gestao_colaboradores.entity.colaborador c) {
        return repository.save(c);
    }
}
