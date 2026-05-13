    package br.com.elementosearte.elementosearte_api.produto_fornecedor.dto;

    import lombok.AllArgsConstructor;
    import lombok.Getter;
    import lombok.NoArgsConstructor;
    import lombok.Setter;

    import java.math.BigDecimal;

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    public class ProdutoFornecedorResponseDTO {

        private Long idProdutoFornecedor;

        private Long idProduto;

        private Long idFornecedor;

        private boolean fornecedorPrincipal;

        private boolean ativo;

        private BigDecimal custoFornecedor;
    }
