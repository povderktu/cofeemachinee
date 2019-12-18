package com.olbati.kata.coffeemachine.services;

import com.olbati.kata.coffeemachine.dto.DrinkSoldDetailsDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @author Ahmed Jerid <ahmed.jerid@olbati.com>
 *         Date: 07/08/2017
 */
public class ConsolePrinter implements IPrinter {

    private final Logger logger = LoggerFactory.getLogger(ConsolePrinter.class);

    @Override
    public void print(List<DrinkSoldDetailsDto> drinkSoldDetailsDtoList) {
        drinkSoldDetailsDtoList.forEach((item) -> logger.info(item.toString()));
    }
}
