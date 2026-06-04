package br.com.elementosearte.elementosearte_api.produto_imagem;

import br.com.elementosearte.elementosearte_api.produto_imagem.dto.ProdutoImagemRequestDTO;
import br.com.elementosearte.elementosearte_api.produto_imagem.dto.ProdutoImagemResponseDTO;
import br.com.elementosearte.elementosearte_api.produtos.ProdutoEntity;
import br.com.elementosearte.elementosearte_api.produtos.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.ArrayList;

@Service
public class ProdutoImagemService {

    @Autowired
    private ProdutoImagemRepository produtoImagemRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    private ProdutoImagemResponseDTO toDTO(ProdutoImagemEntity produtoImagem) {
        return new ProdutoImagemResponseDTO(
                produtoImagem.getIdProdutoImagem(),
                produtoImagem.getProduto().getIdProduto(),
                produtoImagem.getNomeArquivo(),
                produtoImagem.getUrlImagem(),
                produtoImagem.isPrincipal(),
                produtoImagem.isAtivo(),
                produtoImagem.getOrdemExibicao()
        );
    }


    public ProdutoImagemResponseDTO adicionarImagemAoProduto(ProdutoImagemRequestDTO dto) {

        ProdutoEntity produto = produtoRepository.findById(dto.getIdProduto())
                .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado"));

        ProdutoImagemEntity produtoImagem = new ProdutoImagemEntity();
        produtoImagem.setProduto(produto);
        produtoImagem.setUrlImagem(dto.getUrlImagem());
        produtoImagem.setNomeArquivo(dto.getNomeArquivo());
        produtoImagem.setPrincipal(dto.isPrincipal());
        produtoImagem.setOrdemExibicao(
                dto.getOrdemExibicao() != null ? dto.getOrdemExibicao() : 0
        );

        ProdutoImagemEntity salvo = produtoImagemRepository.save(produtoImagem);

        return toDTO(salvo);
    }


    public List<ProdutoImagemResponseDTO> listarImagensDoProduto(Long idProduto) {
        if (!produtoRepository.existsById(idProduto)) {
            throw new IllegalArgumentException("Produto não encontrado");
        }

        return produtoImagemRepository.findByProdutoIdProduto(idProduto)
                .stream()
                .map(this::toDTO)
                .toList();
    }

    public List<ProdutoImagemResponseDTO> listarImagensAtivas(Long idProduto) {

        if (!produtoRepository.existsById(idProduto)) {
            throw new IllegalArgumentException("Produto não encontrado");
        }

        return produtoImagemRepository
                .findByProdutoIdProdutoAndAtivoTrue(idProduto)
                .stream()
                .map(this::toDTO)
                .toList();
    }

    public ProdutoImagemResponseDTO definirImagemPrincipal(Long idImagem) {

        ProdutoImagemEntity imagemPrincipal = buscarImagem(idImagem);

        Long idProduto = imagemPrincipal.getProduto().getIdProduto();

        List<ProdutoImagemEntity> imagensDoProduto =
                produtoImagemRepository.findByProdutoIdProduto(idProduto);

        for (ProdutoImagemEntity imagem : imagensDoProduto) {
            imagem.setPrincipal(false);
        }

        imagemPrincipal.setPrincipal(true);

        produtoImagemRepository.saveAll(imagensDoProduto);

        return toDTO(imagemPrincipal);
    }

    private ProdutoImagemEntity buscarImagem(Long idImagem) {
        return produtoImagemRepository.findById(idImagem)
                .orElseThrow(() -> new IllegalArgumentException("Imagem não encontrada"));
    }

    public List<ProdutoImagemResponseDTO> ordenarImagemManualmente(Long idProdutoImagem, Integer novaOrdem) {

        if (novaOrdem == null || novaOrdem < 1) {
            throw new IllegalArgumentException("Ordem inválida");
        }

        ProdutoImagemEntity imagemMovida = buscarImagem(idProdutoImagem);

        Long idProduto = imagemMovida.getProduto().getIdProduto();

        List<ProdutoImagemEntity> imagens = produtoImagemRepository
                .findByProdutoIdProdutoAndAtivoTrueOrderByOrdemExibicaoAsc(idProduto);

        if (novaOrdem > imagens.size()) {
            throw new IllegalArgumentException("Ordem inválida");
        }

        imagens.removeIf(img ->
                img.getIdProdutoImagem().equals(idProdutoImagem)
        );

        imagens.add(novaOrdem - 1, imagemMovida);

        for (int i = 0; i < imagens.size(); i++) {
            imagens.get(i).setOrdemExibicao(i + 1);
        }

        produtoImagemRepository.saveAll(imagens);

        return imagens.stream()
                .map(this::toDTO)
                .toList();
    }

    public ProdutoImagemResponseDTO deletarImagem(Long idProdutoImagem) {
        ProdutoImagemEntity imagem = buscarImagem(idProdutoImagem);

        imagem.setAtivo(false);

        return toDTO(produtoImagemRepository.save(imagem));
    }


}




