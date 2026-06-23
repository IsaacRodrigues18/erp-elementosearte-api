package br.com.elementosearte.elementosearte_api.categorias;

import br.com.elementosearte.elementosearte_api.categorias.dto.CategoriaRequestDTO;
import br.com.elementosearte.elementosearte_api.categorias.dto.CategoriaResponseDTO;
import br.com.elementosearte.elementosearte_api.categorias.dto.mapper.CategoriaMapperDto;
import br.com.elementosearte.elementosearte_api.exceptions.BusinessException;
import br.com.elementosearte.elementosearte_api.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;
    private final CategoriaMapperDto categoriaMapperDto;

    public CategoriaService(CategoriaRepository categoriaRepository, CategoriaMapperDto categoriaMapperDto) {
        this.categoriaRepository = categoriaRepository;
        this.categoriaMapperDto = categoriaMapperDto;
    }


    public CategoriaResponseDTO criarCategoria(CategoriaRequestDTO categoriaRequestDTO) {
        if (categoriaRepository.existsByNome(categoriaRequestDTO.getNome())) {
            throw new BusinessException("Categoria já cadastrada");
        }

        CategoriaEntity categoria = categoriaMapperDto.toEntity(categoriaRequestDTO);

        CategoriaEntity categoriaSalva = categoriaRepository.save(categoria);

        return categoriaMapperDto.toResponseDto(categoriaSalva);
    }

    public List<CategoriaResponseDTO> listarCategorias() {
        return categoriaRepository.findAll().stream()
                .map(categoriaMapperDto::toResponseDto)
                .toList();
    }

    public CategoriaResponseDTO buscarCategoriaPorId(Long idCategoria) {
        CategoriaEntity categoria = categoriaRepository.findById(idCategoria)
                .orElseThrow(() -> new ResourceNotFoundException("Categoria não encontrada"));

        return categoriaMapperDto.toResponseDto(categoria);
    }


    public CategoriaResponseDTO atualizarCategoria(Long idCategoria, CategoriaRequestDTO categoriaRequestDTO) {
        CategoriaEntity categoria = categoriaRepository.findByAtivoTrue(idCategoria)
                .orElseThrow(() -> new ResourceNotFoundException("Categoria não encontrada"));

        if (!categoria.getNome().equals(categoriaRequestDTO.getNome())
                && categoriaRepository.existsByNome(categoriaRequestDTO.getNome())) {
            throw new BusinessException("Categoria já cadastrada");
        }

        categoria.setNome(categoriaRequestDTO.getNome());
        categoria.setDescricao(categoriaRequestDTO.getDescricao());

        CategoriaEntity categoriaAtualizada = categoriaRepository.save(categoria);

        return categoriaMapperDto.toResponseDto(categoriaAtualizada);
    }

    public CategoriaResponseDTO inativarCategoria(Long idCategoria) {
        CategoriaEntity categoria = categoriaRepository.findById(idCategoria)
                .orElseThrow(() -> new ResourceNotFoundException("Categoria não encontrada"));

        if (!categoria.isAtivo()) {
            throw new BusinessException("Categoria já está inativa");
        }

        categoria.setAtivo(false);
        return categoriaMapperDto.toResponseDto(categoria);
    }


    public CategoriaResponseDTO ativarCategoria(Long idCategoria) {
        CategoriaEntity categoria = categoriaRepository.findById(idCategoria)
                .orElseThrow(() -> new ResourceNotFoundException("Categoria não encontrada"));

        if (categoria.isAtivo()) {
            throw new BusinessException("Categoria já está ativa");
        }

        categoria.setAtivo(true);
        return categoriaMapperDto.toResponseDto(categoriaRepository.save(categoria));
    }



    public void deletarCategoria(Long idCategoria) {
        if (!categoriaRepository.existsById(idCategoria)) {
            throw new IllegalArgumentException("Categoria não encontrada");
        }

        categoriaRepository.deleteById(idCategoria);
    }


}



