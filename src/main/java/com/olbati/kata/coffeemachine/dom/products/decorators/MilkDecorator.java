package com.olbati.kata.coffeemachine.dom.products.decorators;

import com.olbati.kata.coffeemachine.dom.products.Product;

import java.math.BigDecimal;

/**
 * @author Ahmed Jerid <ahmed.jerid@olbati.com>
 *         Date: 03/08/2017
 */

public class MilkDecorator extends Product implements IMilkProduct, IHotProduct {

    private IMilkProduct product;

    public MilkDecorator(IMilkProduct milkProduct) {
        this.product = milkProduct;
    }

    @Override
    public String getCode() {
        return product.getCode() + "m";
    }

    @Override
    public BigDecimal getPrice() {
        return product.getPrice();
    }

    @Override
    public String getIdentifier() {
        return product.getCode();
    }
}
