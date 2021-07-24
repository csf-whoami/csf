package com.csf.whoami.service;

import java.io.Serializable;
import java.util.List;

import com.csf.whoami.database.models.BaseEntity;

public interface BaseService<E extends BaseEntity, K extends Serializable> {

    E save(E entry);
    List<E> saveAll(Iterable<E> entries);
    E getEntity(K id);
    void delete(K id);
}
