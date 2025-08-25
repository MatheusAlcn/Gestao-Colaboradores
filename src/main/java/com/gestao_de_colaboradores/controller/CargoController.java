package com.gestao_de_colaboradores.controller;

import com.gestao_de_colaboradores.entity.Cargo;
import com.gestao_de_colaboradores.repository.CargoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cargos")
@CrossOrigin("*")
public class CargoController {

    private final CargoRepository repo;

    public CargoController(CargoRepository repo) { this.repo = repo; }

    @PostMapping
    public ResponseEntity<Cargo> create(@RequestBody Cargo cargo) {
        return ResponseEntity.ok(repo.save(cargo));
    }

    @GetMapping
    public List<Cargo> list() { return repo.findAll(); }

    @GetMapping("/{id}")
    public ResponseEntity<Cargo> get(@PathVariable Long id) {
        return repo.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cargo> update(@PathVariable Long id, @RequestBody Cargo body) {
        return repo.findById(id).map(c -> {
            c.setNome(body.getNome());
            return ResponseEntity.ok(repo.save(c));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!repo.existsById(id)) return ResponseEntity.notFound().build();
        repo.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
