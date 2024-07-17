package com.example.forumhub.domain.dto.usuario;

import com.example.forumhub.domain.models.Usuario;

public record UsuarioDTO(
        Long id,
        String email
) {
    public UsuarioDTO(Usuario autor) {
        this(autor.getId(),autor.getEmail());
    }
}
