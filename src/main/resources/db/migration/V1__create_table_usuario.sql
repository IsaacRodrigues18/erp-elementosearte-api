create table tb_usuarios
(
    id_usuario    SERIAL PRIMARY KEY,
    nome          varchar(100) not null,
    email         varchar(255) not null unique,
    senha         varchar(255) not null,
    ativo         boolean               default true,
    criado_em     timestamp    not null default current_timestamp,
    atualizado_em timestamp
);