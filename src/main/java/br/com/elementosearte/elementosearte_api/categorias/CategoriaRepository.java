package br.com.elementosearte.elementosearte_api.categorias;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CategoriaRepository extends JpaRepository<CategoriaEntity, Long> {

    boolean existsByNome(String nome);
    Optional<CategoriaEntity> findByAtivoTrue(Long idCategoria);
}
