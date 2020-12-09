package com.lambdaschool.javaorders.controllers;


import com.lambdaschool.javaorders.models.Customer;
import com.lambdaschool.javaorders.services.CustomerServices;
import com.lambdaschool.javaorders.views.OrderCounts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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



}
