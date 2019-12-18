package com.olbati.kata.coffeemachine.dom.order;

import com.olbati.kata.coffeemachine.dom.products.IProduct;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author Ahmed Jerid <ahmed.jerid@olbati.com>
 *         Date: 01/08/2017
 */
public class Order {

    public final IProduct product;
    final int sugarQuantity;
    public final BigDecimal amountMoney;
    public LocalDateTime createdDate;

    public Order(int sugarQuantity, BigDecimal amountMoney, IProduct product) {
        this.amountMoney = amountMoney;
        this.sugarQuantity = sugarQuantity;
        this.product = product;
        this.createdDate = LocalDateTime.now();
    }


    public int getSugarQuantity() {
        return sugarQuantity;
    }

    public BigDecimal getAmountMoney() {
        return amountMoney;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }
}
