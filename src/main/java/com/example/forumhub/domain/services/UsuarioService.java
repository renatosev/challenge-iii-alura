package com.example.forumhub.domain.services;

import com.example.forumhub.domain.dto.usuario.UsuarioAtualizacaoDTO;
import com.example.forumhub.domain.dto.usuario.UsuarioCadastroDTO;
import com.example.forumhub.domain.dto.usuario.UsuarioDTO;
import com.example.forumhub.domain.exceptions.UsuarioDelete;
import com.example.forumhub.domain.exceptions.UsuarioNaoEncontrado;
import com.example.forumhub.domain.models.Usuario;
import com.example.forumhub.domain.repositorys.IUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioService implements UserDetailsService {

    @Autowired
    private IUsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return this.usuarioRepository.findByEmail(email);
    }

    @Transactional
    public UsuarioDTO cadastrarUsuario(UsuarioCadastroDTO dto) {
        Usuario usuario = new Usuario(dto);
        usuario.setSenha(passwordEncoder.encode(dto.senha()));
        this.usuarioRepository.save(usuario);
        var usuarioDto = new UsuarioDTO(usuario);
        return usuarioDto;
    }

    public List<UsuarioDTO> listarUsuarios() {
        var lista = this.usuarioRepository.findAll();
        var listagemUsuarios = lista.stream().map(UsuarioDTO::new).collect(Collectors.toList());
        return listagemUsuarios;
    }

    public UsuarioDTO buscarUsuario(Long id) {
        Usuario usuario = this.usuarioRepository.findById(id).orElseThrow(()-> new UsuarioNaoEncontrado("Verifique o nome de usuario novamente."));
        UsuarioDTO usuarioDTO = new UsuarioDTO(usuario);
        return  usuarioDTO;
    }

    @Transactional
    public UsuarioDTO atualizarUsuario(UsuarioAtualizacaoDTO dto) {
        Usuario usuario = this.usuarioRepository.findById(dto.id()).orElseThrow(()-> new UsuarioNaoEncontrado("Verifique o nome de usuario novamente."));
        if (dto.senha() != null){
            usuario.setSenha(passwordEncoder.encode(dto.senha()));
        }
        if (dto.nome() != null){
            usuario.setNome(dto.nome());
        }
        this.usuarioRepository.save(usuario);
        UsuarioDTO usuarioDTO = new UsuarioDTO(usuario);
        return usuarioDTO;
    }


    @Transactional
    public void deletarUsuario(Long id) {
        buscarUsuario(id);
        try {
            this.usuarioRepository.deleteById(id);
        }catch (Exception e){
            throw new UsuarioDelete("Esse usuario nao pode ser deletado");
        }
    }
}

