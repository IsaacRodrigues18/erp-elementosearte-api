package br.com.elementosearte.elementosearte_api.produto_imagem;

import br.com.elementosearte.elementosearte_api.exceptions.BusinessException;
import br.com.elementosearte.elementosearte_api.exceptions.ResourceNotFoundException;
import br.com.elementosearte.elementosearte_api.produto_imagem.dto.ProdutoImagemRequestDTO;
import br.com.elementosearte.elementosearte_api.produto_imagem.dto.ProdutoImagemResponseDTO;
import br.com.elementosearte.elementosearte_api.produto_imagem.dto.mapper.ProdutoImagemMapper;
import br.com.elementosearte.elementosearte_api.produtos.ProdutoEntity;
import br.com.elementosearte.elementosearte_api.produtos.ProdutoRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Set;

@Service
public class ProdutoImagemService {

    private static final long TAMANHO_MAXIMO_ARQUIVO =
            5 * 1024 * 1024;

    private static final Set<String> TIPOS_PERMITIDOS = Set.of(
            "image/jpeg",
            "image/png",
            "image/webp"
    );

    private final ProdutoImagemRepository produtoImagemRepository;
    private final ProdutoRepository produtoRepository;
    private final ProdutoImagemMapper produtoImagemMapper;

    public ProdutoImagemService(
            ProdutoImagemRepository produtoImagemRepository,
            ProdutoRepository produtoRepository,
            ProdutoImagemMapper produtoImagemMapper
    ) {
        this.produtoImagemRepository = produtoImagemRepository;
        this.produtoRepository = produtoRepository;
        this.produtoImagemMapper = produtoImagemMapper;
    }

    @Transactional
    public ProdutoImagemResponseDTO adicionarImagemAoProduto(
            ProdutoImagemRequestDTO dto,
            MultipartFile arquivo
    ) {
        validarArquivo(arquivo);

        ProdutoEntity produto = produtoRepository
                .findById(dto.getIdProduto())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Produto não encontrado")
                );

        String nomeArquivo = obterNomeArquivo(arquivo);

        boolean imagemJaCadastrada =
                produtoImagemRepository
                        .existsByProduto_IdProdutoAndNomeArquivoAndAtivoTrue(
                                produto.getIdProduto(),
                                nomeArquivo
                        );

        if (imagemJaCadastrada) {
            throw new BusinessException(
                    "Esta imagem já está cadastrada para este produto"
            );
        }

        if (dto.isPrincipal()) {
            removerImagemPrincipalAtual(
                    produto.getIdProduto()
            );
        }

        definirOrdemPadrao(
                dto,
                produto.getIdProduto()
        );

        try {
            ProdutoImagemEntity produtoImagem =
                    produtoImagemMapper.toEntity(
                            dto,
                            produto,
                            arquivo.getBytes(),
                            nomeArquivo,
                            arquivo.getContentType(),
                            arquivo.getSize()
                    );

            ProdutoImagemEntity imagemSalva =
                    produtoImagemRepository.save(produtoImagem);

            return produtoImagemMapper.toResponseDTO(
                    imagemSalva
            );

        } catch (IOException erro) {
            throw new BusinessException(
                    "Não foi possível processar o arquivo da imagem"
            );
        }
    }

    public List<ProdutoImagemResponseDTO>listarImagensDoProduto(Long idProduto) {
        validarExistenciaProduto(idProduto);

        return produtoImagemRepository
                .findByProduto_IdProduto(idProduto)
                .stream()
                .map(produtoImagemMapper::toResponseDTO)
                .toList();
    }

    public List<ProdutoImagemResponseDTO>listarImagensAtivas(Long idProduto) {
        validarExistenciaProduto(idProduto);

        return produtoImagemRepository
                .findByProduto_IdProdutoAndAtivoTrue(idProduto)
                .stream()
                .map(produtoImagemMapper::toResponseDTO)
                .toList();
    }

    @Transactional
    public ProdutoImagemResponseDTO definirImagemPrincipal(Long idProdutoImagem) {
        ProdutoImagemEntity imagemPrincipal = buscarImagemPorId(idProdutoImagem);

        if (!imagemPrincipal.isAtivo()) {
            throw new BusinessException("Não é possível definir uma imagem inativa como principal");
        }

        Long idProduto = imagemPrincipal.getProduto().getIdProduto();

        List<ProdutoImagemEntity> imagensDoProduto = produtoImagemRepository
                .findByProduto_IdProduto(idProduto);

        imagensDoProduto.forEach(imagem -> imagem.setPrincipal(false));

        imagemPrincipal.setPrincipal(true);

        produtoImagemRepository.saveAll(imagensDoProduto);

        return produtoImagemMapper.toResponseDTO(imagemPrincipal);
    }

    @Transactional
    public List<ProdutoImagemResponseDTO> ordenarImagemManualmente(Long idProdutoImagem, Integer novaOrdem) {
        if (novaOrdem == null || novaOrdem < 1) {
            throw new BusinessException("A nova ordem deve ser maior ou igual a 1");
        }

        ProdutoImagemEntity imagemMovida = buscarImagemPorId(idProdutoImagem);

        if (!imagemMovida.isAtivo()) {
            throw new BusinessException("Não é possível ordenar uma imagem inativa");
        }

        Long idProduto = imagemMovida.getProduto().getIdProduto();

        List<ProdutoImagemEntity> imagens = produtoImagemRepository
                .findByProduto_IdProdutoAndAtivoTrueOrderByOrdemExibicaoAsc(idProduto);

        if (novaOrdem > imagens.size()) {
            throw new BusinessException("A nova ordem é maior que a quantidade de imagens");
        }

        imagens.removeIf(imagem -> imagem.getIdProdutoImagem().equals(idProdutoImagem));

        imagens.add(novaOrdem - 1, imagemMovida);

        for (int indice = 0; indice < imagens.size(); indice++) {
            imagens.get(indice).setOrdemExibicao(indice + 1);
        }

        List<ProdutoImagemEntity> imagensSalvas = produtoImagemRepository.saveAll(imagens);

        return imagensSalvas
                .stream()
                .map(produtoImagemMapper::toResponseDTO)
                .toList();
    }

    @Transactional
    public void deletarImagem(Long idProdutoImagem) {
        ProdutoImagemEntity imagem = buscarImagemPorId(idProdutoImagem);

        if (!imagem.isAtivo()) {
            throw new BusinessException("A imagem já está inativa");
        }

        boolean eraPrincipal = imagem.isPrincipal();

        Long idProduto = imagem.getProduto().getIdProduto();

        imagem.setAtivo(false);
        imagem.setPrincipal(false);

        produtoImagemRepository.save(imagem);

        reorganizarOrdemDasImagens(idProduto);

        if (eraPrincipal) {
            definirNovaImagemPrincipal(idProduto);
        }

    }

    public ProdutoImagemEntity buscarImagemPorId(
            Long idProdutoImagem
    ) {
        return produtoImagemRepository
                .findById(idProdutoImagem)
                .orElseThrow(() -> new ResourceNotFoundException("Imagem não encontrada"));
    }

    public byte[] buscarDadosImagem(
            Long idProdutoImagem
    ) {
        ProdutoImagemEntity imagem = buscarImagemPorId(idProdutoImagem);

        if (!imagem.isAtivo()) {
            throw new ResourceNotFoundException("Imagem não encontrada");
        }

        return imagem.getDadosImagem();
    }

    private void validarArquivo(
            MultipartFile arquivo
    ) {
        if (arquivo == null || arquivo.isEmpty()) {
            throw new BusinessException("O arquivo da imagem é obrigatório");
        }

        if (arquivo.getSize() > TAMANHO_MAXIMO_ARQUIVO) {
            throw new BusinessException("A imagem deve possuir no máximo 5 MB");
        }

        String tipoArquivo =
                arquivo.getContentType();

        if (tipoArquivo == null || !TIPOS_PERMITIDOS.contains(tipoArquivo)) {
            throw new BusinessException("Formato de imagem inválido. " + "Utilize JPEG, PNG ou WEBP");
        }
    }

    private String obterNomeArquivo(MultipartFile arquivo) {
        String nomeOriginal = arquivo.getOriginalFilename();

        if (nomeOriginal == null || nomeOriginal.isBlank()) {
            return "imagem";
        }
        return nomeOriginal;
    }

    private void validarExistenciaProduto(Long idProduto) {
        if (!produtoRepository.existsById(idProduto)) {
            throw new ResourceNotFoundException("Produto não encontrado");
        }
    }

    private void definirOrdemPadrao(ProdutoImagemRequestDTO dto, Long idProduto) {
        if (dto.getOrdemExibicao() != null && dto.getOrdemExibicao() >= 1) {
            return;
        }

        int quantidadeImagensAtivas = produtoImagemRepository
                .findByProduto_IdProdutoAndAtivoTrue(idProduto)
                .size();

        dto.setOrdemExibicao(quantidadeImagensAtivas + 1);
    }

    private void removerImagemPrincipalAtual(Long idProduto) {
        List<ProdutoImagemEntity> imagens = produtoImagemRepository
                        .findByProduto_IdProduto(idProduto);

        imagens.forEach(imagem -> imagem.setPrincipal(false));

        produtoImagemRepository.saveAll(imagens);
    }

    private void definirNovaImagemPrincipal(Long idProduto) {
        List<ProdutoImagemEntity> imagensAtivas = produtoImagemRepository
                        .findByProduto_IdProdutoAndAtivoTrueOrderByOrdemExibicaoAsc(idProduto);

        if (imagensAtivas.isEmpty()) {
            return;
        }

        ProdutoImagemEntity novaPrincipal = imagensAtivas.get(0);

        novaPrincipal.setPrincipal(true);

        produtoImagemRepository.save(novaPrincipal);
    }

    private void reorganizarOrdemDasImagens(Long idProduto) {
        List<ProdutoImagemEntity> imagensAtivas =
                produtoImagemRepository.findByProduto_IdProdutoAndAtivoTrueOrderByOrdemExibicaoAsc(idProduto);

        for (int indice = 0; indice < imagensAtivas.size(); indice++) {
            imagensAtivas.get(indice)
                    .setOrdemExibicao(indice + 1);
        }
        produtoImagemRepository.saveAll(imagensAtivas);
    }
}