package service.customer;

import base.service.BaseServiceImpl;
import entity.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import repository.customer.CustomerRepository;

import java.util.Optional;

public class CustomerServiceImpl extends BaseServiceImpl<Customer,Long, CustomerRepository> implements CustomerService {
    private final CustomerRepository customerRepository;
    private final SessionFactory sessionFactory;

    public CustomerServiceImpl(CustomerRepository customerRepository , SessionFactory sessionFactory){
        super(customerRepository,sessionFactory);
        this.customerRepository = customerRepository;
        this.sessionFactory  = sessionFactory;
    }

    @Override
    public Optional<Customer> signIn(String email, String password) {
        Transaction transaction = null;
        try (Session session = sessionFactory.getCurrentSession()) {
            transaction = session.beginTransaction();
            Optional<Customer> result = customerRepository.signIn(email,password);
            transaction.commit();
            return result;
        } catch (Exception e) {
            assert transaction != null;
            transaction.rollback();
            return Optional.empty();
        }
    }
}
