package base.repository;

import base.entity.BaseEntity;
import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.io.Serializable;
import java.util.List;

@AllArgsConstructor

public abstract class BaseRepositoryImpl<T extends BaseEntity<ID>, ID extends Serializable>
        implements BaseRepository<T, ID> {

    protected SessionFactory sessionFactory;

    @Override
    public T saveOrUpdate(T entity) {
        Session session = sessionFactory.getCurrentSession();
        if (entity.getId() == null) session.persist(entity);
        else entity = session.merge(entity);
        return entity;
    }

    @Override
    public T findById(ID id) {
        Session session = sessionFactory.getCurrentSession();
        Query<T> query =
                session.createQuery(String.format("from %s where id =:id", getEntity()), getEntityClass());
        query.setParameter("id", id);
        return query.getSingleResultOrNull();
    }

    @Override
    public void delete(T entity) {
        Session session = sessionFactory.getCurrentSession();
        session.remove(entity);
    }

    @Override
    public List<T> findAll() {
        Session session = sessionFactory.getCurrentSession();
        Query<T> query = session.createQuery(String.format("from %s", getEntity()), getEntityClass());
        return query.getResultList();
    }

    public abstract Class<T> getEntityClass();

    public abstract String getEntity();

}
