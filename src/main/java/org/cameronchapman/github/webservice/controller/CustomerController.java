package org.cameronchapman.github.webservice.controller;

import org.cameronchapman.github.webservice.data.CustomerDao;
import org.cameronchapman.github.webservice.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class CustomerController {

    @Autowired
    CustomerDao customerDao;

    @RequestMapping(value="/customers", method= RequestMethod.GET)
    public ResponseEntity<List<Customer>> getAll() {
        List<Customer> customers = customerDao.getAll();
        return new ResponseEntity<List<Customer>>(customers, HttpStatus.ACCEPTED);
    }

    @RequestMapping(value="/customers/{id}", method=RequestMethod.GET)
    public ResponseEntity<Customer> getById(@PathVariable Long id) {
        Customer customer = customerDao.getById(id);
        return new ResponseEntity<Customer>(customer, HttpStatus.OK);
    }

    @RequestMapping(value="customers", method=RequestMethod.POST)
    public ResponseEntity<Map<String, Number>> insert(@RequestBody Customer customer) {
        Number newId = customerDao.insert(customer);
        Map<String, Number> response = new HashMap<String, Number>(1);
        response.put("id", newId);
        return new ResponseEntity<Map<String, Number>>(response, HttpStatus.CREATED);
    }

}