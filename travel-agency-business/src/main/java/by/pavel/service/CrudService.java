package by.pavel.service;

import by.pavel.domain.Dto;
import by.pavel.domain.Entity;

import javax.validation.Valid;

public interface CrudService<E extends Entity, D extends Dto<E>> {

    void save(@Valid E entity);
    E save(@Valid D dto);
    void update(@Valid E entity);
    void delete(@Valid E entity);
}
