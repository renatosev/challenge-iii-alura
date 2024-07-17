package com.example.forumhub.domain.services;

import com.example.forumhub.domain.dto.curso.CursoAtualizacaoDTO;
import com.example.forumhub.domain.dto.curso.CursoCadastroDTO;
import com.example.forumhub.domain.dto.curso.CursoDTO;
import com.example.forumhub.domain.exceptions.CursoDelete;
import com.example.forumhub.domain.exceptions.CursoNaoEncontrado;
import com.example.forumhub.domain.models.Curso;
import com.example.forumhub.domain.repositorys.ICursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CursoService {

    @Autowired
    private ICursoRepository cursoRepository;

    @Transactional
    public CursoDTO cadastrarCurso(CursoCadastroDTO dto) {
        Curso curso = new Curso(dto);
        this.cursoRepository.save(curso);
        var cursoDto = new CursoDTO(curso);
        return cursoDto;
    }


    public List<CursoDTO> listarCursos() {
        var lista = this.cursoRepository.findAll();
        var listagemCursos = lista.stream().map(CursoDTO::new).collect(Collectors.toList());
        return listagemCursos;
    }

    public CursoDTO buscarCurso(Long id) {
        var curso = this.cursoRepository.findById(id).orElseThrow(()-> new CursoNaoEncontrado("Verifique o nome do curso novamente."));
        var cusoDto = new CursoDTO(curso);
        return cusoDto;
    }

    @Transactional
    public CursoDTO atualizarCurso(CursoAtualizacaoDTO dto) {
        var curso = this.cursoRepository.findById(dto.id()).orElseThrow(()-> new CursoNaoEncontrado("Verifique o nome do curso novamente."));
        if (dto.nome() != null){
            curso.setNome(dto.nome());
        }
        if (dto.categoria() != null){
            curso.setCategoria(dto.categoria());
        }
        this.cursoRepository.save(curso);
        var cursoDto = new CursoDTO(curso);
        return cursoDto;
    }

    @Transactional
    public void deletarCursso(Long id) {
        var cusro = this.cursoRepository.findById(id).orElseThrow(()-> new CursoNaoEncontrado("Verifique o nome do curso novamente."));
        try {
            this.cursoRepository.delete(cusro);
        }catch (Exception e){
            throw new CursoDelete("Esse curso nao pode ser deletado");
        }
    }
}
