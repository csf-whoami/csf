package com.csf.whoami.service;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.data.jpa.repository.JpaRepository;

import com.csf.whoami.database.models.BaseEntity;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class BaseServiceAbstract<E extends BaseEntity, K extends Serializable> implements BaseService<E, K> {
    protected abstract JpaRepository<E, K> getRepository();

    @Override
    public E save(E entry) {
        return getRepository().save(entry);
    }

    @Override
    public List<E> saveAll(Iterable<E> entries) {
        return getRepository().saveAll(entries);
    }

    @Override
    public void delete(K id) {
        log.info("Delete entity id : {} ", id);
        E entity = getEntity(id);
        Optional.ofNullable(entity).ifPresent(it -> {
            it.setDeletedAt(new Date());
            getRepository().save(entity);
        });
    }

    @Override
    public E getEntity(K id) {
        log.info("Get entity id : {} ", id);
        return getRepository().findById((K) id).orElseThrow(() -> new EntityNotFoundException("Entity not found"));
    }
}
