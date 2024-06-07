package repository.orders;

import base.repository.BaseRepositoryImpl;
import entity.Customer;
import entity.Orders;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class OrdersRepositoryImpl extends BaseRepositoryImpl<Orders, Long>
        implements OrdersRepository {

    public OrdersRepositoryImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Class<Orders> getEntityClass() {
        return Orders.class;
    }

    @Override
    public String getEntity() {
        return "Orders";
    }


    @Override
    public List<Orders> findByCustomer(Customer customer) {
        Session session = sessionFactory.openSession();
        Query<Orders> query =
                session.createQuery("from entity.Orders o where o.customer=:customer", Orders.class);
        query.setParameter("customer",customer);
        return query.getResultList();
    }

}
