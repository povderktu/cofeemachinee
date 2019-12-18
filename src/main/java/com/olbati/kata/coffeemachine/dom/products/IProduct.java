package com.olbati.kata.coffeemachine.dom.products;

import java.math.BigDecimal;

/**
 * @author Ahmed Jerid <ahmed.jerid@olbati.com>
 *         Date: 03/08/2017
 */
public interface IProduct {

    String getCode();

    String getIdentifier();

    BigDecimal getPrice();

   
}
