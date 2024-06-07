package service.orders;

import base.exception.NotFoundException;
import base.service.BaseServiceImpl;
import entity.Customer;
import entity.Orders;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import repository.orders.OrdersRepository;

import java.util.List;

public class OrdersServiceImpl extends BaseServiceImpl<Orders, Long , OrdersRepository> implements OrdersService {
    private final OrdersRepository ordersRepository;
    private final SessionFactory sessionFactory ;

    public OrdersServiceImpl(OrdersRepository ordersRepository, SessionFactory sessionFactory){
        super(ordersRepository, sessionFactory);
        this.ordersRepository = ordersRepository;
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Orders> findByCustomer(Customer customer) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            List<Orders> foundEntity = ordersRepository.findByCustomer(customer);
            session.getTransaction().commit();
            return foundEntity;
        } catch (Exception e) {
            throw new NotFoundException(String.format("entity with %s not found", customer));
        }
    }

}
