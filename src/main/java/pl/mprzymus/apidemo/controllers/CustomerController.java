package pl.mprzymus.apidemo.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.mprzymus.apidemo.api.model.CustomerDTO;
import pl.mprzymus.apidemo.api.model.CustomerListDTO;
import pl.mprzymus.apidemo.service.CustomerService;

@RestController
@RequestMapping(CustomerController.URL)
@RequiredArgsConstructor
public class CustomerController {
    public static final String URL = "/api/customers";
    private final CustomerService customerService;

    @GetMapping
    public ResponseEntity<CustomerListDTO> getListOfCustomers() {

        return new ResponseEntity<>(customerService.getAllCustomers(), HttpStatus.OK);

    }

    @GetMapping({"/{id}"})
    public ResponseEntity<CustomerDTO> getCustomerById(@PathVariable Long id) {
        return new ResponseEntity<CustomerDTO>(customerService.getCustomerById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CustomerDTO> createNewCustomer(@RequestBody CustomerDTO customerDTO) {
        return new ResponseEntity<>(customerService.createNewCustomer(customerDTO), HttpStatus.CREATED);
    }

    @PutMapping({"/{id}"})
    public ResponseEntity<CustomerDTO> updateCustomer(@RequestBody CustomerDTO customerDTO, @PathVariable Long id) {
        return new ResponseEntity<>(customerService.saveOrUpdate(id, customerDTO), HttpStatus.OK);
    }

    @PatchMapping({"/{id}"})
    public ResponseEntity<CustomerDTO> patchCustomer(@RequestBody CustomerDTO customerDTO, @PathVariable Long id) {
        return new ResponseEntity<>(customerService.patchCustomer(id, customerDTO), HttpStatus.OK);
    }

    @DeleteMapping({"/{id}"})
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomerById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

