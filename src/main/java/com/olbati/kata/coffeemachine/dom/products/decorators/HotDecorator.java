package com.olbati.kata.coffeemachine.dom.products.decorators;

import com.olbati.kata.coffeemachine.dom.products.Product;

import java.math.BigDecimal;

/**
 * @author Ahmed Jerid <ahmed.jerid@olbati.com>
 *         Date: 03/08/2017
 */
public class HotDecorator extends Product implements IMilkProduct, IHotProduct {

    IHotProduct product;

    public HotDecorator(IHotProduct product) {
        this.product = product;
    }

    @Override
    public String getCode() {
        return product.getCode() + "h";
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
