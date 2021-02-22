package pl.mprzymus.apidemo.service;

import pl.mprzymus.apidemo.api.model.CustomerDTO;
import pl.mprzymus.apidemo.api.model.CustomerListDTO;

public interface CustomerService {
    CustomerDTO getCustomerById(Long id);
    CustomerListDTO getAllCustomers();
    CustomerDTO createNewCustomer(CustomerDTO customerDTO);
    CustomerDTO saveOrUpdate(Long id, CustomerDTO customerDTO);
    CustomerDTO patchCustomer(Long id, CustomerDTO customerDTO);
}
