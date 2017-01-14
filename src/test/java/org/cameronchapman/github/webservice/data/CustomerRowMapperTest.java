package org.cameronchapman.github.webservice.data;

import static org.junit.Assert.assertEquals;

import java.sql.ResultSet;

import org.cameronchapman.github.webservice.model.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CustomerRowMapperTest {
    
	@Test
<<<<<<< HEAD
	public void mapRowTest() throws Exception {
		CustomerRowMapper customerRowMapper = new CustomerRowMapper();
		ResultSet rs = Mockito.mock(ResultSet.class);
		Long id = new Long(1);
=======
	public void customerRowMapperTest() throws Exception {
		CustomerRowMapper customerRowMapper = new CustomerRowMapper();
		Long id = new Long(1);
		ResultSet rs = Mockito.mock(ResultSet.class);
>>>>>>> Removing snapshot, adding untracked directory.
		Mockito.when(rs.getLong("id")).thenReturn(id);
		Mockito.when(rs.getString("name")).thenReturn("Customer Name");
		Mockito.when(rs.getString("address1")).thenReturn("Address 1");
		Mockito.when(rs.getString("address2")).thenReturn("Address 2");
		Mockito.when(rs.getString("city")).thenReturn("City");
		Mockito.when(rs.getString("state")).thenReturn("State");
		Mockito.when(rs.getString("zip")).thenReturn("Zip");
<<<<<<< HEAD
		Customer customer = customerRowMapper.mapRow(rs, 1);
=======
		
		Customer customer = customerRowMapper.mapRow(rs, 1);
		
>>>>>>> Removing snapshot, adding untracked directory.
		assertEquals(customer.getId(), id);
		assertEquals(customer.getName(), "Customer Name");
		assertEquals(customer.getAddress1(), "Address 1");
		assertEquals(customer.getAddress2(), "Address 2");
		assertEquals(customer.getCity(), "City");
		assertEquals(customer.getState(), "State");
		assertEquals(customer.getZip(), "Zip");
	}
<<<<<<< HEAD
}
=======
	
}
>>>>>>> Removing snapshot, adding untracked directory.
