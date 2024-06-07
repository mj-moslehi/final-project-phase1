package repository.customer;

import base.repository.BaseRepository;
import entity.Customer;

import java.util.Optional;

public interface CustomerRepository extends BaseRepository<Customer,Long> {

    Optional<Customer> signIn(String email,String password);
}
