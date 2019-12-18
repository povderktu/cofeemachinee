package com.olbati.kata.coffeemachine;


import com.olbati.kata.coffeemachine.repositories.IOrderRepository;
import com.olbati.kata.coffeemachine.dom.order.Order;
import com.olbati.kata.coffeemachine.dom.products.Chocolate;
import com.olbati.kata.coffeemachine.dom.products.Coffee;
import com.olbati.kata.coffeemachine.dom.products.Orange;
import com.olbati.kata.coffeemachine.dom.products.Tea;
import com.olbati.kata.coffeemachine.dom.products.decorators.HotDecorator;
import com.olbati.kata.coffeemachine.services.BeverageQuantityChecker;
import com.olbati.kata.coffeemachine.services.EmailNotifier;
import com.olbati.kata.coffeemachine.services.ForewordMessageProcess;
import com.olbati.kata.coffeemachine.services.MachineTranslator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
public class MachineTranslatorTest {

    @InjectMocks
    private MachineTranslator machineTranslator;

    @Mock
    private ForewordMessageProcess forewordMessageProcess;
    @Mock
    private IOrderRepository orderRepository;
    @Mock
    private BeverageQuantityChecker beverageQuantityChecker;
    @Mock
    private EmailNotifier emailNotifier;

    @Test
    public void should_return_instruction_of_tea_when_order_is_tea() {
        //when
        Order order = new Order(0, new BigDecimal(1), new Tea());
        String instruction = machineTranslator.command(order);

        //then
        assertThat(instruction).isEqualTo("T::");
    }

    @Test
    public void should_return_instruction_of_coffee_when_order_is_coffee() {
        //when
        Order order = new Order(0, new BigDecimal(1), new Coffee());
        String instruction = machineTranslator.command(order);

        //then
        assertThat(instruction).isEqualTo("C::");
    }

    @Test
    public void should_return_instruction_of_chocolate_when_order_is_chocolate() {
        //when
        Order order = new Order(0, new BigDecimal(1), new Chocolate());
        String instruction = machineTranslator.command(order);

        //then
        assertThat(instruction).isEqualTo("H::");
    }

    @Test
    public void should_return_orange_instruction_when_order_is_orange() {
        //given
        Order orangeOrder = new Order(0, new BigDecimal(1), new Orange());
        //when
        String instruction = machineTranslator.command(orangeOrder);
        //then
        assertThat(instruction).isEqualTo("O::");
    }

    @Test
    public void should_return_instruction_when_order_is_chocolate_with_one_sugar() {
        //when
        Order order = new Order(1, new BigDecimal(1), new Chocolate());
        String instruction = machineTranslator.command(order);

        //then
        assertThat(instruction).isEqualTo("H:1:0");
    }

    @Test
    public void should_return_instruction_of_coffee_with_two_sugar_and_stick_when_order_is_coffee_with_two_sugar() {
        //when
        Order order = new Order(2, new BigDecimal(1), new Coffee());
        String instruction = machineTranslator.command(order);

        //then
        assertThat(instruction).isEqualTo("C:2:0");
    }

    @Test
    public void should_return_message_instruction__when_order_is_message() {
        //given
        String msg = "default message";
        when(forewordMessageProcess.sendsMsg(anyString())).thenReturn("M:default message");

        //when
        String instruction = machineTranslator.sendMessage(msg);

        //then
        assertThat(instruction.charAt(0)).isEqualTo('M');
        assertThat(instruction.substring(2, instruction.length())).isEqualTo("default message");
    }


    @Test
    public void should_return_tea_instruction_only_if_money_enough() {
        //given
        Order order = new Order(1, BigDecimal.valueOf(1), new Tea());

        //when
        String instruction = machineTranslator.command(order);

        //then
        assertThat(instruction).isEqualTo("T:1:0");
    }

    @Test
    public void should_return_coffee_instruction_if_money_enough() {
        //given
        Order order = new Order(1, new BigDecimal(1), new Coffee());

        //when
        String instruction = machineTranslator.command(order);

        //then
        assertThat(instruction).isEqualTo("C:1:0");
    }

    @Test
    public void should_return_tea_instruction_if_money_enough_when_tea_order() {
        //given
        Order order = new Order(1, new BigDecimal(0.4), new Tea());

        //when
        String instruction = machineTranslator.command(order);

        //then
        assertThat(instruction).isEqualTo("T:1:0");
    }



    @Test
    public void should_return_chocolate_instruction_if_money_enough_when_chocolate_order() {
        //given
        Order order = new Order(1, new BigDecimal(0.5), new Chocolate());

        //when
        String instruction = machineTranslator.command(order);

        //then
        assertThat(instruction).isEqualTo("H:1:0");
    }

    @Test
    public void should_send_message_instruction_if_money_not_enough_for_order_chocolate() {
        //given
        Order order = new Order(1, new BigDecimal(0.3), new Chocolate());
        //when
        machineTranslator.command(order);
        //then
        verify(forewordMessageProcess, times(1)).sendsMsg(anyString());
    }

    @Test
    public void should_send_message_instruction_if_money_not_enough_for_order_orange() {
        //given
        Order order = new Order(1, new BigDecimal(0.6), new Orange());
        //when
        machineTranslator.command(order);
        //then
        verify(forewordMessageProcess, times(1)).sendsMsg(anyString());
    }

    @Test
    public void should_send_message_instruction_if_money_not_enough_for_order_tea() {
        //given
        Order order = new Order(1, new BigDecimal(0.2), new Tea());
        //when
        machineTranslator.command(order);
        //then
        verify(forewordMessageProcess, times(1)).sendsMsg(anyString());
    }

    @Test
    public void should_send_message_instruction_if_money_not_enough_for_order_coffee() {
        //given
        Order order = new Order(1, new BigDecimal(0.4), new Coffee());
        //when
        machineTranslator.command(order);
        //then

        verify(forewordMessageProcess, times(1)).sendsMsg(anyString());
    }

    @Test
    public void should_send_message_with_missingmoney_when_order_coffe_and_not_enough_money() {
        //given
        String msg = "Not enough money, please add , 0.50";
        Order order = new Order(1, new BigDecimal(0.1), new Coffee());
        //when
        machineTranslator.command(order);
        //then
        verify(forewordMessageProcess, times(1)).sendsMsg(msg);
    }

    @Test
    public void should_send_message_with_missingmoney_when_order_tea_and_not_enough_money() {
        //given
        String msg = "Not enough money, please add , 0.20";
        Order order = new Order(1, new BigDecimal(0.2), new Tea());
        //when
        machineTranslator.command(order);
        //then
        verify(forewordMessageProcess, times(1)).sendsMsg(msg);
    }

    @Test
    public void should_send_message_with_missingmoney_when_order_chocolate_and_not_enough_money() {
        //given
        String msg = "Not enough money, please add , 0.10";
        Order order = new Order(1, new BigDecimal(0.4), new Chocolate());
        //when
        machineTranslator.command(order);
        //then
        verify(forewordMessageProcess, times(1)).sendsMsg(msg);
    }

    @Test
    public void should_return_orange_instruction_when_order_is_orange_with_enough_money() {
        //given
        Order orangeOrder = new Order(0, new BigDecimal(1), new Orange());
        //when
        String instruction = machineTranslator.command(orangeOrder);
        //then
        assertThat(instruction).isEqualTo("O::");
    }

    @Test
    public void should_return_extra_hot_coffee_instruction_when_order_is_extra_hot_coffee() {
        //given
        Coffee coffee = new Coffee();

        HotDecorator hotCoffee = new HotDecorator(coffee);

        Order CoffeeOrder = new Order(0, new BigDecimal(1), hotCoffee);
        //when
        String instruction = machineTranslator.command(CoffeeOrder);

        //then
        assertThat(instruction).isEqualTo("Ch::");
    }

    @Test
    public void should_save_order_when_successful_purchased() {
        //given
        Coffee coffee = new Coffee();
        Order coffeeOrder = new Order(0, new BigDecimal(1), coffee);

        //when
        machineTranslator.command(coffeeOrder);

        //then
        verify(orderRepository, times(1)).save(coffeeOrder);
    }

    @Test
    public void should_ignore_save_order_when_failure_purchased() {
        //given
        Coffee coffee = new Coffee();
        Order coffeeOrder = new Order(0, new BigDecimal(0), coffee);

        //when
        machineTranslator.command(coffeeOrder);

        //then
        verify(orderRepository, never()).save(coffeeOrder);
    }

    @Test
    public void should_notify_user_when_shortage_of_liquid() {
        //given
        Coffee coffee = new Coffee();
        Order coffeeOrder = new Order(0, new BigDecimal(1), coffee);
        when(beverageQuantityChecker.isEmpty(anyString())).thenReturn(true);

        //when
        machineTranslator.command(coffeeOrder);

        //then
        verify(emailNotifier, times(1)).notifyMissingDrink(anyString());
        verify(forewordMessageProcess, times(1)).sendsMsg(" A drink can't be delivered because of a shortage ");
    }

}