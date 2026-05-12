CREATE TABLE tb_produto_fornecedor
(
    id_produto_fornecedor BIGSERIAL PRIMARY KEY,
    id_produto            BIGINT NOT NULL,
    id_fornecedor         BIGINT NOT NULL,
    fornecedor_principal  BOOLEAN NOT NULL DEFAULT FALSE,
    custo_fornecedor      NUMERIC(10, 2) NOT NULL,
    ativo                 BOOLEAN NOT NULL DEFAULT TRUE,
    criado_em             TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_produto_fornecedor_produto
        FOREIGN KEY (id_produto)
            REFERENCES tb_produtos (id_produto),

    CONSTRAINT fk_produto_fornecedor_fornecedor
        FOREIGN KEY (id_fornecedor)
            REFERENCES tb_fornecedores (id_fornecedor),

    CONSTRAINT uk_produto_fornecedor
        UNIQUE (id_produto, id_fornecedor)
);