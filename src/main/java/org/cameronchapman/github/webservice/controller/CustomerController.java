package org.cameronchapman.github.webservice.controller;

import org.cameronchapman.github.webservice.data.CustomerDao;
import org.cameronchapman.github.webservice.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CustomerController {

    @Autowired
    CustomerDao customerDao;

    @RequestMapping("/customers")
    public ResponseEntity<List<Customer>> getAll() {
        List<Customer> customers = customerDao.getAll();
        return new ResponseEntity<List<Customer>>(customers, HttpStatus.ACCEPTED);
    }

}
