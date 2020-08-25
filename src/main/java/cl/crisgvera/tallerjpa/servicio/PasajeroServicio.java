package cl.crisgvera.tallerjpa.servicio;

import cl.crisgvera.tallerjpa.modelo.Pasajero;
import cl.crisgvera.tallerjpa.repositorio.PasajeroRepositorio;
import cl.crisgvera.tallerjpa.servicio.util.CRUD;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class PasajeroServicio implements CRUD<Pasajero, Long> {

    @Autowired
    private PasajeroRepositorio pasajeroRepositorio;

    @Override
    public Pasajero findById(Long id) {
        return pasajeroRepositorio.findById(id)
                .orElse(null);
    }

    @Override
    public Collection<Pasajero> findAll() {
        return pasajeroRepositorio.findAll().stream()
                .filter(Objects::nonNull)
                .collect(Collectors.collectingAndThen(Collectors.toList(),
                        Collections::unmodifiableList));
    }

    @Override
    public Pasajero save(Pasajero pasajero) {
        return pasajeroRepositorio.save(pasajero);
    }

    @Override
    public Pasajero update(Pasajero pasajero) {
        return pasajeroRepositorio.save(pasajero);
    }

    @Override
    public void delete(Pasajero pasajero) {
        pasajeroRepositorio.delete(pasajero);
    }
}
