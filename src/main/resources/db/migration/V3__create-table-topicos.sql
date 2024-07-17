create table topicos(
    id bigint auto_increment primary key,
    titulo varchar(255) not null,
    mensagem text not null,
    data_criacao datetime not null,
    status varchar(255),
    usuario_id bigint not null,
    curso_id bigint not null,
    foreign key (usuario_id) references usuarios(id),
    foreign key (curso_id) references cursos(id)
);