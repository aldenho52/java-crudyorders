package com.lambdaschool.javaorders.controllers;


import com.lambdaschool.javaorders.models.Order;
import com.lambdaschool.javaorders.services.OrderServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrdersController
{
    @Autowired
    private OrderServices orderServices;


    //    http://localhost:2019/orders/order/7
    @GetMapping(value = "/order/{orderid}", produces = "application/json")
    public ResponseEntity<?> findOrderById(@PathVariable long orderid) {
        Order order  = orderServices.findById(orderid);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    //    http://localhost:2019/orders/advanceamount/{amount}
    @GetMapping(value="advanceamount/{amount}", produces = "application/json")
    public ResponseEntity<?> findOrdersWithAdvanceAmount(@PathVariable double amount)
    {
        List<Order> myList = orderServices.findByAdvanceamountGreaterThan(amount);
        return new ResponseEntity<>(myList, HttpStatus.OK);
    }


    //    POST http://localhost:2019/orders/order
    //    PUT http://localhost:2019/orders/order/63
    //    DELETE http://localhost:2019/orders/order/58
    @DeleteMapping(value="/order/{ordnum}")
    public ResponseEntity<?> deleteOrderById(@PathVariable long ordnum)
    {
        orderServices.delete(ordnum);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
