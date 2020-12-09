package com.lambdaschool.javaorders.services;

import com.lambdaschool.javaorders.models.Order;
import com.lambdaschool.javaorders.repositories.OrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service(value = "orderService")
public class OrderServicesImpl implements OrderServices
{
    @Autowired
    private OrdersRepository orderrepos;

    @Override
    public Order findById(long id)
    {
        Order o = orderrepos.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Order " + id + " Not Found!"));
        return o;
    }

    @Override
    public List<Order> findByAdvanceamountGreaterThan(double number)
    {
        List<Order> rtnList = orderrepos.findByAdvanceamountGreaterThan(number);
        return rtnList;
    }

}
