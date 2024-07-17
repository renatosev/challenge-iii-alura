package com.example.forumhub.domain.dto.curso;

import jakarta.validation.constraints.NotNull;

public record CursoAtualizacaoDTO(
        String nome,
        String categoria,
        @NotNull
        Long id
) {
}
