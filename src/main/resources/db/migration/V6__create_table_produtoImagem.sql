CREATE TABLE tb_produto_imagem(

    id_produto_imagem BIGSERIAL PRIMARY KEY,

    id_produto BIGINT NOT NULL,

    url_imagem VARCHAR(255) NOT NULL,

    nome_arquivo VARCHAR(255) NOT NULL,

    principal BOOLEAN NOT NULL DEFAULT  FALSE,

    ativo BOOLEAN NOT NULL DEFAULT TRUE,

    criado_em TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    atualizado_em TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_produto_imagem_produto
        FOREIGN KEY (id_produto) REFERENCES
            tb_produtos(id_produto)

);