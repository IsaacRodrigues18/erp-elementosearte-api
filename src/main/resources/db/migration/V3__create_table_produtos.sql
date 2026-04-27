CREATE TABLE tb_produtos
(
    id_produto                BIGSERIAL PRIMARY KEY,
    nome_produto              VARCHAR(255)   NOT NULL,
    descricao_produto         VARCHAR(255),
    custo_referencia          NUMERIC(10, 2) NOT NULL,
    preco_venda_referencia    NUMERIC(10, 2) NOT NULL,
    ativo                     BOOLEAN        NOT NULL DEFAULT TRUE,
    criado_em                 TIMESTAMP      NOT NULL DEFAULT CURRENT_TIMESTAMP,
    atualizado_em             TIMESTAMP      NOT NULL DEFAULT CURRENT_TIMESTAMP,
    criado_por_usuario_id     BIGINT,
    atualizado_por_usuario_id BIGINT,
    id_categoria              BIGINT         NOT NULL,

    CONSTRAINT fk_produto_criado_por_usuario
        FOREIGN KEY (criado_por_usuario_id)
            REFERENCES tb_usuarios (id_usuario),

    CONSTRAINT fk_produto_atualizado_por_usuario
        FOREIGN KEY (atualizado_por_usuario_id)
            REFERENCES tb_usuarios (id_usuario),

    CONSTRAINT fk_produto_categoria
        FOREIGN KEY (id_categoria)
            REFERENCES tb_categorias (id_categoria)
);