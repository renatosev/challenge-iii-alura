package com.example.forumhub.domain.repositorys;

import com.example.forumhub.domain.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUsuarioRepository  extends JpaRepository<Usuario,Long> {
    Usuario findByEmail(String email);
}
