package com.gestao_de_colaboradores.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Colaborador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @ManyToOne
    @JoinColumn(name = "cargo_id")
    private Cargo cargo;

    private String email;
    private String cpf;

    @ManyToOne
    @JoinColumn(name = "setor_id")
    private Setor setor;

    private LocalDate dataAdmissao;
    private boolean ativo = true; // soft delete

    // --- Getters ---
    public Long getId() { return id; }
    public String getNome() { return nome; }
    public Cargo getCargo() { return cargo; }
    public String getEmail() { return email; }
    public String getCpf() { return cpf; }
    public Setor getSetor() { return setor; }
    public LocalDate getDataAdmissao() { return dataAdmissao; }
    public boolean isAtivo() { return ativo; }

    // --- Setters ---
    public void setId(Long id) { this.id = id; }
    public void setNome(String nome) { this.nome = nome; }
    public void setCargo(Cargo cargo) { this.cargo = cargo; }
    public void setEmail(String email) { this.email = email; }
    public void setCpf(String cpf) { this.cpf = cpf; }
    public void setSetor(Setor setor) { this.setor = setor; }
    public void setDataAdmissao(LocalDate dataAdmissao) { this.dataAdmissao = dataAdmissao; }
    public void setAtivo(boolean ativo) { this.ativo = ativo; }
}
