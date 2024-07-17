package com.example.forumhub.domain.repositorys;

import com.example.forumhub.domain.models.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICursoRepository extends JpaRepository<Curso,Long> {
}
