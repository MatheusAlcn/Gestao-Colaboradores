package com.gestao_de_colaboradores.excepition;

public class ColaboradorNotFoundException extends RuntimeException {
    public ColaboradorNotFoundException(Long id) {
        super("Colaborador com ID " + id + " não encontrado.");
    }
}
