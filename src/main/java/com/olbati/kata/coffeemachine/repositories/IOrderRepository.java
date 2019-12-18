package com.olbati.kata.coffeemachine.repositories;

import com.olbati.kata.coffeemachine.dom.order.Order;
import com.olbati.kata.coffeemachine.dom.products.IProduct;

import java.util.List;

/**
 * @author Ahmed Jerid <ahmed.jerid@olbati.com>
 *         Date: 04/08/2017
 */
public interface IOrderRepository {

    void save(Order order);

    List<Order> findAll();

    List<Order> findOrdersByProduct(IProduct product);

    List<IProduct> findProductTypes();
}
