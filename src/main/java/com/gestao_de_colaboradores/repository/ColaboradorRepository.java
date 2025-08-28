package com.gestao_de_colaboradores.repository;

import com.gestao_de_colaboradores.entity.Colaborador;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ColaboradorRepository extends JpaRepository<Colaborador, Long> {
    Optional<Colaborador> findByIdAndAtivoTrue(Long id);
    boolean existsByCpf(String cpf);
    boolean existsByEmail(String email);

    List<Colaborador> findByNomeContainingIgnoreCaseAndSetorContainingIgnoreCaseAndCargoContainingIgnoreCase(
            String nome, String setor, String cargo);
}
