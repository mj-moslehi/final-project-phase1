package base.service;

import base.entity.BaseEntity;
import base.repository.BaseRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.SessionFactory;

import java.io.Serializable;

@RequiredArgsConstructor

public class BaseServiceImpl<T extends BaseEntity<ID>, ID extends Serializable,
        R extends BaseRepository<T, ID>>
        implements BaseService<T, ID> {
    private final R repository;
    private final SessionFactory sessionFactory;
}
