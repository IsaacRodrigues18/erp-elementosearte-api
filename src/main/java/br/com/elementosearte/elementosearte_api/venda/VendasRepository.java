package br.com.elementosearte.elementosearte_api.venda;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface VendasRepository extends JpaRepository<VendasEntity, Long> {

   List<VendasEntity> findByCriadoEmBetween(LocalDateTime inicio, LocalDateTime fim);

   List<VendasEntity> findByUsuarioIdUsuario(Long idUsuario);
}
