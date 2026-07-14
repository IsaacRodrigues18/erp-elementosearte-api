ALTER TABLE tb_produto_imagem
DROP COLUMN url_imagem;

ALTER TABLE tb_produto_imagem
    ADD COLUMN dados_imagem BYTEA NOT NULL;

ALTER TABLE tb_produto_imagem
    ADD COLUMN tipo_arquivo VARCHAR(100) NOT NULL;

ALTER TABLE tb_produto_imagem
    ADD COLUMN tamanho_arquivo BIGINT NOT NULL;