package cl.crisgvera.tallerjpa.repositorio;

import cl.crisgvera.tallerjpa.modelo.Viaje;
import cl.crisgvera.tallerjpa.modelo.relacional.ViajeId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface ViajeRepositorio extends JpaRepository<Viaje, ViajeId> {
    Collection<Viaje> findAllByPasajero_Id(Long pasajeroId);
}
