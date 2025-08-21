package com.gestao_de_colaboradores.controller;


import com.gestao_de_colaboradores.service.colaboradorService;
import org.springframework.web.bind.annotation.*;


import java.util.List;


@RestController
@RequestMapping("/colaboradores")
public class colaboradorController {
    private final colaboradorService service;


    public colaboradorController(colaboradorService service) {
        this.service = service;
    }


    @GetMapping
    public List<com.gestao_colaboradores.entity.colaborador> getAll() {
        return service.findAll();
    }


    @PostMapping
    public com.gestao_colaboradores.entity.colaborador create(@RequestBody com.gestao_colaboradores.entity.colaborador c) {
        return service.save(c);
    }
}
