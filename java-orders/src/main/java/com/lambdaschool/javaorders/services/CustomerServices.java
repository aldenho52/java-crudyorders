package com.lambdaschool.javaorders.services;

import com.lambdaschool.javaorders.models.Customer;
import com.lambdaschool.javaorders.views.OrderCounts;

import java.util.List;

public interface CustomerServices
{
    List<Customer> findAllCustomers();
    Customer findById(long id);
    List<Customer> findByNameLike(String substring);

    List<OrderCounts> findOrderCounts();
}
