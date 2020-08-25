package cl.crisgvera.tallerjpa.servicio;

import cl.crisgvera.tallerjpa.modelo.Chofer;
import cl.crisgvera.tallerjpa.repositorio.ChoferRepositorio;
import cl.crisgvera.tallerjpa.servicio.util.CRUD;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ChoferServicio implements CRUD<Chofer, Long> {

    // Inyectamos la dependencia relacionada con el repositorio de la entidad Chofer
    @Autowired
    private ChoferRepositorio choferRepositorio;

    // Buscamos un registro en la base de datos mediante un id solicitado
    @Override
    public Chofer findById(Long id) { // READ
        return choferRepositorio.findById(id)
                .orElse(null); // Recordar que findById(ID id) retorna un objeto Optional
    }

    @Override
    public Collection<Chofer> findAll() { // READ
        // Utilizamos programación funcional para obtener fácilmente todos los registros
        return choferRepositorio.findAll() // Encuentra todos los objetos de la tabla
                .stream() // Crea un objeto de tipo stream con ellos
                .filter(Objects::nonNull) // Filtramos los objetos para seleccionar sólo los no nulos
                .collect(Collectors.collectingAndThen(Collectors.toList(), // Coleccionamos los objetos filtrados
                        Collections::unmodifiableList)); // Volvemos dicha colección una colección inmodificable
    }

    @Override
    public Chofer save(Chofer chofer) { // CREATE
        return choferRepositorio.save(chofer); // Método save(Entity entity) del CrudRepository
    }

    @Override
    public Chofer update(Chofer chofer) { // UPDATE
        // El método update(Entity entity) no existe, por ello utilizamos el método
        // save(Entity entity). Esto es debido a los estados de las entidades en las bases de datos.
        return choferRepositorio.save(chofer);
    }

    @Override
    public void delete(Chofer chofer) { // DELETE
        choferRepositorio.delete(chofer); // Método delete(Entity entity) del CrudRepository
    }

}
