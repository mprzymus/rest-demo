package pl.mprzymus.apidemo.api.mapper;

import org.junit.jupiter.api.Test;
import pl.mprzymus.apidemo.api.model.CustomerDTO;
import pl.mprzymus.apidemo.domain.Customer;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CustomerMapperTest {

    private final CustomerMapper customerMapper = CustomerMapper.INSTANCE;
    private final String LAST_NAME = "lastName";
    private final String NAME = "name";

    @Test
    void categoryToCategoryDTO() {
        var customer = new Customer();
        customer.setFirstName(NAME);
        customer.setLastName(LAST_NAME);

        var customerDTO = customerMapper.customerToCustomerDTO(customer);

        assertEquals(customer.getFirstName(), customerDTO.getFirstName());
        assertEquals(customer.getLastName(), customerDTO.getLastName());
    }

    @Test
    void categoryDtoToCategory() {
        var customer = new CustomerDTO();
        customer.setFirstName(NAME);
        customer.setLastName(LAST_NAME);

        var customerDTO = customerMapper.customerDtoToCustomer(customer);

        assertEquals(customer.getFirstName(), customerDTO.getFirstName());
        assertEquals(customer.getLastName(), customerDTO.getLastName());
    }
}