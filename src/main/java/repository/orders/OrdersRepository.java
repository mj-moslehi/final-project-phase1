package repository.orders;

import base.repository.BaseRepository;
import entity.Customer;
import entity.Orders;

import java.util.List;

public interface OrdersRepository extends BaseRepository<Orders, Long> {

    List<Orders> findByCustomer(Customer customer);

}
