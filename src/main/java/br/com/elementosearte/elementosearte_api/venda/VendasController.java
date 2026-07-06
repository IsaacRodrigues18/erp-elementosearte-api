package br.com.elementosearte.elementosearte_api.venda;

import br.com.elementosearte.elementosearte_api.venda.dto.VendasRequestDto;
import br.com.elementosearte.elementosearte_api.venda.dto.VendasResponseDto;
import jakarta.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/vendas")
public class VendasController {

    private final VendasService vendasService;

    public VendasController(VendasService vendasService) {
        this.vendasService = vendasService;
    }

    @PostMapping("/usuario/{idUsuario}")
    public ResponseEntity<VendasResponseDto> registrarVenda(
            @PathVariable Long idUsuario,
            @RequestBody @Valid VendasRequestDto requestDto) {

        VendasResponseDto responseDto = vendasService.registrarVenda(idUsuario, requestDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @GetMapping("/{idVenda}")
    public ResponseEntity<VendasResponseDto> buscarVendaPorId(@PathVariable Long idVenda) {
        return ResponseEntity.ok(vendasService.buscarVendaPorId(idVenda));
    }

    @GetMapping
    public ResponseEntity<List<VendasResponseDto>> listarTodasVendas() {
        return ResponseEntity.ok(vendasService.listarTodasVendas());
    }

    @GetMapping("/periodo")
    public ResponseEntity<List<VendasResponseDto>> listarVendasPorPeriodo(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFim) {

        return ResponseEntity.ok(vendasService.listarVendasPorPeriodo(dataInicio, dataFim));
    }

    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<List<VendasResponseDto>> listarVendasPorUsuario(@PathVariable Long idUsuario) {
        return ResponseEntity.ok(vendasService.listarVendasPorUsuario(idUsuario));
    }

    @PatchMapping("/cancelar/{idVenda}")
    public ResponseEntity<Void> cancelarVenda(@PathVariable Long idVenda) {
        vendasService.cancelarVenda(idVenda);
        return ResponseEntity.noContent().build();
    }
}