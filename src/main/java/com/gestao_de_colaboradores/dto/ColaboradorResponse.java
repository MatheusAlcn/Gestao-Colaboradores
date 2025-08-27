package com.gestao_de_colaboradores.dto;

import java.time.LocalDate;

/**
 * DTO utilizado para retornar informações de um colaborador para o frontend.
 * Contém dados detalhados, incluindo IDs e nomes de cargo e setor.
 */

public class ColaboradorResponse {
    public Long id;
    public String nome;
    public String email;
    public String cpf;
    public LocalDate dataAdmissao;
    public boolean ativo;
    public Long cargoId;
    public String cargoNome;
    public Long setorId;
    public String setorNome;
}
