package com.gestao_de_colaboradores.controller;

import com.gestao_de_colaboradores.entity.Colaborador;
import com.gestao_de_colaboradores.service.ColaboradorService;
import jakarta.validation.Valid;
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
<<<<<<< HEAD
    public ResponseEntity<ColaboradorResponse> create(@Valid @RequestBody ColaboradorRequest req) {
        return ResponseEntity.ok(service.create(req));
    }

    @GetMapping
    public ResponseEntity<List<ColaboradorResponse>> list() {
        return ResponseEntity.ok(service.list());
=======
    public ResponseEntity<Colaborador> create(@RequestBody Colaborador colaborador) {
        return ResponseEntity.ok(service.create(colaborador));
    }

    @GetMapping
    public List<Colaborador> list() {
        return service.list();
>>>>>>> branch-functional
    }

    @GetMapping("/{id}")
    public ResponseEntity<Colaborador> get(@PathVariable Long id) {
        return ResponseEntity.ok(service.get(id));
    }

    @PutMapping("/{id}")
<<<<<<< HEAD
    public ResponseEntity<ColaboradorResponse> update(
            @PathVariable Long id,
            @Valid @RequestBody ColaboradorRequest req) {
        return ResponseEntity.ok(service.update(id, req));
=======
    public ResponseEntity<Colaborador> update(@PathVariable Long id, @RequestBody Colaborador colaborador) {
        return ResponseEntity.ok(service.update(id, colaborador));
>>>>>>> branch-functional
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.softDelete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
<<<<<<< HEAD
    public ResponseEntity<List<ColaboradorResponse>> search(
=======
    public List<Colaborador> search(
>>>>>>> branch-functional
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) String setor,
            @RequestParam(required = false) String cargo) {
        return ResponseEntity.ok(service.search(nome, setor, cargo));
    }
}
