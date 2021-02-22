package pl.mprzymus.apidemo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.mprzymus.apidemo.domain.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
