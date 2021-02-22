package pl.mprzymus.apidemo.controllers;

import com.sun.xml.bind.v2.model.core.ID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import pl.mprzymus.apidemo.api.model.CustomerDTO;
import pl.mprzymus.apidemo.api.model.CustomerListDTO;
import pl.mprzymus.apidemo.service.CustomerService;

import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(MockitoExtension.class)
class CustomerControllerTest extends AbstractRestControllerTest {
    public static final String FIRST_NAME = "Michale";
    public static final String LAST_NAME = "Weston";
    private static final Long ID = 1L;
    public static final String URL = "/api/customer/";
    @Mock
    private CustomerService customerService;

    @InjectMocks
    private CustomerController customerController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();
    }

    @Test
    public void testListCustomers() throws Exception {

        //given
        CustomerDTO customer1 = new CustomerDTO();
        customer1.setFirstName("Michale");
        customer1.setLastName("Weston");
        customer1.setCustomerUrl("/api/customer/1");

        CustomerDTO customer2 = new CustomerDTO();
        customer2.setFirstName("Sam");
        customer2.setLastName("Axe");
        customer2.setCustomerUrl("/api/customer/2");

        when(customerService.getAllCustomers()).thenReturn(new CustomerListDTO(List.of(customer1, customer2)));

        mockMvc.perform(get("/api/customers/")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.customers", hasSize(2)));
    }

    @Test
    public void testGetCustomerById() throws Exception {

        //given
        CustomerDTO customer1 = new CustomerDTO();
        customer1.setFirstName(FIRST_NAME);
        customer1.setLastName("Weston");
        customer1.setCustomerUrl("/api/customer/1");

        when(customerService.getCustomerById(anyLong())).thenReturn(customer1);

        //when
        mockMvc.perform(get("/api/customers/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", equalTo(FIRST_NAME)));
    }

    @Test
    public void testCreateCustomer() throws Exception {

        //given
        CustomerDTO customer = new CustomerDTO();
        customer.setFirstName(FIRST_NAME);
        customer.setLastName(LAST_NAME);

        CustomerDTO savedCustomer = new CustomerDTO();
        savedCustomer.setFirstName(FIRST_NAME);
        savedCustomer.setLastName(LAST_NAME);
        savedCustomer.setCustomerUrl(URL + ID);

        when(customerService.createNewCustomer(any())).thenReturn(savedCustomer);

        //when
        mockMvc.perform(post("/api/customers/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(customer)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.firstName", equalTo(FIRST_NAME)))
                .andExpect(jsonPath("$.customerUrl", equalTo(URL + ID)));
    }
}