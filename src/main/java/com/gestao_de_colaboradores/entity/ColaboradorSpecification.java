package com.gestao_de_colaboradores.specification;

import com.gestao_de_colaboradores.entity.Colaborador;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class ColaboradorSpecification {

    public static Specification<Colaborador> comFiltros(String nome, String setor, String cargo) {
        return (root, query, builder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (nome != null && !nome.isEmpty()) {
                predicates.add(builder.like(builder.lower(root.get("nome")), "%" + nome.toLowerCase() + "%"));
            }

            if (setor != null && !setor.isEmpty()) {
                predicates.add(builder.equal(root.get("setor").get("nome"), setor));
            }

            if (cargo != null && !cargo.isEmpty()) {
                predicates.add(builder.equal(root.get("cargo").get("nome"), cargo));
            }

            // Só retorna colaboradores ativos
            predicates.add(builder.isTrue(root.get("ativo")));

            return builder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
