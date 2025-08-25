package com.gestao_de_colaboradores.controller;

import com.gestao_de_colaboradores.dto.ColaboradorRequest;
import com.gestao_de_colaboradores.dto.ColaboradorResponse;
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
    public ResponseEntity<ColaboradorResponse> create(@RequestBody ColaboradorRequest req) {
        return ResponseEntity.ok(service.create(req));
    }

    @GetMapping
    public List<ColaboradorResponse> list() {
        return service.list();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ColaboradorResponse> get(@PathVariable Long id) {
        return ResponseEntity.ok(service.get(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ColaboradorResponse> update(@PathVariable Long id, @RequestBody ColaboradorRequest req) {
        return ResponseEntity.ok(service.update(id, req));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.softDelete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public List<ColaboradorResponse> search(
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) String setor,
            @RequestParam(required = false) String cargo) {
        return service.search(nome, setor, cargo);
    }
}
