package br.com.elementosearte.elementosearte_api.usuarios;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {

    boolean existsByEmail(String email);

    Optional<UsuarioEntity> findByEmail(String email);

    List<UsuarioEntity> findByAtivoTrue();

    Optional<UsuarioEntity> findById(Long idUsuario);



}
