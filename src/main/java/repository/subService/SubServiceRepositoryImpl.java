package repository.subService;

import base.repository.BaseRepositoryImpl;
import entity.Service;
import entity.SubService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class SubServiceRepositoryImpl extends BaseRepositoryImpl<SubService,Long>
        implements SubServiceRepository{
    public SubServiceRepositoryImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Class<SubService> getEntityClass() {
        return SubService.class;
    }

    @Override
    public String getEntity() {
        return "SubService";
    }

    @Override
    public List<SubService> findByService(Service service) {
        Session session = sessionFactory.openSession();
        Query<SubService> query =
                session.createQuery("from entity.SubService ss where ss.service=:service",getEntityClass());
        query.setParameter("service",service);
        return query.getResultList();
    }
}
