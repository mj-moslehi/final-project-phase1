package repository.expert;

import base.repository.BaseRepositoryImpl;
import entity.Expert;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.Optional;

public class ExpertRepositoryImpl extends BaseRepositoryImpl<Expert, Long>
        implements ExpertRepository {

    public ExpertRepositoryImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Class<Expert> getEntityClass() {
        return Expert.class;
    }

    @Override
    public String getEntity() {
        return "Expert";
    }

    @Override
    public Optional<Expert> signIn(String email, String password) {
        Session session = sessionFactory.getCurrentSession();
        Query<Expert> query =
                session.createQuery("from entity.Expert e " +
                        "where e.email=:email and e.password=:password",getEntityClass());
        query.setParameter("email",email);
        query.setParameter("password",password);
        return Optional.ofNullable(query.uniqueResult());
    }
}
