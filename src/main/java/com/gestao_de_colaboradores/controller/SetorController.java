package com.gestao_de_colaboradores.controller;

import com.gestao_de_colaboradores.entity.Setor;
import com.gestao_de_colaboradores.repository.SetorRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller responsável pelos setores.
 * Permite CRUD completo para a entidade Setor.
 */

@RestController
@RequestMapping("/setores")
@CrossOrigin("*")
public class SetorController {

    private final SetorRepository repo;

    public SetorController(SetorRepository repo) { this.repo = repo; }

    @PostMapping
    public ResponseEntity<Setor> create(@RequestBody Setor setor) {
        return ResponseEntity.ok(repo.save(setor));
    }

    @GetMapping
    public List<Setor> list() { return repo.findAll(); }

    @GetMapping("/{id}")
    public ResponseEntity<Setor> get(@PathVariable Long id) {
        return repo.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Setor> update(@PathVariable Long id, @RequestBody Setor body) {
        return repo.findById(id).map(s -> {
            s.setNome(body.getNome());
            return ResponseEntity.ok(repo.save(s));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!repo.existsById(id)) return ResponseEntity.notFound().build();
        repo.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
