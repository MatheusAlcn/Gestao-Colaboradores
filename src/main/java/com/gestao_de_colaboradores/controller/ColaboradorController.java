package com.gestao_de_colaboradores.controller;

import com.gestao_de_colaboradores.entity.Colaborador;
import com.gestao_de_colaboradores.service.ColaboradorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/colaboradores")
@CrossOrigin("*")
public class ColaboradorController {

    private final ColaboradorService service;

    public ColaboradorController(ColaboradorService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Colaborador> create(@RequestBody Colaborador colaborador) {
        return ResponseEntity.ok(service.create(colaborador));
    }

    @GetMapping
    public List<Colaborador> list() {
        return service.list();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Colaborador> get(@PathVariable Long id) {
        return ResponseEntity.ok(service.get(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Colaborador> update(@PathVariable Long id, @RequestBody Colaborador colaborador) {
        return ResponseEntity.ok(service.update(id, colaborador));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.softDelete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public List<Colaborador> search(
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) String setor,
            @RequestParam(required = false) String cargo) {
        return service.search(nome, setor, cargo);
    }
}
