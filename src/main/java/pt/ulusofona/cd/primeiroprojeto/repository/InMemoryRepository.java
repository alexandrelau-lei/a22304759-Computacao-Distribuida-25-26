package pt.ulusofona.cd.primeiroprojeto.repository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryRepository<T extends Identifiable>  implements GenericRepository<T> {

    private final Map<UUID, T> db = new ConcurrentHashMap<>();

    @Override
    public T save(T entity) {
        if (entity.getId() == null) {
            entity.setId(UUID.randomUUID());
        }
        db.put(entity.getId(), entity);
        return entity;
    }

    @Override
    public Optional<T> findById(UUID id) {
        return Optional.ofNullable(db.get(id));
    }

    @Override
    public List<T> findAll() {
        return new ArrayList<>(db.values());
    }

    @Override
    public void deleteById(UUID id) {
        db.remove(id);
    }

    @Override
    public boolean existsById(UUID id) {
        return db.containsKey(id);
    }
}

interface GenericRepository<T extends Identifiable>  {
    T save(T entity);
    Optional<T> findById(UUID id);
    List<T> findAll();
    void deleteById(UUID id);
    boolean existsById(UUID id);
}
