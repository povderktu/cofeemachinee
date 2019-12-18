package com.olbati.kata.coffeemachine.dom.products;

import java.util.Objects;

/**
 * @author Ahmed Jerid <ahmed.jerid@olbati.com>
 *         Date: 04/08/2017
 */
public abstract class Product implements IProduct {


    @Override
    public boolean equals(Object o) {

        if (o == this) return true;
        if (!(o instanceof IProduct)) {
            return false;
        }
        IProduct product = (IProduct) o;
        return this.getIdentifier() == product.getIdentifier();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIdentifier());
    }


    @Override
    public String toString() {
        return "Product{ code: " + getCode() +
                ", identifier: " + getIdentifier() +
                " }";
    }

}
