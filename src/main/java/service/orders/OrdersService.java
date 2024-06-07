package service.orders;

import base.service.BaseService;
import entity.Customer;
import entity.Orders;

import java.util.List;

public interface OrdersService extends BaseService<Orders,Long> {

    List<Orders> findByCustomer(Customer customer);


}
