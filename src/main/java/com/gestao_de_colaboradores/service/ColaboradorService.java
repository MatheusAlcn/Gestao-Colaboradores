package com.gestao_de_colaboradores.service;

import com.gestao_de_colaboradores.entity.Colaborador;
import com.gestao_de_colaboradores.entity.Cargo;
import com.gestao_de_colaboradores.entity.Setor;
import com.gestao_de_colaboradores.repository.ColaboradorRepository;
import com.gestao_de_colaboradores.repository.CargoRepository;
import com.gestao_de_colaboradores.repository.SetorRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
    public Colaborador create(Colaborador c) {
        // Validar cargo e setor
        if (c.getCargo() != null) {
            Cargo cargo = cargoRepo.findById(c.getCargo().getId())
                    .orElseThrow(() -> new EntityNotFoundException("Cargo não encontrado: " + c.getCargo().getId()));
            c.setCargo(cargo);
        }

        if (c.getSetor() != null) {
            Setor setor = setorRepo.findById(c.getSetor().getId())
                    .orElseThrow(() -> new EntityNotFoundException("Setor não encontrado: " + c.getSetor().getId()));
            c.setSetor(setor);
        }

        c.setAtivo(true);
        return colabRepo.save(c);
    }

    public List<Colaborador> list() {
        return colabRepo.findAll().stream()
                .filter(Colaborador::isAtivo)
                .toList();
    }

    public Colaborador get(Long id) {
        return colabRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Colaborador não encontrado: " + id));
    }

    @Transactional
    public Colaborador update(Long id, Colaborador cAtualizado) {
        Colaborador c = colabRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Colaborador não encontrado: " + id));

        c.setNome(cAtualizado.getNome());
        c.setEmail(cAtualizado.getEmail());
        c.setCpf(cAtualizado.getCpf());
        c.setDataAdmissao(cAtualizado.getDataAdmissao());

        if (cAtualizado.getCargo() != null) {
            Cargo cargo = cargoRepo.findById(cAtualizado.getCargo().getId())
                    .orElseThrow(() -> new EntityNotFoundException("Cargo não encontrado: " + cAtualizado.getCargo().getId()));
            c.setCargo(cargo);
        }

        if (cAtualizado.getSetor() != null) {
            Setor setor = setorRepo.findById(cAtualizado.getSetor().getId())
                    .orElseThrow(() -> new EntityNotFoundException("Setor não encontrado: " + cAtualizado.getSetor().getId()));
            c.setSetor(setor);
        }

        return colabRepo.save(c);
    }

    @Transactional
    public void softDelete(Long id) {
        Colaborador c = colabRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Colaborador não encontrado: " + id));
        c.setAtivo(false);
        colabRepo.save(c);
    }

    public List<Colaborador> search(String nome, String setor, String cargo) {
        return colabRepo.findAll().stream()
                .filter(c -> c.isAtivo()
                        && (nome == null || c.getNome().toLowerCase().contains(nome.toLowerCase()))
                        && (setor == null || c.getSetor() != null && c.getSetor().getNome().toLowerCase().contains(setor.toLowerCase()))
                        && (cargo == null || c.getCargo() != null && c.getCargo().getNome().toLowerCase().contains(cargo.toLowerCase()))
                )
                .toList();
    }
}
