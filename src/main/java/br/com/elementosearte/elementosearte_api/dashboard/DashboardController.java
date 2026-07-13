package br.com.elementosearte.elementosearte_api.dashboard;

import br.com.elementosearte.elementosearte_api.dashboard.dto.FaturamentoPorMesResponseDto;
import br.com.elementosearte.elementosearte_api.dashboard.dto.ProdutoEstoqueBaixoResponseDto;
import br.com.elementosearte.elementosearte_api.dashboard.dto.ResumoDashboardResponseDto;
import br.com.elementosearte.elementosearte_api.dashboard.dto.UltimaVendaResponseDto;
import br.com.elementosearte.elementosearte_api.fornecedor.dto.FornecedorResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/dashboard")
public class DashboardController {
    private final DashboardService dashboardService;

    public DashboardController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    @GetMapping("/resumo")
    public ResponseEntity<ResumoDashboardResponseDto> buscarResumoDashboard() {
        return ResponseEntity.ok(dashboardService.buscarResumoDashboard());
    }

    @GetMapping("/faturamento-mes")
    public ResponseEntity<BigDecimal> calcularFaturamentoDoMes() {
        return ResponseEntity.ok(dashboardService.calcularFaturamentoDoMes());
    }

    @GetMapping("/faturamento-ano")
    public ResponseEntity<BigDecimal> calcularFaturamentoDoAno() {
        return ResponseEntity.ok(dashboardService.calcularFaturamentoDoAno());
    }

    @GetMapping("/faturamento-mensal")
    public ResponseEntity<List<FaturamentoPorMesResponseDto>> listarFaturamentoPorMes(
            @RequestParam(required = false) Integer ano) {

        if (ano == null) {
            ano = LocalDate.now().getYear();
        }

        return ResponseEntity.ok(dashboardService.listarFaturamentoPorMes(ano));
    }

    @GetMapping("/ultimas-vendas")
    public ResponseEntity<List<UltimaVendaResponseDto>> listarUltimasVendas() {
        return ResponseEntity.ok(dashboardService.listarUltimasVendas());
    }

    @GetMapping("/fornecedores-ativos")
    public ResponseEntity<List<FornecedorResponseDTO>> listarFornecedoresAtivos() {
        return ResponseEntity.ok(dashboardService.listarFornecedoresAtivos());
    }

    @GetMapping("/estoque-baixo")
    public ResponseEntity<List<ProdutoEstoqueBaixoResponseDto>> listarProdutosComEstoqueBaixo() {
        return ResponseEntity.ok(dashboardService.listarProdutosComEstoqueBaixo());
    }
}
