CREATE TABLE tb_movimentacao_estoque
(
    id_movimentacao_estoque BIGSERIAL PRIMARY KEY,

    id_produto BIGINT NOT NULL,

    id_usuario BIGINT NOT NULL,

    quantidade INTEGER NOT NULL,

    valor_unitario DECIMAL(10,2) NOT NULL,

    tipo_movimentacao VARCHAR(30) NOT NULL,

    observacao VARCHAR(255),

    criado_em TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_movimentacao_produto
        FOREIGN KEY (id_produto)
            REFERENCES tb_produtos(id_produto),

    CONSTRAINT fk_movimentacao_usuario
        FOREIGN KEY (id_usuario)
            REFERENCES tb_usuarios(id_usuario)
);