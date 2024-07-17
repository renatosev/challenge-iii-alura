create table usuarios(
     id bigint auto_increment primary key,
     nome varchar(255) not null,
     email varchar(255) not null unique,
     senha varchar(255) not null
);