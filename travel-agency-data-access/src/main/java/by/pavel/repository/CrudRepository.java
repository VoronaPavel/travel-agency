package by.pavel.repository;

import by.pavel.domain.Dto;
import by.pavel.domain.Entity;

public interface CrudRepository<E extends Entity, D extends Dto<E>> {

    E save(D dto);

    void save(E entity);
    void update(E entity);
    void delete(E entity);
}
