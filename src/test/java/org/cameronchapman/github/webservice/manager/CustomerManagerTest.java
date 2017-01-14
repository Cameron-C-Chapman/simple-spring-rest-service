package org.cameronchapman.github.webservice.manager;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Matchers.any;

import java.util.ArrayList;
import java.util.List;

import org.cameronchapman.github.webservice.data.CustomerDao;
import org.cameronchapman.github.webservice.exception.NoNewCustomerIdReturnedException;
import org.cameronchapman.github.webservice.model.Customer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CustomerManagerTest {

    @Mock
    CustomerDao mockCustomerDao;

    @InjectMocks
    CustomerManager customerManger;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getAllTest() throws Exception {
        Mockito.when(mockCustomerDao.getAll()).thenReturn(new ArrayList<Customer>());
        List<Customer> customers = customerManger.getAll();
        assertNotNull(customers);
    }
    
    // @TODO add timeout test

    @Test
    public void getAllReturnsNullConvertsToEmptyListTest() throws Exception {
        Mockito.when(mockCustomerDao.getAll()).thenReturn(null);
        List<Customer> customers = customerManger.getAll();
        assertNotNull(customers);
    }

    @Test
    public void getCustomerByIdTest() throws Exception {
        Mockito.when(mockCustomerDao.getById(any(Long.class))).thenReturn(new Customer());
        Customer customer = customerManger.getCustomerById(new Long(1));
        assertNotNull(customer);
    }

    @Test
    public void getCustomerByIdNullResultIsValidTest() throws Exception {
        Mockito.when(mockCustomerDao.getById(any(Long.class))).thenReturn(null);
        Customer customer = customerManger.getCustomerById(new Long(1));
        assertNull(customer);
    }

    @Test
    public void insertCustomerTest() throws Exception {
        Mockito.when(mockCustomerDao.insert(any(Customer.class))).thenReturn(99);
        Number newId = customerManger.insertCustomer(new Customer());
        assertEquals(newId, 99);
    }

    @Test(expected = NoNewCustomerIdReturnedException.class)
    public void insertCustomerNullCustomerThrowsCorrectExceptionTest() throws Exception {
        Mockito.when(mockCustomerDao.insert(any(Customer.class))).thenReturn(null);
        customerManger.insertCustomer(new Customer());
    }

}