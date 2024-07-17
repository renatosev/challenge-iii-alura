package com.example.forumhub.controllers;

import com.example.forumhub.domain.dto.topico.TopicoAtualizacaoDTO;
import com.example.forumhub.domain.dto.topico.TopicoCadastroDTO;
import com.example.forumhub.domain.dto.topico.TopicoDTO;
import com.example.forumhub.domain.services.TopicosService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;


@RestController
@RequestMapping("/topicos")
@SecurityRequirement(name = "bearer-key")
public class TopicosController {
    @Autowired
    private TopicosService topicosService;

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deletarTopico(@PathVariable Long id){
        this.topicosService.deletarTopico(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    @Transactional
    public  ResponseEntity atualizarTopico(@RequestBody @Valid TopicoAtualizacaoDTO dto){
        var topicoDto = this.topicosService.atualizarTopico(dto);
        return ResponseEntity.ok(topicoDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity buscarTopico(@PathVariable Long id){
        var topico = this.topicosService.buscarTopico(id);
        return ResponseEntity.ok(topico);

    }

    @GetMapping
    public ResponseEntity<List<TopicoDTO>> listarTopicos(){
        var listagemTopicos = this.topicosService.listarTopicos();
        return ResponseEntity.ok(listagemTopicos);
    }

    @PostMapping
    @Transactional
    public ResponseEntity cadastrarTopico(@RequestBody @Valid TopicoCadastroDTO dto){
        var topicoDTO = this.topicosService.salvarTopico(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(topicoDTO.id()).toUri();
       return ResponseEntity.created(uri).body(topicoDTO);
    }
}
