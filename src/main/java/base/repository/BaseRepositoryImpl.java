package base.repository;

import base.entity.BaseEntity;
import lombok.AllArgsConstructor;

import java.io.Serializable;
@AllArgsConstructor

public abstract class BaseRepositoryImpl<T extends BaseEntity<ID>, ID extends Serializable>
        implements BaseRepository<T, ID>{



}
