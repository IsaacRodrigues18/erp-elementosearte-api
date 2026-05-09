package br.com.elementosearte.elementosearte_api.fornecedor;

import br.com.elementosearte.elementosearte_api.fornecedor.dto.FornecedorRequestDTO;
import br.com.elementosearte.elementosearte_api.fornecedor.dto.FornecedorResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FornecedorService {

    @Autowired
    private FornecedorRepository fornecedorRepository;


    public FornecedorResponseDTO doDTO(FornecedorEntity fornecedor) {
        return new FornecedorResponseDTO(
                fornecedor.getNomeFornecedor(),
                fornecedor.getCidade(),
                fornecedor.getTelefone(),
                fornecedor.getEmail(),
                fornecedor.isAtivo()
        );
    }

    public FornecedorResponseDTO criarFornecedor(FornecedorRequestDTO dto) {
        if (fornecedorRepository.existsByNomeFornecedor(dto.getNomeFornecedor())) {
            throw new IllegalArgumentException("Fornecedor já cadastrado");
        }

        FornecedorEntity fornecedor = new FornecedorEntity();

        fornecedor.setNomeFornecedor(dto.getNomeFornecedor());
        fornecedor.setCidade(dto.getCidade());
        fornecedor.setTelefone(dto.getTelefone());
        fornecedor.setEmail(dto.getEmail());

        FornecedorEntity fornecedorSalvo = fornecedorRepository.save(fornecedor);
        return doDTO(fornecedorSalvo);

    }

    public List<FornecedorResponseDTO> listarFornecedores() {
        return fornecedorRepository.findByAtivoTrue().
                stream().
                map(this::doDTO).
                toList();
    }

    public List<FornecedorResponseDTO> listarFornecedoresPorCidade(String cidade) {
        return fornecedorRepository.findByCidadeAndAtivoTrue(cidade)
                .stream()
                .map(this::doDTO)
                .toList();
    }

    public FornecedorResponseDTO buscarFornecedorPorId(Long idFornecedor) {
        FornecedorEntity fornecedor = fornecedorRepository.findById(idFornecedor)
                .orElseThrow(() -> new IllegalArgumentException("Fornecedor não encontrado"));
        return doDTO(fornecedor);
    }

    public void inativarFornecedor(Long idFornecedor) {
        FornecedorEntity fornecedor = fornecedorRepository.findById(idFornecedor).
                orElseThrow(() -> new IllegalArgumentException("Fornecedor não encontrado"));

        fornecedor.setAtivo(false);
        fornecedorRepository.save(fornecedor);
    }

    public void deletarFornecedor(Long idFornecedor){
        FornecedorEntity fornecedor = fornecedorRepository.findById(idFornecedor).
                orElseThrow(() -> new IllegalArgumentException("Fornecedor não encontrado"));

        fornecedorRepository.delete(fornecedor);
    }


}
