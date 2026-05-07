CREATE TABLE tb_fornecedores (
                                 id_fornecedor BIGSERIAL PRIMARY KEY,
                                 nome_fornecedor VARCHAR(100) NOT NULL,
                                 cidade VARCHAR(100) NOT NULL,
                                 telefone VARCHAR(20),
                                 email VARCHAR(255) UNIQUE,
                                 observacao VARCHAR(255),
                                 ativo BOOLEAN NOT NULL DEFAULT TRUE,
                                 criado_em TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                 atualizado_em TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);