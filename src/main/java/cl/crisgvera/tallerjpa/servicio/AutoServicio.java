package cl.crisgvera.tallerjpa.servicio;

import cl.crisgvera.tallerjpa.modelo.Auto;
import cl.crisgvera.tallerjpa.repositorio.AutoRepositorio;
import cl.crisgvera.tallerjpa.servicio.util.CRUD;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class AutoServicio implements CRUD<Auto, Long> {
    @Autowired
    private AutoRepositorio autoRepositorio;

    @Override
    public Auto findById(Long id) {
        return autoRepositorio.findById(id)
                .orElse(null);
    }

    @Override
    public Collection<Auto> findAll() {
        return autoRepositorio.findAll().stream()
                .filter(Objects::nonNull)
                .collect(Collectors.collectingAndThen(Collectors.toList(),
                        Collections::unmodifiableList));
    }

    @Override
    public Auto save(Auto auto) {
        return autoRepositorio.save(auto);
    }

    @Override
    public Auto update(Auto auto) {
        return autoRepositorio.save(auto);
    }

    @Override
    public void delete(Auto auto) {
        autoRepositorio.delete(auto);
    }
}
