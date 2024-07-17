package com.example.forumhub.domain.repositorys;

import com.example.forumhub.domain.models.Topico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITopicoRepository extends JpaRepository<Topico,Long> {
}
