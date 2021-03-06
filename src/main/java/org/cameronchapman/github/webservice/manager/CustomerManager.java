package org.cameronchapman.github.webservice.manager;

import java.util.ArrayList;
import java.util.List;

import org.cameronchapman.github.webservice.data.CustomerDao;
import org.cameronchapman.github.webservice.exception.NoNewCustomerIdReturnedException;
import org.cameronchapman.github.webservice.model.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional(timeout = 5, readOnly = true)
public class CustomerManager {

    private static Logger LOGGER = LoggerFactory.getLogger(CustomerManager.class);

    @Autowired
    private CustomerDao customerDao;

    public List<Customer> getAll() {
        List<Customer> customers = customerDao.getAll();
        if (null == customers) {
            customers = new ArrayList<Customer>();
        }
        return customers;
    }

    public Customer getCustomerById(Long id) {
        Customer customer = null;
        try {
            customer = customerDao.getById(id);
        } catch(DataAccessException dae) {
            // if no data is returned return null to caller
            LOGGER.debug("No data returned from getCustomerById. id={}", id);
        }
        return customer;
    }

    @Transactional(readOnly = false)
    public Number insertCustomer(Customer customer) throws Exception {
        Number newId = customerDao.insert(customer, new GeneratedKeyHolder());
        // if no new id was generated from the insert attempt throw an exception
        if (null == newId) {
            throw new NoNewCustomerIdReturnedException("No new customer id returned.");
        }
        return newId;
    }

}