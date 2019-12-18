package com.olbati.kata.coffeemachine.dom.products;

import com.olbati.kata.coffeemachine.dom.products.decorators.IHotProduct;

import java.math.BigDecimal;

/**
 * @author Ahmed Jerid <ahmed.jerid@olbati.com>
 *         Date: 03/08/2017
 */
public class Tea extends Product implements IHotProduct {
    @Override
    public String getCode() {
        return "T";
    }

    @Override
    public String getIdentifier() {
        return this.getCode();
    }

    @Override
    public BigDecimal getPrice() {
        return new BigDecimal(0.4).setScale(3, BigDecimal.ROUND_HALF_UP);
    }
}
