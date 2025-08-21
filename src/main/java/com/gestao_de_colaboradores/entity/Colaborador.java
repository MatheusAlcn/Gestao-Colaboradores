package com.gestao_de_colaboradores.entity;

import jakarta.persistence.*;

@Entity
public class Colaborador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String cargo;
    private String email;
    private String setor;

    private boolean ativo = true; // soft delete

    // --- Getters ---
    public Long getId() { return id; }
    public String getNome() { return nome; }
    public String getCargo() { return cargo; }
    public String getEmail() { return email; }
    public String getSetor() { return setor; }
    public boolean isAtivo() { return ativo; }

    // --- Setters ---
    public void setId(Long id) { this.id = id; }
    public void setNome(String nome) { this.nome = nome; }
    public void setCargo(String cargo) { this.cargo = cargo; }
    public void setEmail(String email) { this.email = email; }
    public void setSetor(String setor) { this.setor = setor; }
    public void setAtivo(boolean ativo) { this.ativo = ativo; }
}
