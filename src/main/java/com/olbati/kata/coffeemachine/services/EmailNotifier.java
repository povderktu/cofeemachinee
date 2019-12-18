package com.olbati.kata.coffeemachine.services;

/**
 * @author Ahmed Jerid <ahmed.jerid@olbati.com>
 *         Date: 07/08/2017
 */
public interface EmailNotifier {
    
    void notifyMissingDrink(String drink);

}
