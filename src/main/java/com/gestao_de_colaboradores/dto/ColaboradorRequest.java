package com.gestao_de_colaboradores.dto;

import java.time.LocalDate;

/**
 * DTO (Data Transfer Object) utilizado para criar ou atualizar um colaborador.
 * Representa os dados que o frontend envia para a API.
 */

public class ColaboradorRequest {
    public String nome;
    public String email;
    public String cpf;
    public LocalDate dataAdmissao;
    public Long cargoId;
    public Long setorId;
}
