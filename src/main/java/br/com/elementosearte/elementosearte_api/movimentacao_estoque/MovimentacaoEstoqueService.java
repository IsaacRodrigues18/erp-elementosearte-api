
/*
registrarMovimentacao()
buscarMovimentacaoPorId()
listarMovimentacoes()
listarMovimentacoesPorProduto()
listarMovimentacoesPorUsuario()

*/
package br.com.elementosearte.elementosearte_api.movimentacao_estoque;

import br.com.elementosearte.elementosearte_api.movimentacao_estoque.dto.MovimentacaoEstoqueMapper;
import br.com.elementosearte.elementosearte_api.movimentacao_estoque.dto.MovimentacaoEstoqueRequestDTO;
import br.com.elementosearte.elementosearte_api.movimentacao_estoque.dto.MovimentacaoEstoqueResponseDTO;
import br.com.elementosearte.elementosearte_api.produtos.ProdutoEntity;
import br.com.elementosearte.elementosearte_api.produtos.ProdutoRepository;
import br.com.elementosearte.elementosearte_api.usuarios.UsuarioEntity;
import br.com.elementosearte.elementosearte_api.usuarios.UsuarioRepository;
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

    public MovimentacaoEstoqueResponseDTO registrarMovimentacao(MovimentacaoEstoqueRequestDTO requestDTO) {
        ProdutoEntity produto = produtoRepository.findById(requestDTO.getIdProduto())
                .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado"));

        UsuarioEntity usuario = usuarioRepository.findById(requestDTO.getIdUsuario())
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));

        MovimentacaoEstoqueEntity entity = movimentacaoEstoqueMapper.toEntity(
                requestDTO,
                produto,
                usuario
        );

        MovimentacaoEstoqueEntity movimentacaoSalvar = movimentacaoEstoqueRepository.save(entity);

        return movimentacaoEstoqueMapper.toResponseDTO(movimentacaoSalvar);

    }

    public MovimentacaoEstoqueResponseDTO buscarMovimentacaoPorId(Long idMovimentacao) {
        MovimentacaoEstoqueEntity movimentacaoEstoqueEntity = movimentacaoEstoqueRepository.findById(idMovimentacao)
                .orElseThrow(() -> new IllegalArgumentException("Movimentação não encontrada"));

        return movimentacaoEstoqueMapper.toResponseDTO(movimentacaoEstoqueEntity);
    }


    public List<MovimentacaoEstoqueResponseDTO> listarMovimentacoes() {
        return movimentacaoEstoqueRepository.findAll()
                .stream()
                .map(this.movimentacaoEstoqueMapper::toResponseDTO)
                .toList();
    }

    public List<MovimentacaoEstoqueResponseDTO> listarMovimentacoesPorProduto(Long idProduto) {
        produtoRepository.findById(idProduto)
                .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado"));

        return movimentacaoEstoqueRepository
                .findByProdutoIdProduto(idProduto)
                .stream()
                .map(movimentacaoEstoqueMapper::toResponseDTO)
                .toList();
    }

    public List<MovimentacaoEstoqueResponseDTO> listarMovimenacoesPorUsuario(Long idUsuario) {
        usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));

        return movimentacaoEstoqueRepository
                .findByUsuarioIdUsuario(idUsuario)
                .stream()
                .map(movimentacaoEstoqueMapper::toResponseDTO)
                .toList();

    }


}
