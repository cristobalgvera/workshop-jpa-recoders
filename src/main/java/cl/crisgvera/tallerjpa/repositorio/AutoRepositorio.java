package cl.crisgvera.tallerjpa.repositorio;

import cl.crisgvera.tallerjpa.modelo.Auto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AutoRepositorio extends JpaRepository<Auto, Long> {
}
