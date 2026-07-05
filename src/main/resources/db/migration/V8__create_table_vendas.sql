CREATE TABLE tb_vendas
(
    id_venda        BIGSERIAL PRIMARY KEY,
    id_usuario      BIGINT         NOT NULL,
    forma_pagamento VARCHAR(50)    NOT NULL,
    valor_total     DECIMAL(10, 2) NOT NULL,
    criado_em       TIMESTAMP      NOT NULL DEFAULT CURRENT_TIMESTAMP

        CONSTRAINT fk_venda_usuario
        FOREIGN KEY (id_usuario)
        REFERENCES tb_usuarios(id_usuario)


);