package org.cameronchapman.github.webservice.org.cameronchapman.github.webservice.manager;

import org.cameronchapman.github.webservice.data.CustomerDao;
import org.cameronchapman.github.webservice.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional(timeout=5)
public class CustomerManager {

    @Autowired
    private CustomerDao customerDao;

    @Transactional(readOnly = true)
    public List<Customer> getAll() {
        return customerDao.getAll();
    }

    @Transactional(readOnly = true)
    public Customer getCustomerById(Long id) {
        return customerDao.getById(id);
    }

    public Number insertCustomer(Customer customer) {
        return customerDao.insert(customer);
    }

}