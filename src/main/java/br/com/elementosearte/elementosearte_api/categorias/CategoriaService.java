package br.com.elementosearte.elementosearte_api.categorias;

import br.com.elementosearte.elementosearte_api.categorias.dto.CategoriaRequestDTO;
import br.com.elementosearte.elementosearte_api.categorias.dto.CategoriaResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService {


    @Autowired
    private CategoriaRepository categoriaRepository;


    public CategoriaResponseDTO criarCategoria(CategoriaRequestDTO categoriaDTO) {
        if (categoriaRepository.existsByNome(categoriaDTO.getNome())) {
            throw new IllegalArgumentException("Categoria já cadastrada");
        }

        CategoriaEntity categoriaEntity = new CategoriaEntity();
        categoriaEntity.setNome(categoriaDTO.getNome());
        categoriaEntity.setDescricao(categoriaDTO.getDescricao());

        CategoriaEntity categoriaSalva = categoriaRepository.save(categoriaEntity);

        return new CategoriaResponseDTO(
                categoriaSalva.getIdCategoria(),
                categoriaSalva.getNome(),
                categoriaSalva.getDescricao(),
                categoriaSalva.isAtivo()
        );
    }

    public List<CategoriaResponseDTO> listarCategorias() {
        List<CategoriaEntity> categoriaEntityList = categoriaRepository.findAll();
        return categoriaEntityList.stream()
                .map(categoria -> new CategoriaResponseDTO(
                        categoria.getIdCategoria(),
                        categoria.getNome(),
                        categoria.getDescricao(),
                        categoria.isAtivo()
                ))
                .toList();
    }

    public CategoriaResponseDTO buscarCategoriaPorId(Long idCategoria) {
        CategoriaEntity categoria = categoriaRepository.findById(idCategoria)
                .orElseThrow(() -> new IllegalArgumentException("Categoria não encontrada"));

        return new CategoriaResponseDTO(
                categoria.getIdCategoria(),
                categoria.getNome(),
                categoria.getDescricao(),
                categoria.isAtivo()
        );
    }


    public CategoriaResponseDTO atualizarCategoria(Long idCategoria, CategoriaRequestDTO categoriaDTO) {
        CategoriaEntity categoria = categoriaRepository.findById(idCategoria)
                .orElseThrow(() -> new IllegalArgumentException("Categoria não encontrada"));

        if (!categoria.getNome().equals(categoriaDTO.getNome())
                && categoriaRepository.existsByNome(categoriaDTO.getNome())) {
            throw new IllegalArgumentException("Categoria já cadastrada");
        }

        categoria.setNome(categoriaDTO.getNome());
        categoria.setDescricao(categoriaDTO.getDescricao());

        CategoriaEntity categoriaAtualizada = categoriaRepository.save(categoria);

        return new CategoriaResponseDTO(
                categoriaAtualizada.getIdCategoria(),
                categoriaAtualizada.getNome(),
                categoriaAtualizada.getDescricao(),
                categoriaAtualizada.isAtivo()
        );
    }


    public void deletarCategoria(Long idCategoria) {
        if (!categoriaRepository.existsById(idCategoria)) {
            throw new IllegalArgumentException("Categoria não encontrada");
        }

        categoriaRepository.deleteById(idCategoria);
    }


}



