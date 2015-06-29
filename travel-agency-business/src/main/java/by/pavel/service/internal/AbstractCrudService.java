package by.pavel.service.internal;

import by.pavel.domain.Dto;
import by.pavel.domain.Entity;
import by.pavel.repository.CrudRepository;
import by.pavel.service.CrudService;

public abstract class AbstractCrudService<E extends Entity, D extends Dto<E>, R extends CrudRepository<E, D>> implements CrudService<E, D> {

    protected final R repository;

    protected AbstractCrudService(R repository) {
        this.repository = repository;
    }

    @Override public void save(E entity) {
        repository.save(entity);
    }

    @Override public E save(D dto) {
        return repository.save(dto);
    }

    @Override public void update(E entity) {
        repository.update(entity);
    }

    @Override public void delete(E entity) {
        repository.delete(entity);
    }
}