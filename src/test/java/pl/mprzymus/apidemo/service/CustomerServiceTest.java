package pl.mprzymus.apidemo.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.mprzymus.apidemo.api.mapper.CustomerMapper;
import pl.mprzymus.apidemo.api.model.CustomerDTO;
import pl.mprzymus.apidemo.domain.Customer;
import pl.mprzymus.apidemo.repositories.CustomerRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

    private final String LAST_NAME = "lastName";
    private final String NAME = "name";
    public static final long ID = 1L;
    @Mock
    private CustomerRepository customerRepository;

    private CustomerService customerService;

    @BeforeEach
    void setUp() {
        customerService = new CustomerServiceImpl(CustomerMapper.INSTANCE, customerRepository);
    }
    @Test
    void getCustomerById() {
        var customer = new Customer();
        customer.setId(ID);
        customer.setFirstName(NAME);
        customer.setLastName(LAST_NAME);

        when(customerRepository.findById(any())).thenReturn(Optional.of(customer));

        var result = customerService.getCustomerById(ID);

        assertEquals(customer.getFirstName(), result.getFirstName());
        assertEquals(customer.getLastName(), result.getLastName());
    }

    @Test
    void getCustomerUnknownId() {
        when(customerRepository.findById(any())).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> customerService.getCustomerById(ID));

    }

    @Test
    void getAllCustomers() {
        var customersList = List.of(new Customer(), new Customer());

        when(customerRepository.findAll()).thenReturn(customersList);

        var result = customerService.getAllCustomers();

        assertEquals(customersList.size(), result.getCustomers().size());
    }

    @Test
    void createNewCustomer() {
        var customerDto = new CustomerDTO();
        customerDto.setFirstName(NAME);
        customerDto.setLastName(LAST_NAME);

        var savedCustomer = new Customer();
        savedCustomer.setId(ID);
        savedCustomer.setFirstName(customerDto.getFirstName());
        savedCustomer.setLastName(customerDto.getLastName());

        when(customerRepository.save(any())).thenReturn(savedCustomer);

        var savedDto = customerService.createNewCustomer(customerDto);

        assertEquals(customerDto.getFirstName(), savedDto.getFirstName());
        assertEquals(customerDto.getLastName(), savedDto.getLastName());
        assertEquals("/api/customers/"+ ID, savedDto.getCustomerUrl());
    }

    @Test
    void deleteTest() {
        customerService.deleteCustomerById(ID);

        verify(customerRepository, times(1)).deleteById(eq(ID));
    }
}