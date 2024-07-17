package com.example.forumhub.domain.dto.usuario;

import jakarta.validation.constraints.NotNull;

public record UsuarioAtualizacaoDTO(
        String nome,

        String senha,

        @NotNull
        Long id
) {
}
