package com.example.forumhub.controllers;

import com.example.forumhub.domain.dto.usuario.*;
import com.example.forumhub.domain.models.Usuario;
import com.example.forumhub.domain.services.UsuarioService;
import com.example.forumhub.infra.security.TokenService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/usuarios")
@SecurityRequirement(name = "bearer-key")
public class UsuarioController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/login")
    public ResponseEntity realizarLogin(@RequestBody @Valid UsuarioAutenticacaoDTO dto) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(dto.email(), dto.senha());
        var authentication = this.authenticationManager.authenticate(authenticationToken);
        var tokenJwt = tokenService.gerarToken((Usuario) authentication.getPrincipal());

        return ResponseEntity.ok(new TokenUsuarioDTO(tokenJwt));
    }

    @PostMapping
    @Transactional
    public ResponseEntity cadastrarUsuario(@RequestBody @Valid UsuarioCadastroDTO dto) {
        var usuarioDTO = this.usuarioService.cadastrarUsuario(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(usuarioDTO.id()).toUri();
        return ResponseEntity.created(uri).body(usuarioDTO);
    }

    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> listarUsuarios(){
        var listagemUsuario = this.usuarioService.listarUsuarios();
        return ResponseEntity.ok(listagemUsuario);
    }

    @GetMapping("/{id}")
    public  ResponseEntity buscrUsuario(@PathVariable Long id){
        UsuarioDTO usuarioDTO = this.usuarioService.buscarUsuario(id);
        return ResponseEntity.ok(usuarioDTO);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity atualizarUsuario(@RequestBody @Valid UsuarioAtualizacaoDTO dto){
        UsuarioDTO usuarioDTO = this.usuarioService.atualizarUsuario(dto);
        return  ResponseEntity.ok(usuarioDTO);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public  ResponseEntity deletarUsuario(@PathVariable Long id){
        this.usuarioService.deletarUsuario(id);
        return ResponseEntity.noContent().build();
    }

}
