package br.com.elementosearte.elementosearte_api.fornecedor;

import br.com.elementosearte.elementosearte_api.exceptions.BusinessException;
import br.com.elementosearte.elementosearte_api.exceptions.ResourceNotFoundException;
import br.com.elementosearte.elementosearte_api.fornecedor.dto.FornecedorRequestDTO;
import br.com.elementosearte.elementosearte_api.fornecedor.dto.FornecedorResponseDTO;
import br.com.elementosearte.elementosearte_api.fornecedor.dto.mapper.FornecedorMapperDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FornecedorService {

    private final FornecedorRepository fornecedorRepository;
    private final FornecedorMapperDto fornecedorMapperDto;

    public FornecedorService(FornecedorRepository fornecedorRepository, FornecedorMapperDto fornecedorMapperDto) {
        this.fornecedorRepository = fornecedorRepository;
        this.fornecedorMapperDto = fornecedorMapperDto;
    }


    public FornecedorResponseDTO criarFornecedor(FornecedorRequestDTO dto) {
        if (fornecedorRepository.existsByNomeFornecedor(dto.getNomeFornecedor())) {
            throw new BusinessException("Fornecedor já cadastrado");
        }

        if (dto.getEmail() != null && dto.getEmail().isBlank()) {
            dto.setEmail(null);
        }

        FornecedorEntity fornecedor = fornecedorMapperDto.toEntity(dto);

        FornecedorEntity fornecedorSalvo = fornecedorRepository.save(fornecedor);

        return fornecedorMapperDto.toResponseDto(fornecedorSalvo);

    }

    public List<FornecedorResponseDTO> listarFornecedores() {
        return fornecedorRepository.findAll().
                stream().
                map(fornecedorMapperDto::toResponseDto).
                toList();
    }

    public List<FornecedorResponseDTO> listarFornecedoresAtivos() {
        return fornecedorRepository.findByAtivoTrue().
                stream().
                map(fornecedorMapperDto::toResponseDto).
                toList();
    }


    public List<FornecedorResponseDTO> listarFornecedoresPorCidade(String cidade) {
        return fornecedorRepository.findByCidadeAndAtivoTrue(cidade)
                .stream()
                .map(fornecedorMapperDto::toResponseDto)
                .toList();
    }

    public FornecedorResponseDTO buscarFornecedorPorId(Long idFornecedor) {
        FornecedorEntity fornecedor = fornecedorRepository.findById(idFornecedor)
                .orElseThrow(() -> new ResourceNotFoundException("Fornecedor não encontrado"));
        return fornecedorMapperDto.toResponseDto(fornecedor);
    }

    public void inativarFornecedor(Long idFornecedor) {
        FornecedorEntity fornecedor = fornecedorRepository.findById(idFornecedor).
                orElseThrow(() -> new ResourceNotFoundException("Fornecedor não encontrado"));

        if (!fornecedor.isAtivo()) {
            throw new BusinessException("Fornecedor já está inativo");
        }

        fornecedor.setAtivo(false);
        fornecedorRepository.save(fornecedor);
    }

    public void ativarFornecedor(Long idFornecedor) {
        FornecedorEntity fornecedor = fornecedorRepository.findById(idFornecedor)
                .orElseThrow(() -> new ResourceNotFoundException("Fornecedor não encontrado"));

        if (fornecedor.isAtivo()) {
            throw new BusinessException("Fornecedor já está ativo");
        }

        fornecedor.setAtivo(true);
        fornecedorRepository.save(fornecedor);
    }


    public void deletarFornecedor(Long idFornecedor) {
        FornecedorEntity fornecedor = fornecedorRepository.findById(idFornecedor).
                orElseThrow(() -> new ResourceNotFoundException("Fornecedor não encontrado"));

        fornecedorRepository.delete(fornecedor);
    }

}
