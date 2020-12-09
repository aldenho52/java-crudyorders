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

    Customer save(Customer customer); // POST
    Customer update(Customer customer, long id);

    void delete(long id);
}
