package com.example.forumhub.domain.dto.topico;

import com.example.forumhub.domain.dto.curso.CursoDTO;
import com.example.forumhub.domain.dto.usuario.UsuarioDTO;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record TopicoCadastroDTO(
        @NotBlank
        String titulo,

        @NotBlank
        String mensagem,

        @NotNull
        UsuarioDTO autor,

        @NotNull
        @Future
        LocalDateTime data,

        @NotBlank
        String status,

        @NotNull
        CursoDTO curso
) {
}
