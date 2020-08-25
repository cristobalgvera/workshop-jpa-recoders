package cl.crisgvera.tallerjpa.repositorio;

import cl.crisgvera.tallerjpa.modelo.OficinaUber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OficinaUberRepositorio extends JpaRepository<OficinaUber, Long> {
}
