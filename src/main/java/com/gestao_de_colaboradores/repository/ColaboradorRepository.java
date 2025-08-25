package com.gestao_de_colaboradores.repository;

import com.gestao_de_colaboradores.entity.Colaborador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ColaboradorRepository extends JpaRepository<Colaborador, Long>, JpaSpecificationExecutor<Colaborador> {
    // Aqui você pode adicionar queries personalizadas se precisar
}
