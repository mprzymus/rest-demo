package pl.mprzymus.apidemo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.mprzymus.apidemo.api.mapper.CustomerMapper;
import pl.mprzymus.apidemo.api.model.CustomerDTO;
import pl.mprzymus.apidemo.api.model.CustomerListDTO;
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
        return customerMapper.customerToCustomerDTO(customer);
    }

    @Override
    public CustomerListDTO getAllCustomers() {
        var list = customerRepository.findAll().stream()
                .map(customerMapper::customerToCustomerDTO)
                .collect(Collectors.toList());
        return new CustomerListDTO(list);
    }

    @Override
    public CustomerDTO createNewCustomer(CustomerDTO customerDTO) {
        var customer = customerMapper.customerDtoToCustomer(customerDTO);
        var saved = customerRepository.save(customer);
        var toReturn = customerMapper.customerToCustomerDTO(saved);
        toReturn.setCustomerUrl("/api/customer/" + saved.getId());
        return toReturn;
    }
}
