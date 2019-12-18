package com.olbati.kata.coffeemachine.dom.products;

import com.olbati.kata.coffeemachine.dom.products.decorators.IHotProduct;
import com.olbati.kata.coffeemachine.dom.products.decorators.IMilkProduct;

import java.math.BigDecimal;

/**
 * @author Ahmed Jerid <ahmed.jerid@olbati.com>
 *         Date: 03/08/2017
 */
public class Chocolate extends Product implements IHotProduct, IMilkProduct {
    @Override
    public String getCode() {
        return "H";
    }

    @Override
    public String getIdentifier() {
        return this.getCode();
    }

    @Override
    public BigDecimal getPrice() {
        return new BigDecimal(0.5).setScale(3, BigDecimal.ROUND_HALF_UP);
    }
}
