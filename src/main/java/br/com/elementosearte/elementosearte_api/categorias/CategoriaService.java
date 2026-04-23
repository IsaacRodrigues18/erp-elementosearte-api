package br.com.elementosearte.elementosearte_api.categorias;

import br.com.elementosearte.elementosearte_api.categorias.dto.CategoriaRequestDTO;
import br.com.elementosearte.elementosearte_api.categorias.dto.CategoriaResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoriaService {


    @Autowired
    CategoriaRepository categoriaRepository;


    public CategoriaResponseDTO criarCategoria(CategoriaRequestDTO categoriaDTO){
        if (CategoriaRepository.existsByNome(categoriaDTO.getNome())){
            throw new IllegalArgumentException("Categoria já cadastrada");
        }

        CategoriaEntity categoriaEntity = new CategoriaEntity();
        categoriaEntity.setNome(categoriaDTO.getNome());
        categoriaEntity.setDescricao(categoriaDTO.getDescricao());

        CategoriaEntity categoriaSalvo = categoriaRepository.save(categoriaEntity);

        return new CategoriaResponseDTO(
                categoriaSalvo.getIdCategoria(),
                categoriaSalvo.getNome(),
                categoriaSalvo.getDescricao(),
                categoriaSalvo.isAtivo()
        );
    }





}
