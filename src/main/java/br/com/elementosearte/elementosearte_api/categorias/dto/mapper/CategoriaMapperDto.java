package br.com.elementosearte.elementosearte_api.categorias.dto.mapper;

import br.com.elementosearte.elementosearte_api.categorias.CategoriaEntity;
import br.com.elementosearte.elementosearte_api.categorias.dto.CategoriaRequestDTO;
import br.com.elementosearte.elementosearte_api.categorias.dto.CategoriaResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class CategoriaMapperDto {

    public CategoriaEntity toEntity(CategoriaRequestDTO categoriaRequestDTO) {
        CategoriaEntity categoriaEntity = new CategoriaEntity();

        categoriaEntity.setNome(categoriaRequestDTO.getNomeCategoria());
        categoriaEntity.setDescricao(categoriaRequestDTO.getDescricao());

        return categoriaEntity;
    }
    public CategoriaResponseDTO toResponseDto(CategoriaEntity categoriaEntity) {
        CategoriaResponseDTO categoriaResponseDTO = new CategoriaResponseDTO();

        categoriaResponseDTO.setIdCategoria(categoriaEntity.getIdCategoria());
        categoriaResponseDTO.setNomeCategoria(categoriaEntity.getNome());
        categoriaResponseDTO.setDescricao(categoriaEntity.getDescricao());
        categoriaResponseDTO.setAtivo(categoriaEntity.isAtivo());

        return categoriaResponseDTO;
    }
}
