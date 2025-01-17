package base.service;

import base.entity.BaseEntity;
import base.repository.BaseRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.io.Serializable;
import java.util.List;

@RequiredArgsConstructor

public class BaseServiceImpl<T extends BaseEntity<ID>, ID extends Serializable,
        R extends BaseRepository<T, ID>>
        implements BaseService<T, ID> {

    private final R repository;
    private final SessionFactory sessionFactory;

    @Override
    public T saveOrUpdate(T entity) {
        Transaction transaction = null;
        try (Session session = sessionFactory.getCurrentSession()) {
            transaction = session.beginTransaction();
            T t = repository.saveOrUpdate(entity);
            transaction.commit();
            return t;
        } catch (Exception e) {
            assert transaction != null;
            transaction.rollback();
            return null;
        }
    }

    @Override
    public T findById(ID id) {
        Transaction transaction = null;
        try (Session session = sessionFactory.getCurrentSession()) {
            transaction = session.beginTransaction();
            T entity = repository.findById(id);
            transaction.commit();
            return entity;
        } catch (Exception e) {
            assert transaction != null;
            transaction.rollback();
            return null;
        }
    }

    @Override
    public void delete(T t) {
        Transaction transaction = null;
        try (Session session = sessionFactory.getCurrentSession()) {
            transaction = session.beginTransaction();
            repository.delete(t);
            transaction.commit();
        } catch (Exception e) {
            assert transaction != null;
            transaction.rollback();
        }
    }

    @Override
    public List<T> findAll() {
        Transaction transaction = null;
        try (Session session = sessionFactory.getCurrentSession()) {
            transaction = session.beginTransaction();
            List<T> entity = repository.findAll();
            transaction.commit();
            return entity;
        } catch (Exception e) {
            assert transaction != null;
            transaction.rollback();
            return null;
        }
    }
}