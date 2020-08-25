package cl.crisgvera.tallerjpa.servicio;

import cl.crisgvera.tallerjpa.modelo.OficinaUber;
import cl.crisgvera.tallerjpa.repositorio.OficinaUberRepositorio;
import cl.crisgvera.tallerjpa.servicio.util.CRUD;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class OficinaUberServicio implements CRUD<OficinaUber, Long> {

    @Autowired
    private OficinaUberRepositorio oficinaUberRepositorio;

    @Override
    public OficinaUber findById(Long id) {
        return oficinaUberRepositorio.findById(id)
                .orElse(null);
    }

    @Override
    public Collection<OficinaUber> findAll() {
        return oficinaUberRepositorio.findAll().stream()
                .filter(Objects::nonNull)
                .collect(Collectors.collectingAndThen(Collectors.toList(),
                        Collections::unmodifiableList));
    }

    @Override
    public OficinaUber save(OficinaUber oficinaUber) {
        return oficinaUberRepositorio.save(oficinaUber);
    }

    @Override
    public OficinaUber update(OficinaUber oficinaUber) {
        return oficinaUberRepositorio.save(oficinaUber);
    }

    @Override
    public void delete(OficinaUber oficinaUber) {
        oficinaUberRepositorio.delete(oficinaUber);
    }
}
