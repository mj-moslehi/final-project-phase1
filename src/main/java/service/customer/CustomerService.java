package service.customer;

import base.service.BaseService;
import entity.Customer;

import java.util.Optional;

public interface CustomerService extends BaseService<Customer,Long> {

    Optional<Customer> signIn(String email, String password);
}
