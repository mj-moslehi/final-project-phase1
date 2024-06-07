package repository.expert_subService;

import base.repository.BaseRepositoryImpl;
import entity.Expert;
import entity.Expert_SubService;
import entity.SubService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class Expert_SubServiceRepositoryImpl extends BaseRepositoryImpl<Expert_SubService, Long>
        implements Expert_SubServiceRepository {

    public Expert_SubServiceRepositoryImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Class<Expert_SubService> getEntityClass() {
        return Expert_SubService.class;
    }

    @Override
    public String getEntity() {
        return "Expert_SubService";
    }

    @Override
    public List<SubService> findSubServiceByExpert(Expert expert) {
        Session session = sessionFactory.openSession();
        Query<SubService> query =
                session.createQuery("select es.subService " +
                        "from entity.Expert_SubService es " +
                        "where es.expert=:expert",SubService.class);
        query.setParameter("expert",expert);
        return query.getResultList();
    }

    @Override
    public List<Expert_SubService> findByExpertAndSubService(Expert expert, SubService subService) {
        Session session = sessionFactory.openSession();
        Query<Expert_SubService> query =
                session.createQuery("from entity.Expert_SubService es " +
                        "where es.subService=:subService and es.expert=:expert", Expert_SubService.class);
        query.setParameter("subService",subService);
        query.setParameter("expert",expert);
        return query.getResultList();
    }

}
