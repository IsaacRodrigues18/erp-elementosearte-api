package br.com.elementosearte.elementosearte_api.categorias;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<CategoriaEntity, Long> {

    boolean existsByNome(String nome);
}
