package br.com.elementosearte.elementosearte_api.categorias.dto.mapper;

import br.com.elementosearte.elementosearte_api.categorias.CategoriaEntity;
import br.com.elementosearte.elementosearte_api.categorias.dto.CategoriaRequestDTO;
import br.com.elementosearte.elementosearte_api.categorias.dto.CategoriaResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class CategoriaMapperDto {

    public CategoriaEntity toEntity(CategoriaRequestDTO categoriaRequestDTO) {
        CategoriaEntity categoriaEntity = new CategoriaEntity();

        categoriaEntity.setNome(categoriaRequestDTO.getNome());
        categoriaEntity.setDescricao(categoriaRequestDTO.getDescricao());

        return categoriaEntity;
    }
    public CategoriaResponseDTO toDTO(CategoriaEntity categoriaEntity) {
        CategoriaResponseDTO categoriaResponseDTO = new CategoriaResponseDTO();

        categoriaResponseDTO.setIdCategoria(categoriaEntity.getIdCategoria());
        categoriaResponseDTO.setNome(categoriaEntity.getNome());
        categoriaResponseDTO.setDescricao(categoriaEntity.getDescricao());
        categoriaResponseDTO.setAtivo(categoriaEntity.isAtivo());

        return categoriaResponseDTO;
    }
}
