package com.olbati.kata.coffeemachine.dom.products;

import java.math.BigDecimal;

/**
 * @author Ahmed Jerid <ahmed.jerid@olbati.com>
 *         Date: 03/08/2017
 */
public class Orange extends Product implements IProduct {
    @Override
    public String getCode() {
        return "O";
    }

    @Override
    public String getIdentifier() {
        return this.getCode();
    }

    @Override
    public BigDecimal getPrice() {
        return new BigDecimal(0.8).setScale(3, BigDecimal.ROUND_HALF_UP);
    }
}
