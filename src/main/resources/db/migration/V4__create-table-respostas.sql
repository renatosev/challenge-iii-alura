create table respostas(
    id bigint auto_increment primary key,
    mensagem text not null,
    topico_id bigint not null,
    data_criacao datetime not null,
    usuario_id bigint not null,
    solucao varchar(255),
    foreign key (topico_id) references topicos(id),
    foreign key (usuario_id) references usuarios(id)
);