package com.gestao_de_colaboradores.service;

import com.gestao_de_colaboradores.dto.ColaboradorRequest;
import com.gestao_de_colaboradores.dto.ColaboradorResponse;
import com.gestao_de_colaboradores.entity.Colaborador;
import com.gestao_de_colaboradores.exception.BusinessException;
import com.gestao_de_colaboradores.repository.ColaboradorRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ColaboradorServiceImpl implements ColaboradorService {

    private final ColaboradorRepository colaboradorRepository;
    private final ModelMapper mapper;

    @Override
    public ColaboradorResponse create(ColaboradorRequest req) {
        if (colaboradorRepository.existsByCpf(req.getCpf())) {
            throw new BusinessException("Já existe um colaborador com este CPF");
        }
        if (colaboradorRepository.existsByEmail(req.getEmail())) {
            throw new BusinessException("Já existe um colaborador com este e-mail");
        }

        Colaborador colaborador = mapper.map(req, Colaborador.class);
        colaboradorRepository.save(colaborador);
        return mapper.map(colaborador, ColaboradorResponse.class);
    }

    @Override
    public ColaboradorResponse get(Long id) {
        Colaborador colaborador = colaboradorRepository.findByIdAndAtivoTrue(id)
                .orElseThrow(() -> new EntityNotFoundException("Colaborador não encontrado"));
        return mapper.map(colaborador, ColaboradorResponse.class);
    }

    @Override
    public List<ColaboradorResponse> list() {
        return colaboradorRepository.findAll().stream()
                .filter(Colaborador::isAtivo)
                .map(c -> mapper.map(c, ColaboradorResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public ColaboradorResponse update(Long id, ColaboradorRequest req) {
        Colaborador colaborador = colaboradorRepository.findByIdAndAtivoTrue(id)
                .orElseThrow(() -> new EntityNotFoundException("Colaborador não encontrado"));

        if (!colaborador.getCpf().equals(req.getCpf()) &&
                colaboradorRepository.existsByCpf(req.getCpf())) {
            throw new BusinessException("Já existe um colaborador com este CPF");
        }

        if (!colaborador.getEmail().equals(req.getEmail()) &&
                colaboradorRepository.existsByEmail(req.getEmail())) {
            throw new BusinessException("Já existe um colaborador com este e-mail");
        }

        colaborador.setNome(req.getNome());
        colaborador.setEmail(req.getEmail());
        colaborador.setCpf(req.getCpf());
        colaborador.setCargo(req.getCargo());
        colaborador.setSetor(req.getSetor());
        colaborador.setDataAdmissao(req.getDataAdmissao());

        colaboradorRepository.save(colaborador);
        return mapper.map(colaborador, ColaboradorResponse.class);
    }

    @Override
    public void softDelete(Long id) {
        Colaborador colaborador = colaboradorRepository.findByIdAndAtivoTrue(id)
                .orElseThrow(() -> new EntityNotFoundException("Colaborador não encontrado"));
        colaborador.setAtivo(false);
        colaboradorRepository.save(colaborador);
    }

    @Override
    public List<ColaboradorResponse> search(String nome, String setor, String cargo) {
        return colaboradorRepository
                .findByNomeContainingIgnoreCaseAndSetorContainingIgnoreCaseAndCargoContainingIgnoreCase(
                        nome == null ? "" : nome,
                        setor == null ? "" : setor,
                        cargo == null ? "" : cargo
                ).stream()
                .filter(Colaborador::isAtivo)
                .map(c -> mapper.map(c, ColaboradorResponse.class))
                .collect(Collectors.toList());
    }
}
