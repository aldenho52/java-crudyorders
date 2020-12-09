package com.lambdaschool.javaorders.repositories;

import com.lambdaschool.javaorders.models.Customer;
import com.lambdaschool.javaorders.views.OrderCounts;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CustomersRepository extends CrudRepository<Customer, Long>
{
    List<Customer> findByCustnameContainingIgnoreCase(String substring);

    @Query(value = "select c.custname name, count(o.ordnum) countorders " +
        "FROM customers c " +
        "LEFT JOIN orders o " +
        "ON c.custcode = o.custcode " +
        "GROUP BY name " +
        "ORDER BY countorders DESC", nativeQuery = true)
    List<OrderCounts> findOrderCounts();
}
