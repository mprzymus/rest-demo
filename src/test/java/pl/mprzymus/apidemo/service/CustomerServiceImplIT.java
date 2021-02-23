package pl.mprzymus.apidemo.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pl.mprzymus.apidemo.api.mapper.CustomerMapper;
import pl.mprzymus.apidemo.api.model.CustomerDTO;
import pl.mprzymus.apidemo.bootstrap.Bootstrap;
import pl.mprzymus.apidemo.repositories.CategoryRepository;
import pl.mprzymus.apidemo.repositories.CustomerRepository;
import pl.mprzymus.apidemo.repositories.VendorRepository;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class CustomerServiceImplIT {

    private static final String NAME = "testName";
    private long id = 1L;
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private VendorRepository vendorRepository;

    private CustomerService customerService;

    @BeforeEach
    void setUp() {
        var bootstrap = new Bootstrap(categoryRepository, customerRepository, vendorRepository);
        bootstrap.run();

        customerService = new CustomerServiceImpl(CustomerMapper.INSTANCE, customerRepository);

        id = findId();
    }

    @Test
    void patchCustomerUpdateFirstName() {

        var dto = new CustomerDTO();
        dto.setFirstName(NAME);

        var beforeUpdate = customerRepository.findById(id).orElseThrow();
        var firstNameBeforeUpdate = beforeUpdate.getFirstName();

        var updated = customerService.patchCustomer(id, dto);

        assertEquals(dto.getFirstName(), updated.getFirstName());
        assertNotEquals(firstNameBeforeUpdate, updated.getFirstName());
        assertEquals(beforeUpdate.getLastName(), updated.getLastName());
    }

    @Test
    void patchCustomerUpdateLastName() {
        var dto = new CustomerDTO();
        dto.setLastName(NAME);

        var beforeUpdate = customerRepository.findById(id).orElseThrow();
        var lastNameBeforeUpdate = beforeUpdate.getLastName();

        var updated = customerService.patchCustomer(id, dto);

        assertEquals(beforeUpdate.getFirstName(), updated.getFirstName());
        assertNotEquals(lastNameBeforeUpdate, updated.getLastName());
        assertEquals(dto.getLastName(), updated.getLastName());
    }

    private long findId() {
        return customerRepository.findAll().get(0).getId();
    }
}