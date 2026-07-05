CREATE TABLE tb_vendas_item
(
    id_venda_item  BIGSERIAL PRIMARY KEY,
    id_venda       BIGINT         NOT NULL,
    id_produto     BIGINT         NOT NULL,
    quantidade     INT            NOT NULL,
    valor_unitario DECIMAL(10, 2) NOT NULL,
    tipo_desconto  VARCHAR(30),
    valor_desconto DECIMAL(10, 2),
    sub_tot al      DECIMAL(10, 2) NOT NULL,

    CONSTRAINT fk_venda_item_venda
        FOREIGN KEY (id_venda)
            REFERENCES tb_vendas(id_venda),

    CONSTRAINT fk_venda_item_produto
        FOREIGN KEY (id_produto)
            REFERENCES tb_produtos(id_produto)
);