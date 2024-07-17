package com.example.forumhub.domain.dto.topico;

import com.example.forumhub.domain.dto.curso.CursoDTO;
import com.example.forumhub.domain.dto.usuario.UsuarioDTO;
import com.example.forumhub.domain.models.Topico;


import java.time.LocalDateTime;

public record TopicoDTO(
        Long id,
        String titulo,
        String mensagem,
        UsuarioDTO autor,
        LocalDateTime data,
        String status,
        CursoDTO curso
) {
    public TopicoDTO(Topico topico) {
        this(topico.getId(), topico.getTitulo(), topico.getMensagem(), new UsuarioDTO(topico.getAutor()), topico.getDataCriacao(), topico.getStatus(), new CursoDTO(topico.getCurso()));
    }
}
