package com.example.forumhub.controllers;

import com.example.forumhub.domain.dto.curso.CursoAtualizacaoDTO;
import com.example.forumhub.domain.dto.curso.CursoCadastroDTO;
import com.example.forumhub.domain.dto.curso.CursoDTO;
import com.example.forumhub.domain.services.CursoService;
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
@RequestMapping("/cursos")
@SecurityRequirement(name = "bearer-key")
public class CursoController {
    @Autowired
    private CursoService cursoService;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrarCurso(@RequestBody @Valid CursoCadastroDTO dto) {
        CursoDTO cursoDTO = this.cursoService.cadastrarCurso(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(cursoDTO.id()).toUri();
        return ResponseEntity.created(uri).body(cursoDTO);
    }

    @GetMapping
    public  ResponseEntity<List<CursoDTO>> listarCursos(){
        var listagemCursos = this.cursoService.listarCursos();
        return ResponseEntity.ok(listagemCursos);
    }

    @GetMapping("/{id}")
    public ResponseEntity buscarCurso(@PathVariable Long id){
        var cursoDTO = this.cursoService.buscarCurso(id);
        return ResponseEntity.ok(cursoDTO);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity atualizarCurso(@RequestBody @Valid CursoAtualizacaoDTO dto){
        var cursoDTO = this.cursoService.atualizarCurso(dto);
        return ResponseEntity.ok(cursoDTO);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deletarCurso(@PathVariable Long id){
        this.cursoService.deletarCursso(id);
        return ResponseEntity.noContent().build();
    }
}
