package com.example.forumhub.domain.models;

import com.example.forumhub.domain.dto.topico.TopicoCadastroDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "topicos")
@EqualsAndHashCode(of = "id")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Topico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    @NotBlank
    @Column(unique = true)
    private String titulo;

    @NotBlank
    @Column(unique = true)
    private String mensagem;

    private LocalDateTime dataCriacao;

    private String status;

    private LocalDateTime dataAtualizacao;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "usuario_id")
    private Usuario autor;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "curso_id")
    private Curso curso;

    @OneToMany(mappedBy = "topico",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Resposta> respostas = new ArrayList<>();

    public Topico(TopicoCadastroDTO dto) {
        this.titulo = dto.titulo();
        this.mensagem = dto.mensagem();
        this.dataCriacao = dto.data();
        this.status = dto.status();
    }
}
