package cl.crisgvera.tallerjpa.repositorio;

import cl.crisgvera.tallerjpa.modelo.Pasajero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PasajeroRepositorio extends JpaRepository<Pasajero, Long> {
}
