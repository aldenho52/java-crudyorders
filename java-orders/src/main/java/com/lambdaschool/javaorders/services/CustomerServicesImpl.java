package com.lambdaschool.javaorders.services;

import com.lambdaschool.javaorders.models.Customer;
import com.lambdaschool.javaorders.models.Order;
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

    @Transactional
    @Override
    public Customer save(Customer customer)
    {
        Customer newCustomer = new Customer();

        if (customer.getCustcode() != 0) {
            custrepos.findById(customer.getCustcode())
                .orElseThrow(() -> new EntityNotFoundException("Customer " + customer.getCustcode() + " Not Found"));
            newCustomer.setCustcode(customer.getCustcode());
        }

        newCustomer.setCustname(customer.getCustname());
        newCustomer.setCustcity(customer.getCustcity());
        newCustomer.setWorkingarea(customer.getWorkingarea());
        newCustomer.setCustcountry(customer.getCustcountry());
        newCustomer.setGrade(customer.getGrade());
        newCustomer.setOpeningamt(customer.getOpeningamt());
        newCustomer.setReceiveamt(customer.getReceiveamt());
        newCustomer.setPaymentamt(customer.getPaymentamt());
        newCustomer.setOutstandingamt(customer.getOutstandingamt());
        newCustomer.setPhone(customer.getPhone());
        newCustomer.setAgent(customer.getAgent());

        newCustomer.getOrders().clear();
        for (Order o : customer.getOrders())
        {
            Order newOrder = new Order();
            newOrder.setOrdamount(o.getOrdamount());
            newOrder.setAdvanceamount(o.getAdvanceamount());
            newOrder.setOrderdescription(o.getOrderdescription());
            newOrder.setPayments(o.getPayments());

            newOrder.setCustomer(newCustomer);
            newCustomer.getOrders().add(newOrder);
        }

        return custrepos.save(newCustomer);
    }

    @Transactional
    @Override
    public Customer update(Customer customer, long id)
    {
        Customer updateCustomer = custrepos.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Customer " + id + " Not Found"));



        if(customer.getCustname() != null) updateCustomer.setCustname(customer.getCustname());
        if(customer.getCustcity() != null) updateCustomer.setCustcity(customer.getCustcity());
        if(customer.getWorkingarea() != null) updateCustomer.setWorkingarea(customer.getWorkingarea());
        if(customer.getCustcountry() != null) updateCustomer.setCustcountry(customer.getCustcountry());
        if(customer.getGrade() != null) updateCustomer.setGrade(customer.getGrade());
        if(customer.getPhone() != null) updateCustomer.setPhone(customer.getPhone());
        if(customer.getAgent() != null) updateCustomer.setAgent(customer.getAgent());
        if(customer.hasValueForOpeningAmt) updateCustomer.setOpeningamt(customer.getOpeningamt());
        if(customer.hasValueForReceiveAmt) updateCustomer.setReceiveamt(customer.getReceiveamt());
        if(customer.hasValueForPaymentAmt) updateCustomer.setPaymentamt(customer.getPaymentamt());
        if(customer.hasValueForOutstandingAmt) updateCustomer.setOutstandingamt(customer.getOutstandingamt());

        if (customer.getOrders().size() > 0) {
            updateCustomer.getOrders().clear();
            for (Order o : customer.getOrders())
            {
                Order newOrder = new Order();
                newOrder.setOrdamount(o.getOrdamount());
                newOrder.setAdvanceamount(o.getAdvanceamount());
                newOrder.setOrderdescription(o.getOrderdescription());
                newOrder.setPayments(o.getPayments());

                newOrder.setCustomer(updateCustomer);
                updateCustomer.getOrders().add(newOrder);
            }
        }

        return custrepos.save(updateCustomer);
    }

    @Transactional
    @Override
    public void delete(long id)
    {
        custrepos.deleteById(id);
    }
}
