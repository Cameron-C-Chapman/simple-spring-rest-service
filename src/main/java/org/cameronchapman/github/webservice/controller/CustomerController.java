package org.cameronchapman.github.webservice.controller;

import org.cameronchapman.github.webservice.model.Customer;
import org.cameronchapman.github.webservice.org.cameronchapman.github.webservice.manager.CustomerManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class CustomerController {

    private static Logger LOGGER = LoggerFactory.getLogger(CustomerController.class);
    private static String SUCCESS = "SUCCESS";
    private static String FAILED = "FAILED";

    @Autowired
    CustomerManager customerManager;

    @RequestMapping(value="/customers", method= RequestMethod.GET)
    public ResponseEntity<List<Customer>> getAll() {
        List<Customer> customers = null;
        HttpStatus httpStatus = null;
        String responseMsg = null;
        try {
            customers = customerManager.getAll();
            httpStatus = HttpStatus.OK;
            responseMsg = SUCCESS;
        } catch(Exception e) {
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
            responseMsg = FAILED;
            LOGGER.error("Exception calling getAll! msg={}", e.getMessage());
        } finally {
            LOGGER.info("{} | {}", responseMsg, httpStatus.toString());
        }

        return new ResponseEntity<List<Customer>>(customers, httpStatus);
    }

    @RequestMapping(value="/customers/{id}", method=RequestMethod.GET)
    public ResponseEntity<Customer> getById(@PathVariable Long id) {
        Customer customer = null;
        HttpStatus httpStatus = null;
        String responseMsg = null;
        try {
            customer = customerManager.getCustomerById(id);
            httpStatus = HttpStatus.OK;
            responseMsg = SUCCESS;
        } catch(Exception e) {
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
            responseMsg = FAILED;
            LOGGER.error("Exception calling getById! msg={}", e.getMessage());
        } finally {
            LOGGER.info("{} | {}", responseMsg, httpStatus.toString());
        }

        return new ResponseEntity<Customer>(customer, HttpStatus.OK);
    }

    @RequestMapping(value="customers", method=RequestMethod.POST)
    public ResponseEntity<Map<String, Number>> insert(@RequestBody Customer customer) {
        Number newId = null;
        Map<String, Number> response = new HashMap<String, Number>(1);
        HttpStatus httpStatus = null;
        String responseMsg = null;
        try {
            newId = customerManager.insertCustomer(customer);
            response.put("id", newId);
            httpStatus = HttpStatus.OK;
            responseMsg = SUCCESS;
        } catch(Exception e) {
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
            responseMsg = FAILED;
            LOGGER.error("Exception calling insert! msg={}", e.getMessage());
        } finally {
            LOGGER.info("{} | {}", responseMsg, httpStatus.toString());
        }

        return new ResponseEntity<Map<String, Number>>(response, HttpStatus.CREATED);
    }

}