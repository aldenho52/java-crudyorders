package com.lambdaschool.javaorders.services;

import com.lambdaschool.javaorders.models.Order;

import java.util.List;

public interface OrderServices
{
    Order findById(long id);
    List<Order> findByAdvanceamountGreaterThan(double number);
}
