package com.example.forumhub.domain.models;

import com.example.forumhub.domain.dto.curso.CursoCadastroDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@Table(name = "cursos")
@EqualsAndHashCode(of = "id")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nome;

    private String categoria;

    public Curso(CursoCadastroDTO dto) {
        this.nome = dto.nome();
        if (dto.categoria() != null) {
            this.categoria = dto.categoria();
        }
    }
}
