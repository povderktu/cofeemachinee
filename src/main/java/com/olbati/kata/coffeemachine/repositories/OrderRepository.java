package com.olbati.kata.coffeemachine.repositories;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.olbati.kata.coffeemachine.dom.order.Order;
import com.olbati.kata.coffeemachine.dom.products.IProduct;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ahmed Jerid <ahmed.jerid@olbati.com>
 *         Date: 04/08/2017
 */
public class OrderRepository implements IOrderRepository {

    private Multimap<IProduct, Order> orderMap;

    public OrderRepository() {
        this.orderMap = ArrayListMultimap.create();
    }

    public OrderRepository(Multimap<IProduct, Order> orderMap) {
        this.orderMap = orderMap;
    }


    public void save(Order order) {
        this.orderMap.put(order.product, order);
    }

    public List<Order> findAll() {
        return new ArrayList<>(orderMap.values());
    }

    public List<Order> findOrdersByProduct(IProduct product) {
        return new ArrayList<>(orderMap.get(product));
    }

    @Override
    public List<IProduct> findProductTypes() {
        return new ArrayList<>(orderMap.keySet());
    }
}
