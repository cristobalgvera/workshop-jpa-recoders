package cl.crisgvera.tallerjpa.servicio.util;

import java.util.Collection;

public interface CRUD<Entity, ID> {
    Entity findById(ID id);
    Collection<Entity> findAll();
    Entity save(Entity entity);
    Entity update(Entity entity);
    void delete(Entity entity);
}
