
package br.com.elementosearte.elementosearte_api.movimentacao_estoque;

import br.com.elementosearte.elementosearte_api.exceptions.BusinessException;
import br.com.elementosearte.elementosearte_api.exceptions.ResourceNotFoundException;
import br.com.elementosearte.elementosearte_api.movimentacao_estoque.dto.mapper.MovimentacaoEstoqueMapper;
import br.com.elementosearte.elementosearte_api.movimentacao_estoque.dto.MovimentacaoEstoqueRequestDTO;
import br.com.elementosearte.elementosearte_api.movimentacao_estoque.dto.MovimentacaoEstoqueResponseDTO;
import br.com.elementosearte.elementosearte_api.produtos.ProdutoEntity;
import br.com.elementosearte.elementosearte_api.produtos.ProdutoRepository;
import br.com.elementosearte.elementosearte_api.usuarios.UsuarioEntity;
import br.com.elementosearte.elementosearte_api.usuarios.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovimentacaoEstoqueService {

    private final MovimentacaoEstoqueRepository movimentacaoEstoqueRepository;

    private final MovimentacaoEstoqueMapper movimentacaoEstoqueMapper;

    private final ProdutoRepository produtoRepository;

    private final UsuarioRepository usuarioRepository;

    public MovimentacaoEstoqueService(MovimentacaoEstoqueRepository movimentacaoEstoqueRepository,
                                      MovimentacaoEstoqueMapper movimentacaoEstoqueMapper,
                                      ProdutoRepository produtoRepository,
                                      UsuarioRepository usuarioRepository) {

        this.movimentacaoEstoqueRepository = movimentacaoEstoqueRepository;
        this.movimentacaoEstoqueMapper = movimentacaoEstoqueMapper;
        this.produtoRepository = produtoRepository;
        this.usuarioRepository = usuarioRepository;

    }

    private void atualizarEstoqueProduto(ProdutoEntity produto, MovimentacaoEstoqueRequestDTO requestDTO) {

        Integer estoqueAtual = produto.getQuantidadeEstoque();
        Integer quantidade = requestDTO.getQuantidade();

        switch (requestDTO.getTipoMovimentacao()) {

            case ENTRADA ->
                    produto.setQuantidadeEstoque(estoqueAtual + quantidade);

            case SAIDA -> {
                if (estoqueAtual < quantidade) {
                    throw new BusinessException("Estoque insuficiente para realizar a saída.");
                }

                produto.setQuantidadeEstoque(estoqueAtual - quantidade);
            }

            case AJUSTE_POSITIVO ->
                    produto.setQuantidadeEstoque(estoqueAtual + quantidade);

            case AJUSTE_NEGATIVO -> {

                if (estoqueAtual < quantidade) {
                    throw new BusinessException("O ajuste negativo deixaria o estoque abaixo de zero.");
                }

                produto.setQuantidadeEstoque(estoqueAtual - quantidade);
            }
        }
    }

    @Transactional
    public MovimentacaoEstoqueResponseDTO registraMovimentacao(MovimentacaoEstoqueRequestDTO requestDTO) {
        ProdutoEntity produto = produtoRepository.findById(requestDTO.getIdProduto())
                .orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado"));

        UsuarioEntity usuario = usuarioRepository.findById(requestDTO.getIdUsuario())
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado"));

        atualizarEstoqueProduto(produto, requestDTO);

        MovimentacaoEstoqueEntity entity = movimentacaoEstoqueMapper.toEntity(
                requestDTO,
                produto,
                usuario
        );

        MovimentacaoEstoqueEntity movimentacaoSalvar = movimentacaoEstoqueRepository.save(entity);

        return movimentacaoEstoqueMapper.toResponseDTO(movimentacaoSalvar);
    }

    public List<MovimentacaoEstoqueResponseDTO> listarMovimentacoes() {
        return movimentacaoEstoqueRepository.findAll()
                .stream()
                .map(this.movimentacaoEstoqueMapper::toResponseDTO)
                .toList();
    }

    public MovimentacaoEstoqueResponseDTO buscarMovimentacaoPorId(Long idMovimentacao) {
        MovimentacaoEstoqueEntity movimentacaoEstoqueEntity = movimentacaoEstoqueRepository.findById(idMovimentacao)
                .orElseThrow(() -> new ResourceNotFoundException("Movimentação não encontrada"));

        return movimentacaoEstoqueMapper.toResponseDTO(movimentacaoEstoqueEntity);
    }

    public List<MovimentacaoEstoqueResponseDTO> listarPorTipoMovimentacao(TipoMovimentacao tipoMovimentacao) {
        return movimentacaoEstoqueRepository
                .findByTipoMovimentacao(tipoMovimentacao)
                .stream()
                .map(movimentacaoEstoqueMapper::toResponseDTO)
                .toList();
    }


    public List<MovimentacaoEstoqueResponseDTO> listarMovimentacoesPorProduto(Long idProduto) {
        produtoRepository.findById(idProduto)
                .orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado"));

        return movimentacaoEstoqueRepository
                .findByProdutoIdProduto(idProduto)
                .stream()
                .map(movimentacaoEstoqueMapper::toResponseDTO)
                .toList();
    }

    public List<MovimentacaoEstoqueResponseDTO> listarMovimenacoesPorUsuario(Long idUsuario) {
        usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado"));

        return movimentacaoEstoqueRepository
                .findByUsuarioIdUsuario(idUsuario)
                .stream()
                .map(movimentacaoEstoqueMapper::toResponseDTO)
                .toList();

    }
}
