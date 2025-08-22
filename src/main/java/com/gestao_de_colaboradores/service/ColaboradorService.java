package com.gestao_de_colaboradores.service;

import com.gestao_de_colaboradores.entity.Colaborador;
import com.gestao_de_colaboradores.repository.ColaboradorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ColaboradorService {

    private final ColaboradorRepository colaboradorRepository;

    public ColaboradorService(ColaboradorRepository colaboradorRepository) {
        this.colaboradorRepository = colaboradorRepository;
    }

    public List<Colaborador> listarTodos() {
        return colaboradorRepository.findAll().stream()
                .filter(Colaborador::isAtivo)
                .toList();
    }

    public Optional<Colaborador> buscarPorId(Long id) {
        return colaboradorRepository.findById(id)
                .filter(Colaborador::isAtivo);
    }

    public Colaborador criar(Colaborador colaborador) {
        return colaboradorRepository.save(colaborador);
    }

    public Optional<Colaborador> atualizar(Long id, Colaborador dados) {
        return colaboradorRepository.findById(id)
                .filter(Colaborador::isAtivo)
                .map(col -> {
                    col.setNome(dados.getNome());
                    col.setEmail(dados.getEmail());
                    col.setCargo(dados.getCargo());
                    col.setSetor(dados.getSetor());
                    col.setDataAdmissao(dados.getDataAdmissao());
                    return colaboradorRepository.save(col);
                });
    }

    public boolean deletar(Long id) {
        return colaboradorRepository.findById(id)
                .filter(Colaborador::isAtivo)
                .map(col -> {
                    col.setAtivo(false);
                    colaboradorRepository.save(col);
                    return true;
                }).orElse(false);
    }
}
