package repository.admin;

import base.repository.BaseRepositoryImpl;
import entity.Admin;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.Optional;

public class AdminRepositoryImpl extends BaseRepositoryImpl<Admin, Long>
        implements AdminRepository {
    public AdminRepositoryImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Class<Admin> getEntityClass() {
        return Admin.class;
    }

    @Override
    public String getEntity() {
        return "Admin";
    }

    @Override
    public Optional<Admin> signIn(String email, String password) {
        Session session = sessionFactory.getCurrentSession();
        Query<Admin> query =
                session.createQuery("from entity.Admin a " +
                        "where a.email=:email and a.password=:password",getEntityClass());
        query.setParameter("email",email);
        query.setParameter("password",password);
        return Optional.ofNullable(query.uniqueResult());
    }
}
