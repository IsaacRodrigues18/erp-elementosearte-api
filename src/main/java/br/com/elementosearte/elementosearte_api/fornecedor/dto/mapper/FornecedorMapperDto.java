package br.com.elementosearte.elementosearte_api.fornecedor.dto.mapper;

import br.com.elementosearte.elementosearte_api.fornecedor.FornecedorEntity;
import br.com.elementosearte.elementosearte_api.fornecedor.dto.FornecedorRequestDTO;
import br.com.elementosearte.elementosearte_api.fornecedor.dto.FornecedorResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class FornecedorMapperDto {

    public FornecedorEntity toEntity(FornecedorRequestDTO requestDTO) {
        FornecedorEntity fornecedorEntity = new FornecedorEntity();

        fornecedorEntity.setNomeFornecedor(requestDTO.getNomeFornecedor());
        fornecedorEntity.setCidade(requestDTO.getCidade());
        fornecedorEntity.setTelefone(requestDTO.getTelefone());
        fornecedorEntity.setEmail(requestDTO.getEmail());
        fornecedorEntity.setObservacao(requestDTO.getObservacao());

        return fornecedorEntity;
    }

    public FornecedorResponseDTO toResponseDto(FornecedorEntity fornecedorEntity) {
        return new FornecedorResponseDTO(
                fornecedorEntity.getIdFornecedor(),
                fornecedorEntity.getNomeFornecedor(),
                fornecedorEntity.getCidade(),
                fornecedorEntity.getTelefone(),
                fornecedorEntity.getEmail(),
                fornecedorEntity.getObservacao(),
                fornecedorEntity.isAtivo()
        );
    }
}
