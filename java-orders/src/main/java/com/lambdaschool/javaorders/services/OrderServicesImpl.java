package com.lambdaschool.javaorders.services;

import com.lambdaschool.javaorders.models.Order;
import com.lambdaschool.javaorders.models.Payment;
import com.lambdaschool.javaorders.repositories.OrdersRepository;
import com.lambdaschool.javaorders.repositories.PaymentRepository;
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

    @Autowired
    private PaymentRepository payrepos;

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

    @Transactional
    @Override
    public void delete(long id)
    {
        orderrepos.deleteById(id);
    }

    @Transactional
    @Override
    public Order save(Order order)
    {
        Order newOrder = new Order();

        if(order.getOrdnum() != 0) {
            orderrepos.findById(order.getOrdnum())
                .orElseThrow(() -> new EntityNotFoundException("Order " + order.getOrdnum() + " Not Found"));
            newOrder.setOrdnum(order.getOrdnum());
        }

        newOrder.setOrdamount(order.getOrdamount());
        newOrder.setAdvanceamount(order.getAdvanceamount());
        newOrder.setOrderdescription(order.getOrderdescription());
        newOrder.setCustomer(order.getCustomer());

        newOrder.getPayments().clear();
        for (Payment p : order.getPayments())
        {
            Payment newPayment = payrepos.findById(p.getPaymentid())
                .orElseThrow(() -> new EntityNotFoundException("Payment " + p.getPaymentid() + " Not Found"));
            newOrder.getPayments().add(newPayment);
        }

        return orderrepos.save(newOrder);
    }
}
