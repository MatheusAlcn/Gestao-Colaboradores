package com.gestao_de_colaboradores.service;

import com.gestao_de_colaboradores.dto.ColaboradorRequest;
import com.gestao_de_colaboradores.dto.ColaboradorResponse;
import com.gestao_de_colaboradores.entity.Cargo;
import com.gestao_de_colaboradores.entity.Colaborador;
import com.gestao_de_colaboradores.entity.Setor;
import com.gestao_de_colaboradores.repository.CargoRepository;
import com.gestao_de_colaboradores.repository.ColaboradorRepository;
import com.gestao_de_colaboradores.repository.SetorRepository;
import com.gestao_de_colaboradores.specification.ColaboradorSpecification;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * Serviço responsável pelo gerenciamento de colaboradores.
 * Contém métodos para CRUD completo, soft delete e busca dinâmica por filtros.
 *
 * Responsável por:
 * - Garantir a integridade de dados (cargo e setor válidos)
 * - Transformar entidades em DTOs para o frontend
 * - Gerenciar transações de forma segura
 */

@Service
public class ColaboradorService {

    private final ColaboradorRepository colabRepo;
    private final CargoRepository cargoRepo;
    private final SetorRepository setorRepo;

    public ColaboradorService(ColaboradorRepository colabRepo, CargoRepository cargoRepo, SetorRepository setorRepo) {
        this.colabRepo = colabRepo;
        this.cargoRepo = cargoRepo;
        this.setorRepo = setorRepo;
    }

    @Transactional
    public ColaboradorResponse create(ColaboradorRequest r) {
        Cargo cargo = cargoRepo.findById(r.cargoId)
                .orElseThrow(() -> new EntityNotFoundException("Cargo não encontrado: " + r.cargoId));
        Setor setor = setorRepo.findById(r.setorId)
                .orElseThrow(() -> new EntityNotFoundException("Setor não encontrado: " + r.setorId));

        Colaborador c = new Colaborador();
        c.setNome(r.nome);
        c.setEmail(r.email);
        c.setCpf(r.cpf);
        c.setDataAdmissao(r.dataAdmissao);
        c.setCargo(cargo);
        c.setSetor(setor);
        c.setAtivo(true);

        return toResponse(colabRepo.save(c));
    }

    public List<ColaboradorResponse> list() {
        return colabRepo.findAll().stream().map(this::toResponse).toList();
    }

    public ColaboradorResponse get(Long id) {
        Colaborador c = colabRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("Colaborador não encontrado: " + id));
        return toResponse(c);
    }

    @Transactional
    public ColaboradorResponse update(Long id, ColaboradorRequest r) {
        Colaborador c = colabRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("Colaborador não encontrado: " + id));

        if (r.nome != null) c.setNome(r.nome);
        if (r.email != null) c.setEmail(r.email);
        if (r.cpf != null) c.setCpf(r.cpf);
        if (r.dataAdmissao != null) c.setDataAdmissao(r.dataAdmissao);
        if (r.cargoId != null) {
            Cargo cargo = cargoRepo.findById(r.cargoId)
                    .orElseThrow(() -> new EntityNotFoundException("Cargo não encontrado: " + r.cargoId));
            c.setCargo(cargo);
        }
        if (r.setorId != null) {
            Setor setor = setorRepo.findById(r.setorId)
                    .orElseThrow(() -> new EntityNotFoundException("Setor não encontrado: " + r.setorId));
            c.setSetor(setor);
        }

        return toResponse(c);
    }

    @Transactional
    public void softDelete(Long id) {
        Colaborador c = colabRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("Colaborador não encontrado: " + id));
        c.setAtivo(false);
    }

    /*** NOVO MÉTODO: busca por filtros dinâmicos ***/
    public List<ColaboradorResponse> search(String nome, String setor, String cargo) {
        Specification<Colaborador> spec = ColaboradorSpecification.comFiltros(nome, setor, cargo);
        List<Colaborador> colaboradores = colabRepo.findAll(spec);
        return colaboradores.stream().map(this::toResponse).toList();
    }

    private ColaboradorResponse toResponse(Colaborador c) {
        ColaboradorResponse resp = new ColaboradorResponse();
        resp.id = c.getId();
        resp.nome = c.getNome();
        resp.email = c.getEmail();
        resp.cpf = c.getCpf();
        resp.dataAdmissao = c.getDataAdmissao();
        resp.ativo = c.isAtivo();
        if (c.getCargo() != null) {
            resp.cargoId = c.getCargo().getId();
            resp.cargoNome = c.getCargo().getNome();
        }
        if (c.getSetor() != null) {
            resp.setorId = c.getSetor().getId();
            resp.setorNome = c.getSetor().getNome();
        }
        return resp;
    }
}
