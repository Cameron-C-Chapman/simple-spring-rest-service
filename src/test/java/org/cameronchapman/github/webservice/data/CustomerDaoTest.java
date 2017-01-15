package org.cameronchapman.github.webservice.data;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.cameronchapman.github.webservice.model.Customer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

@RunWith(MockitoJUnitRunner.class)
public class CustomerDaoTest {

	@Mock
    JdbcTemplate jdbcTemplate;

    @Mock
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Mock
    CustomerRowMapper customerRowMapper;
    
    @InjectMocks
    CustomerDao customerDao;
    
    @Before
    public void setup() {
    	MockitoAnnotations.initMocks(this);
    }
    
    @Test
    public void getAllTest() throws Exception {
    	List<Customer> customers = new ArrayList<Customer>();
    	customers.add(new Customer());
    	Mockito.when(jdbcTemplate.query(any(String.class), any(CustomerRowMapper.class))).thenReturn(customers);
    	List<Customer> customersReturned = customerDao.getAll();
    	assertEquals(customers, customersReturned);
    }
    
    @Test
    public void getByIdTest() throws Exception {
    	Long customerId = new Long(1);
    	Customer customer = new Customer();
    	customer.setId(customerId);
    	customer.setName("Customer Name");
    	Mockito.when(namedParameterJdbcTemplate.queryForObject(any(String.class), Matchers.<Map<String, Object>>any(), any(CustomerRowMapper.class))).thenReturn(customer);
    	Customer customerReturned = customerDao.getById(customerId);
    	assertEquals(customer, customerReturned);
    }
    
    @Test
    public void insertTest() throws Exception {
    	GeneratedKeyHolder keyHolder = Mockito.mock(GeneratedKeyHolder.class);
    	Customer customer = new Customer();
    	Mockito.when(namedParameterJdbcTemplate.update(any(String.class), any(MapSqlParameterSource.class), any(KeyHolder.class))).thenReturn(99);
    	Mockito.when(keyHolder.getKey()).thenReturn(99);
    	Number insertedId = customerDao.insert(customer, keyHolder);
    	System.out.println(insertedId);
    	assertEquals(insertedId, 99);
    }
    
}
