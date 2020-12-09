package com.lambdaschool.javaorders.services;

import com.lambdaschool.javaorders.models.Customer;
import com.lambdaschool.javaorders.repositories.CustomersRepository;
import com.lambdaschool.javaorders.views.OrderCounts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service(value = "customerService")
public class CustomerServicesImpl implements CustomerServices {
    @Autowired
    private CustomersRepository custrepos;

    @Override
    public List<Customer> findAllCustomers()
    {
        List<Customer> rtnList = new ArrayList<>();
        custrepos.findAll().iterator().forEachRemaining(rtnList::add);
        return rtnList;
    }

    @Override
    public Customer findById(long id)
    {
        Customer c = custrepos.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Customer " + id + " Not Found!"));
        return c;
    }

    @Override
    public List<Customer> findByNameLike(String substring)
    {
        List<Customer> rtnList = custrepos.findByCustnameContainingIgnoreCase(substring);
        return rtnList;
    }

    @Override
    public List<OrderCounts> findOrderCounts()
    {
        List<OrderCounts> rtnList = custrepos.findOrderCounts();
        return rtnList;
    }
}
