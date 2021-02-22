package pl.mprzymus.apidemo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.mprzymus.apidemo.api.mapper.CustomerMapper;
import pl.mprzymus.apidemo.api.model.CustomerDTO;
import pl.mprzymus.apidemo.api.model.CustomerListDTO;
import pl.mprzymus.apidemo.domain.Customer;
import pl.mprzymus.apidemo.repositories.CustomerRepository;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerMapper customerMapper;
    private final CustomerRepository customerRepository;

    @Override
    public CustomerDTO getCustomerById(Long id) {
        var customer = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No customer with id = " + id.toString()));
        return convertToDto(customer);
    }

    @Override
    public CustomerListDTO getAllCustomers() {
        var list = customerRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
        return new CustomerListDTO(list);
    }

    @Override
    public CustomerDTO createNewCustomer(CustomerDTO customerDTO) {
        var customer = customerMapper.customerDtoToCustomer(customerDTO);
        return saveCustomer(customer);
    }

    private CustomerDTO saveCustomer(Customer customer) {
        var saved = customerRepository.save(customer);
        return convertToDto(saved);
    }

    @Override
    public CustomerDTO saveOrUpdate(Long id, CustomerDTO customerDTO) {
        var customer = customerMapper.customerDtoToCustomer(customerDTO);
        customer.setId(id);
        return saveCustomer(customer);
    }

    @Override
    public CustomerDTO patchCustomer(Long id, CustomerDTO customerDTO) {
        return customerRepository.findById(id).map(customer -> {
            if (customerDTO.getFirstName() != null) {
                customer.setFirstName(customerDTO.getFirstName());
            }
            if (customerDTO.getLastName() != null) {
                customer.setLastName(customerDTO.getLastName());
            }
            return convertToDto(customerRepository.save(customer));
        }).orElseThrow(RuntimeException::new);
    }

    @Override
    public void deleteCustomerById(Long id) {
        customerRepository.deleteById(id);
    }

    private CustomerDTO convertToDto(Customer saved) {
        var toReturn = customerMapper.customerToCustomerDTO(saved);
        toReturn.setCustomerUrl("/api/customers/" + saved.getId());
        return toReturn;
    }
}
