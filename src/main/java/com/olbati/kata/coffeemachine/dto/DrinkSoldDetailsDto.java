package com.olbati.kata.coffeemachine.dto;

import com.olbati.kata.coffeemachine.dom.products.IProduct;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * @author Ahmed Jerid <ahmed.jerid@olbati.com>
 *         Date: 04/08/2017
 */
public class DrinkSoldDetailsDto {
    public IProduct product;
    public int quantity;
    public BigDecimal amount;

    public DrinkSoldDetailsDto(IProduct product, int quantity, BigDecimal amount) {
        this.product = product;
        this.quantity = quantity;
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {

        if (o == this) return true;
        if (!(o instanceof DrinkSoldDetailsDto)) {
            return false;
        }
        DrinkSoldDetailsDto dto = (DrinkSoldDetailsDto) o;
        boolean sameIdentifier = this.product.getIdentifier() == dto.product.getIdentifier();
        boolean sameAmount = this.amount.equals(dto.amount);
        boolean sameQuantity = this.quantity == dto.quantity;

        return sameAmount && sameIdentifier && sameQuantity;

    }

    @Override
    public String toString() {
        return "DrinkSoldDetailsDto{" +
                "product=" + product +
                ", quantity=" + quantity +
                ", amount=" + amount +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(product.getIdentifier());
    }
}
