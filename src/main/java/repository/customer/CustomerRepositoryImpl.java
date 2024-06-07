package repository.customer;

import base.repository.BaseRepositoryImpl;
import entity.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.Optional;

public class CustomerRepositoryImpl extends BaseRepositoryImpl<Customer,Long>
        implements CustomerRepository {
    public CustomerRepositoryImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Class<Customer> getEntityClass() {
        return Customer.class;
    }

    @Override
    public String getEntity() {
        return "Customer";
    }

    @Override
    public Optional<Customer> signIn(String email, String password) {
        Session session = sessionFactory.getCurrentSession();
        Query<Customer> query =
                session.createQuery("from entity.Customer c " +
                        "where c.email=:email and c.password=:password",getEntityClass());
        query.setParameter("email",email);
        query.setParameter("password",password);
        return Optional.ofNullable(query.uniqueResult());
    }
}
