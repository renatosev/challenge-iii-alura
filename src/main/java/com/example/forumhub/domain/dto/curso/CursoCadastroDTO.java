package com.example.forumhub.domain.dto.curso;

import jakarta.validation.constraints.NotBlank;

public record CursoCadastroDTO(
        @NotBlank
        String nome,
        String categoria
) {
}
