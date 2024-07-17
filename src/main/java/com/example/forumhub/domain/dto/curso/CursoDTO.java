package com.example.forumhub.domain.dto.curso;


import com.example.forumhub.domain.models.Curso;

public record CursoDTO(
        String nome,
        Long id,
        String categoria
) {
    public CursoDTO(Curso curso) {
        this(curso.getNome(),curso.getId(), curso.getCategoria());
    }
}
