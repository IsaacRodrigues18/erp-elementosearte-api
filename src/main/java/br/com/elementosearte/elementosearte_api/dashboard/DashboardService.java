package br.com.elementosearte.elementosearte_api.dashboard;

import org.springframework.stereotype.Service;
import br.com.elementosearte.elementosearte_api.dashboard.dto.FaturamentoPorMesResponseDto;
import br.com.elementosearte.elementosearte_api.dashboard.dto.ProdutoEstoqueBaixoResponseDto;
import br.com.elementosearte.elementosearte_api.dashboard.dto.ResumoDashboardResponseDto;
import br.com.elementosearte.elementosearte_api.dashboard.dto.UltimaVendaResponseDto;
import br.com.elementosearte.elementosearte_api.fornecedor.FornecedorEntity;
import br.com.elementosearte.elementosearte_api.fornecedor.FornecedorRepository;
import br.com.elementosearte.elementosearte_api.fornecedor.dto.FornecedorResponseDTO;
import br.com.elementosearte.elementosearte_api.fornecedor.dto.mapper.FornecedorMapperDto;
import br.com.elementosearte.elementosearte_api.produtos.ProdutoEntity;
import br.com.elementosearte.elementosearte_api.produtos.ProdutoRepository;
import br.com.elementosearte.elementosearte_api.venda.StatusVenda;
import br.com.elementosearte.elementosearte_api.venda.VendasEntity;
import br.com.elementosearte.elementosearte_api.venda.VendasRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

    @Service
    public class DashboardService {

        private static final Integer LIMITE_ESTOQUE_BAIXO = 5;

        private final VendasRepository vendasRepository;
        private final ProdutoRepository produtoRepository;
        private final FornecedorRepository fornecedorRepository;
        private final FornecedorMapperDto fornecedorMapperDto;

        public DashboardService(VendasRepository vendasRepository,
                                ProdutoRepository produtoRepository,
                                FornecedorRepository fornecedorRepository,
                                FornecedorMapperDto fornecedorMapperDto) {
            this.vendasRepository = vendasRepository;
            this.produtoRepository = produtoRepository;
            this.fornecedorRepository = fornecedorRepository;
            this.fornecedorMapperDto = fornecedorMapperDto;
        }

        public ResumoDashboardResponseDto buscarResumoDashboard() {
            BigDecimal faturamentoMes = calcularFaturamentoDoMes();
            BigDecimal faturamentoAno = calcularFaturamentoDoAno();
            Long quantidadeVendasMes = calcularQuantidadeVendasDoMes();
            Long produtosBaixoEstoque = contarProdutosComEstoqueBaixo();

            return new ResumoDashboardResponseDto(
                    faturamentoMes,
                    faturamentoAno,
                    quantidadeVendasMes,
                    produtosBaixoEstoque
            );
        }

        public BigDecimal calcularFaturamentoDoMes() {
            LocalDate hoje = LocalDate.now();

            LocalDateTime inicio = hoje.withDayOfMonth(1).atStartOfDay();
            LocalDateTime fim = hoje.atTime(23, 59, 59);

            return vendasRepository.findByCriadoEmBetween(inicio, fim)
                    .stream()
                    .filter(venda -> venda.getStatusVenda() == StatusVenda.ATIVA)
                    .map(VendasEntity::getValorTotal)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
        }

        public BigDecimal calcularFaturamentoDoAno() {
            LocalDate hoje = LocalDate.now();

            LocalDateTime inicio = LocalDate.of(hoje.getYear(), 1, 1).atStartOfDay();
            LocalDateTime fim = hoje.atTime(23, 59, 59);

            return vendasRepository.findByCriadoEmBetween(inicio, fim)
                    .stream()
                    .filter(venda -> venda.getStatusVenda() == StatusVenda.ATIVA)
                    .map(VendasEntity::getValorTotal)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
        }

        public List<FaturamentoPorMesResponseDto> listarFaturamentoPorMes(Integer ano) {
            return java.util.stream.IntStream.rangeClosed(1, 12)
                    .mapToObj(mes -> {
                        LocalDateTime inicio = LocalDate.of(ano, mes, 1).atStartOfDay();
                        LocalDateTime fim = LocalDate.of(ano, mes, 1)
                                .withDayOfMonth(LocalDate.of(ano, mes, 1).lengthOfMonth())
                                .atTime(23, 59, 59);

                        BigDecimal faturamento = vendasRepository.findByCriadoEmBetween(inicio, fim)
                                .stream()
                                .filter(venda -> venda.getStatusVenda() == StatusVenda.ATIVA)
                                .map(VendasEntity::getValorTotal)
                                .reduce(BigDecimal.ZERO, BigDecimal::add);

                        return new FaturamentoPorMesResponseDto(mes, faturamento);
                    })
                    .toList();
        }

            public List<UltimaVendaResponseDto> listarUltimasVendas() {
                return vendasRepository.findTop5ByOrderByCriadoEmDesc()
                        .stream()
                        .map(venda -> new UltimaVendaResponseDto(
                                        venda.getIdVenda(),
                                        venda.getUsuario().getNome(),
                                        venda.getValorTotal(),
                                        venda.getCriadoEm(),
                                        venda.getStatusVenda()
                                )
                        )
                        .toList();
            }

        public List<FornecedorResponseDTO> listarFornecedoresAtivos() {
            return fornecedorRepository.findByAtivoTrue()
                    .stream()
                    .map(fornecedorMapperDto::toResponseDto)
                    .toList();
        }

        public List<ProdutoEstoqueBaixoResponseDto> listarProdutosComEstoqueBaixo() {
            return produtoRepository.findByQuantidadeEstoqueLessThanEqual(LIMITE_ESTOQUE_BAIXO)
                    .stream()
                    .map(produto -> new ProdutoEstoqueBaixoResponseDto(
                            produto.getIdProduto(),
                            produto.getNomeProduto(),
                            produto.getQuantidadeEstoque()
                    ))
                    .toList();
        }

        private Long calcularQuantidadeVendasDoMes() {
            LocalDate hoje = LocalDate.now();

            LocalDateTime inicio = hoje.withDayOfMonth(1).atStartOfDay();
            LocalDateTime fim = hoje.atTime(23, 59, 59);

            return vendasRepository.findByCriadoEmBetween(inicio, fim)
                    .stream()
                    .filter(venda -> venda.getStatusVenda() == StatusVenda.ATIVA)
                    .count();
        }

        private Long contarProdutosComEstoqueBaixo() {
            return (long) produtoRepository
                    .findByQuantidadeEstoqueLessThanEqual(LIMITE_ESTOQUE_BAIXO)
                    .size();
        }
}
