package com.example.forumhub.domain.dto.topico;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record TopicoAtualizacaoDTO(
        @NotNull
        Long id,

        @NotBlank
        String mensagem,

        @NotNull
        @Future
        LocalDateTime data,

        @NotBlank
        String status
) {

}
