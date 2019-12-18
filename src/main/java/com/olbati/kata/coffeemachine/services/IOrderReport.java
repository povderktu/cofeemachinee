package com.olbati.kata.coffeemachine.services;

import com.olbati.kata.coffeemachine.dto.DrinkSoldDetailsDto;
import com.olbati.kata.coffeemachine.dom.products.IProduct;

import java.util.List;

/**
 * @author Ahmed Jerid <ahmed.jerid@olbati.com>
 *         Date: 04/08/2017
 */
public interface IOrderReport {


    DrinkSoldDetailsDto getReportDetails(IProduct p);

    List<DrinkSoldDetailsDto> getReportDetails();

}
