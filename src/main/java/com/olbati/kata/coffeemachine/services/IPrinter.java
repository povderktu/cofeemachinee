package com.olbati.kata.coffeemachine.services;

import com.olbati.kata.coffeemachine.dto.DrinkSoldDetailsDto;

import java.util.List;

/**
 * @author Ahmed Jerid <ahmed.jerid@olbati.com>
 *         Date: 07/08/2017
 */
public interface IPrinter {

    void print(List<DrinkSoldDetailsDto> drinkSoldDetailsDtoList);
}
