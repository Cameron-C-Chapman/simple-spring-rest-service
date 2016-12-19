package org.cameronchapman.github.webservice.controller;

import org.cameronchapman.github.webservice.model.Customer;
import org.cameronchapman.github.webservice.org.cameronchapman.github.webservice.manager.CustomerManager;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class CustomerControllerTest {

    @Mock
    CustomerManager mockCustomerManager;

    @InjectMocks
    CustomerController customerController;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();
    }

    @Test
    public void getAllTest() throws Exception {
        List<Customer> customers = new ArrayList<Customer>();
        Customer customer1 = new Customer();
        Customer customer2 = new Customer();
        customers.add(customer1);
        customers.add(customer2);
        Mockito.when(mockCustomerManager.getAll()).thenReturn(customers);
        mockMvc.perform(get("/api/customers")).andExpect(status().isOk());
    }

    @Test
    public void getAllExceptionFromManagerReturns5xxTest() throws Exception {
        Mockito.when(mockCustomerManager.getAll()).thenThrow(Exception.class);
        mockMvc.perform(get("/api/customers")).andExpect(status().is5xxServerError());
    }

    @Test
    public void getByIdTest() throws Exception {
        Mockito.when(mockCustomerManager.getCustomerById(any(Long.class))).thenReturn(new Customer());
        mockMvc.perform(get("/api/customers/1")).andExpect(status().isOk());
    }

    @Test
    public void getByIdNullFromManagerReturnsNoContentTest() throws Exception {
        Mockito.when(mockCustomerManager.getCustomerById(any(Long.class))).thenReturn(null);
        mockMvc.perform(get("/api/customers/99")).andExpect(status().isNoContent());
    }

    @Test
    public void getByIdExceptionFromManagerReturns5xxTest() throws Exception {
        Mockito.when(mockCustomerManager.getCustomerById(any(Long.class))).thenThrow(Exception.class);
        mockMvc.perform(get("/api/customers/1")).andExpect(status().is5xxServerError());
    }

    @Test
    public void insertTest() throws Exception {
        String newCustomer = "{\"name\":\"Testy McTest\",\"address1\":\"999 Main Ave\",\"address2\":\"Apt 9\",\"city\":\"Kansas City\",\"state\":\"MO\",\"zip\":\"64119\"}";
        Mockito.when(mockCustomerManager.insertCustomer(any(Customer.class))).thenReturn(99);
        mockMvc.perform(post("/api/customers").contentType(MediaType.APPLICATION_JSON).content(newCustomer)).andExpect(status().isCreated());
    }

    @Test
    public void insertExceptionFromManagerThrowsException() throws Exception {
        Mockito.when(mockCustomerManager.insertCustomer(any(Customer.class))).thenThrow(Exception.class);
        mockMvc.perform(post("/api/customers").contentType(MediaType.APPLICATION_JSON).content("{}")).andExpect(status().is5xxServerError());
    }

    @Test(expected = AssertionError.class)
    public void insertNullRequestBodyThrowsException() throws Exception {
        mockMvc.perform(post("/api/customers").contentType(MediaType.APPLICATION_JSON).content("")).andExpect(status().is5xxServerError());
    }

}