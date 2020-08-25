package cl.crisgvera.tallerjpa.servicio;

import cl.crisgvera.tallerjpa.modelo.Chofer;
import cl.crisgvera.tallerjpa.modelo.Pasajero;
import cl.crisgvera.tallerjpa.modelo.Viaje;
import cl.crisgvera.tallerjpa.modelo.relacional.ViajeId;
import cl.crisgvera.tallerjpa.repositorio.ViajeRepositorio;
import cl.crisgvera.tallerjpa.servicio.util.CRUD;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ViajeServicio implements CRUD<Viaje, ViajeId> {

    @Autowired
    private ViajeRepositorio viajeRepositorio;

    @Override
    public Viaje findById(ViajeId viajeId) {
        return viajeRepositorio.findById(viajeId)
                .orElse(null);
    }

    // Método polimórfico que entrega la posibilidad de encontrar viajes mediante chofer y pasajero
    public Viaje findById(Chofer chofer, Pasajero pasajero) {
        return findById(new ViajeId(chofer.getId(), pasajero.getId()));
    }

    public Collection<Viaje> findByPasajeroId(Long pasajeroId) {
        return viajeRepositorio.findAllByPasajero_Id(pasajeroId);
    }

    @Override
    public Collection<Viaje> findAll() {
        return viajeRepositorio.findAll().stream()
                .filter(Objects::nonNull)
                .collect(Collectors.collectingAndThen(Collectors.toList(),
                        Collections::unmodifiableList));
    }

    @Override
    public Viaje save(Viaje viaje) {
        return viajeRepositorio.save(viaje);
    }

    @Override
    public Viaje update(Viaje viaje) {
        return viajeRepositorio.save(viaje);
    }

    @Override
    public void delete(Viaje viaje) {
        viajeRepositorio.delete(viaje);
    }

}
