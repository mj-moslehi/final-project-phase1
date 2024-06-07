package base.repository;

import base.entity.BaseEntity;

import java.io.Serializable;
import java.util.List;

public interface BaseRepository <T extends BaseEntity<ID>, ID extends Serializable>{

    T saveOrUpdate(T entity);

    T findById(ID id);

    void delete(T entity);

    List<T> findAll();

}
