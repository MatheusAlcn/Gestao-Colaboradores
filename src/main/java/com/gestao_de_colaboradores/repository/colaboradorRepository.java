package com.Gestao_Colaboradores.repository;


import com.gestao_colaboradores.entity.colaborador;
import org.springframework.data.jpa.repository.JpaRepository;


public interface colaboradorRepository extends JpaRepository<colaborador, Long> {}