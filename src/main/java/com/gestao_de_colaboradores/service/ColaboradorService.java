package com.gestao_de_colaboradores.service;

import com.gestao_de_colaboradores.entity.Colaborador;
import com.gestao_de_colaboradores.exception.ColaboradorNotFoundException;
import com.gestao_de_colaboradores.repository.ColaboradorRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ColaboradorService {

    private final ColaboradorRepository repository;

    public ColaboradorService(ColaboradorRepository repository) {
        this.repository = repository;
    }

    // Criar ou salvar
    public Colaborador save(Colaborador colaborador) {
        validarDados(colaborador);
        colaborador.setAtivo(true);
        return repository.save(colaborador);
    }

    // Buscar por ID
    public Colaborador findById(Long id) {
        return repository.findById(id)
                .filter(Colaborador::isAtivo)
                .orElseThrow(() -> new ColaboradorNotFoundException(id));
    }

    // Buscar todos ativos
    public List<Colaborador> findAll() {
        return repository.findAll().stream()
                .filter(Colaborador::isAtivo)
                .collect(Collectors.toList());
    }

    // Atualizar
    @Transactional
    public Colaborador update(Long id, Colaborador dadosNovos) {
        Colaborador colaboradorExistente = findById(id);
        validarDados(dadosNovos);

        colaboradorExistente.setNome(dadosNovos.getNome());
        colaboradorExistente.setCargo(dadosNovos.getCargo());
        colaboradorExistente.setEmail(dadosNovos.getEmail());
        colaboradorExistente.setSetor(dadosNovos.getSetor());

        return colaboradorExistente;
    }

    // Soft delete
    @Transactional
    public void delete(Long id) {
        Colaborador colaborador = findById(id);
        colaborador.setAtivo(false);
    }

    // Validação de dados
    private void validarDados(Colaborador colaborador) {
        if (colaborador.getNome() == null || colaborador.getNome().trim().isEmpty()) {
            throw new IllegalArgumentException("Nome não pode ser vazio");
        }
        if (colaborador.getCargo() == null || colaborador.getCargo().trim().isEmpty()) {
            throw new IllegalArgumentException("Cargo não pode ser vazio");
        }
    }
}
