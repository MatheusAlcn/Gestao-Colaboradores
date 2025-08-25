package com.gestao_de_colaboradores.dto;

import java.time.LocalDate;

public class ColaboradorRequest {
    public String nome;
    public String email;
    public String cpf;
    public LocalDate dataAdmissao;
    public Long cargoId;
    public Long setorId;
}
