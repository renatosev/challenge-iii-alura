package com.example.forumhub.domain.services;

import com.example.forumhub.domain.dto.topico.TopicoAtualizacaoDTO;
import com.example.forumhub.domain.dto.topico.TopicoCadastroDTO;
import com.example.forumhub.domain.dto.topico.TopicoDTO;
import com.example.forumhub.domain.exceptions.TopicoDelete;
import com.example.forumhub.domain.exceptions.TopicoNaoEncontrado;
import com.example.forumhub.domain.exceptions.UsuarioNaoEncontrado;
import com.example.forumhub.domain.models.Topico;
import com.example.forumhub.domain.repositorys.ICursoRepository;
import com.example.forumhub.domain.repositorys.ITopicoRepository;
import com.example.forumhub.domain.repositorys.IUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TopicosService {
    @Autowired
    private ITopicoRepository topicoRepository;

    @Autowired
    private IUsuarioRepository usuarioRepository;

    @Autowired
    private ICursoRepository cursoRepository;

    @Transactional
    public TopicoDTO salvarTopico(TopicoCadastroDTO dto) {
        Topico topico = new Topico(dto);
        topico.setAutor(this.usuarioRepository.getReferenceById(dto.autor().id()));
        topico.setCurso(this.cursoRepository.getReferenceById(dto.curso().id()));
        this.topicoRepository.save(topico);
        TopicoDTO topicoDTO = new TopicoDTO(topico);
        return topicoDTO;
    }

    public List<TopicoDTO> listarTopicos() {
        return this.topicoRepository.findAll().stream()
                .map(TopicoDTO::new).collect(Collectors.toList());
    }

    public TopicoDTO buscarTopico(Long id) {
        var topico = this.topicoRepository.findById(id).orElseThrow(()-> new UsuarioNaoEncontrado("Verifique o nome de usuario novamente."));
        TopicoDTO topicoDTO = new TopicoDTO(topico);
        return topicoDTO;
    }

    @Transactional
    public TopicoDTO atualizarTopico(TopicoAtualizacaoDTO dto) {
        Topico topico = this.topicoRepository.findById(dto.id())
                .orElseThrow(() -> new TopicoNaoEncontrado("Verifique o nome do topico novamente."));

        if (dto.status() != null) {
            topico.setStatus(dto.status());
        }
        if (dto.mensagem() != null) {
            topico.setMensagem(dto.mensagem());
        }
        topico.setDataAtualizacao(dto.data());

        this.topicoRepository.save(topico);

        TopicoDTO topicoDTO = new TopicoDTO(topico);

        return topicoDTO;
    }

    @Transactional
    public void deletarTopico(Long id) {
        buscarTopico(id);
        try {
            this.topicoRepository.deleteById(id);

        } catch (Exception e) {
            throw new TopicoDelete("Esse topico nao pode ser removido");
        }
    }
}
