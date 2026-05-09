package br.com.elementosearte.elementosearte_api.fornecedor;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface FornecedorRepository extends JpaRepository<FornecedorEntity, Long> {


    boolean existsByNomeFornecedor(String nomeFornecedor);

    List<FornecedorEntity> findByCidade(String cidade);

    List<FornecedorEntity> findByAtivoTrue();

    List<FornecedorEntity> findByCidadeAndAtivoTrue(String cidade);


}
