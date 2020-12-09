package com.lambdaschool.javaorders.controllers;


import com.lambdaschool.javaorders.models.Customer;
import com.lambdaschool.javaorders.services.CustomerServices;
import com.lambdaschool.javaorders.views.OrderCounts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomersController
{
    @Autowired
    private CustomerServices customerServices;

    //    http://localhost:2019/customers/orders
    @GetMapping(value="/orders", produces = "application/json")
    public ResponseEntity<?> listAllCustomersWithOrders() {
        List<Customer> myList = customerServices.findAllCustomers();
        return new ResponseEntity<>(myList, HttpStatus.OK);
    }

    //    http://localhost:2019/customers/customer/7
    @GetMapping(value="/customer/{custid}", produces = "application/json")
    public ResponseEntity<?> findCustomerById(@PathVariable long custid)
    {
        Customer customer = customerServices.findById(custid);
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    //    http://localhost:2019/customers/namelike/mes
    @GetMapping(value = "/namelike/{substring}", produces = "application/json")
    public ResponseEntity<?> findCustomerByNameLike(@PathVariable String substring)
    {
        List<Customer> rtnList = customerServices.findByNameLike(substring);
        return new ResponseEntity<>(rtnList, HttpStatus.OK);
    }

    //    http://localhost:2019/customers/orders/count
    @GetMapping(value = "/orders/count", produces = "application/json")
    public ResponseEntity<?> findOrderCounts()
    {
        List<OrderCounts> rtnList = customerServices.findOrderCounts();
        return new ResponseEntity<>(rtnList, HttpStatus.OK);
    }

    //    POST http://localhost:2019/customers/customer
    @PostMapping(value = "/customer", consumes = "application/json")
    public ResponseEntity<?> addCustomer(@Valid @RequestBody Customer newCustomer)
    {
        newCustomer.setCustcode(0);
        newCustomer = customerServices.save(newCustomer);

        HttpHeaders responseHeaders = new HttpHeaders();
        URI newCustomerURI = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{restid}")
            .buildAndExpand(newCustomer.getCustcode())
            .toUri();
        responseHeaders.setLocation(newCustomerURI);
        return new ResponseEntity<>(responseHeaders, HttpStatus.CREATED);
    }

    //    PUT http://localhost:2019/customers/customer/19
    @PutMapping(value = "/customer/{custcode}", consumes = "application/json")
    public ResponseEntity<?> replaceCustomerById(@PathVariable long custcode, @Valid @RequestBody Customer replaceCustomer)
    {
        replaceCustomer.setCustcode(custcode);
        Customer c = customerServices.save(replaceCustomer);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //    PATCH http://localhost:2019/customers/customer/19
    @PatchMapping(value = "/customer/{custcode}", consumes = "application/json")
    public ResponseEntity<?> updateCustomerById(@PathVariable long custcode, @RequestBody Customer updateCustomer)
    {
        customerServices.update(updateCustomer, custcode);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //    DELETE http://localhost:2019/customers/customer/54
    //    POST http://localhost:2019/orders/order
    //    PUT http://localhost:2019/orders/order/63
    //    DELETE http://localhost:2019/orders/order/58




}
