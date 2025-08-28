package com.gestao_de_colaboradores.service;

import com.gestao_de_colaboradores.dto.ColaboradorRequest;
import com.gestao_de_colaboradores.dto.ColaboradorResponse;

import java.util.List;

public interface ColaboradorService {
    ColaboradorResponse create(ColaboradorRequest req);
    ColaboradorResponse get(Long id);
    List<ColaboradorResponse> list();
    ColaboradorResponse update(Long id, ColaboradorRequest req);
    void softDelete(Long id);
    List<ColaboradorResponse> search(String nome, String setor, String cargo);
}
