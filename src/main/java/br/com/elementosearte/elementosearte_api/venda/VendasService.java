package br.com.elementosearte.elementosearte_api.venda;

import br.com.elementosearte.elementosearte_api.exceptions.BusinessException;
import br.com.elementosearte.elementosearte_api.exceptions.ResourceNotFoundException;
import br.com.elementosearte.elementosearte_api.produtos.ProdutoEntity;
import br.com.elementosearte.elementosearte_api.produtos.ProdutoRepository;
import br.com.elementosearte.elementosearte_api.usuarios.UsuarioEntity;
import br.com.elementosearte.elementosearte_api.usuarios.UsuarioRepository;
import br.com.elementosearte.elementosearte_api.venda.dto.VendasItemRequestDto;
import br.com.elementosearte.elementosearte_api.venda.dto.VendasRequestDto;
import br.com.elementosearte.elementosearte_api.venda.dto.VendasResponseDto;
import br.com.elementosearte.elementosearte_api.venda.dto.mapper.VendasItensMapperDto;
import br.com.elementosearte.elementosearte_api.venda.dto.mapper.VendasMapperDto;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class VendasService {

    private final VendasRepository vendasRepository;
    private final VendasItensRepository vendasItensRepository;
    private final VendasMapperDto vendasMapperDto;
    private final VendasItensMapperDto vendasItensMapperDto;
    private final UsuarioRepository usuarioRepository;
    private final ProdutoRepository produtoRepository;

    public VendasService(VendasRepository vendasRepository,
                         VendasItensRepository vendasItensRepository,
                         VendasMapperDto vendasMapperDto,
                         VendasItensMapperDto vendasItensMapperDto,
                         UsuarioRepository usuarioRepository,
                         ProdutoRepository produtoRepository) {
        this.vendasRepository = vendasRepository;
        this.vendasItensRepository = vendasItensRepository;
        this.vendasMapperDto = vendasMapperDto;
        this.vendasItensMapperDto = vendasItensMapperDto;
        this.usuarioRepository = usuarioRepository;
        this.produtoRepository = produtoRepository;
    }

    @Transactional
    public VendasResponseDto registrarVenda(Long idUsuario, VendasRequestDto requestDto) {

        UsuarioEntity usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado"));

        VendasEntity venda = vendasMapperDto.toEntity(requestDto);
        venda.setUsuario(usuario);
        venda.setStatusVenda(StatusVenda.ATIVA);
        venda.setValorTotal(BigDecimal.ZERO);

        VendasEntity vendaSalva = vendasRepository.save(venda);

        BigDecimal valorTotal = BigDecimal.ZERO;

        for (VendasItemRequestDto itemDto : requestDto.getItens()) {

            ProdutoEntity produto = produtoRepository.findById(itemDto.getIdProduto())
                    .orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado"));

            BigDecimal subtotal = calcularSubtotal(itemDto);

            itemDto.setSubTotal(subtotal);

            VendasItensEntity item = vendasItensMapperDto.toEntity(itemDto, produto, vendaSalva);

            vendasItensRepository.save(item);

            valorTotal = valorTotal.add(subtotal);
        }

        vendaSalva.setValorTotal(valorTotal);

        return vendasMapperDto.toResponseDto(vendaSalva);
    }

    public VendasResponseDto buscarVendaPorId(Long idVenda) {
        VendasEntity venda = vendasRepository.findById(idVenda)
                .orElseThrow(() -> new ResourceNotFoundException("Venda não encontrada"));

        return vendasMapperDto.toResponseDto(venda);
    }

    public List<VendasResponseDto> listarTodasVendas() {
        return vendasRepository.findAll()
                .stream()
                .map(vendasMapperDto::toResponseDto)
                .toList();
    }

    public List<VendasResponseDto> listarVendasPorPeriodo(LocalDate dataInicio, LocalDate dataFim) {

        LocalDateTime inicio = dataInicio.atStartOfDay();
        LocalDateTime fim = dataFim.atTime(23, 59, 59);

        return vendasRepository.findByCriadoEmBetween(inicio, fim)
                .stream()
                .map(vendasMapperDto::toResponseDto)
                .toList();
    }

    public List<VendasResponseDto> listarVendasPorUsuario(Long idUsuario) {

        if (!usuarioRepository.existsById(idUsuario)) {
            throw new ResourceNotFoundException("Usuário não encontrado");
        }

        return vendasRepository.findByUsuarioIdUsuario(idUsuario)
                .stream()
                .map(vendasMapperDto::toResponseDto)
                .toList();
    }

    @Transactional
    public void cancelarVenda(Long idVenda) {

        VendasEntity venda = vendasRepository.findById(idVenda)
                .orElseThrow(() -> new ResourceNotFoundException("Venda não encontrada"));

        if (venda.getStatusVenda() == StatusVenda.CANCELADA) {
            throw new BusinessException("Venda já está cancelada");
        }

        venda.setStatusVenda(StatusVenda.CANCELADA);
    }

    private BigDecimal calcularSubtotal(VendasItemRequestDto itemDto) {

        BigDecimal subtotal = itemDto.getValorUnitario()
                .multiply(BigDecimal.valueOf(itemDto.getQuantidade()));

        if (itemDto.getValorDesconto() == null || itemDto.getTipoDesconto() == null) {
            return subtotal;
        }

        if (itemDto.getTipoDesconto() == TipoDesconto.VALOR) {
            return subtotal.subtract(itemDto.getValorDesconto());
        }

        if (itemDto.getTipoDesconto() == TipoDesconto.PERCENTUAL) {
            BigDecimal desconto = subtotal
                    .multiply(itemDto.getValorDesconto())
                    .divide(BigDecimal.valueOf(100));

            return subtotal.subtract(desconto);
        }

        return subtotal;
    }
}