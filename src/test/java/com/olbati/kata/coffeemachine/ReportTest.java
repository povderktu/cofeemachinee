package com.olbati.kata.coffeemachine;

import com.olbati.kata.coffeemachine.dto.DrinkSoldDetailsDto;
import com.olbati.kata.coffeemachine.repositories.IOrderRepository;
import com.olbati.kata.coffeemachine.dom.order.Order;
import com.olbati.kata.coffeemachine.dom.products.Chocolate;
import com.olbati.kata.coffeemachine.dom.products.Coffee;
import com.olbati.kata.coffeemachine.dom.products.Orange;
import com.olbati.kata.coffeemachine.dom.products.Tea;
import com.olbati.kata.coffeemachine.services.ConsolePrinter;
import com.olbati.kata.coffeemachine.services.OrderReport;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ReportTest {


    @InjectMocks
    OrderReport orderReport;

    @Mock
    private IOrderRepository orderRepository;

    @Test
    public void should_get_details_of_purchased_coffee() {
        //given

        Coffee coffee = new Coffee();
        Order coffeeOrder = new Order(0, new BigDecimal(1), coffee);
        Order coffeeOrder2 = new Order(0, new BigDecimal(1), coffee);
        Order coffeeOrder3 = new Order(0, new BigDecimal(1), coffee);
        Order coffeeOrder4 = new Order(0, new BigDecimal(1), coffee);

        when(orderRepository.findOrdersByProduct(coffee)).thenReturn(Arrays.asList(coffeeOrder, coffeeOrder2, coffeeOrder3, coffeeOrder4));

        //when
        DrinkSoldDetailsDto details = orderReport.getReportDetails(coffee);

        //then
        assertThat(details.product).isInstanceOf(Coffee.class);
        assertThat(details.amount).isEqualTo(coffee.getPrice().multiply(new BigDecimal(4)));
        assertThat(details.quantity).isEqualTo(4);
    }


    @Test
    public void should_get_details_of_1_coffee_and_2_tea_and_2_chocolate_and_1_orange() {
        //given
        when(orderRepository.findProductTypes()).thenReturn(Arrays.asList(new Coffee(), new Tea(), new Chocolate(), new Orange()));

        Order coffeeOrder = new Order(0, new BigDecimal(1), new Coffee());
        Order teaOrder = new Order(0, new BigDecimal(1), new Tea());
        Order chocolateOrder = new Order(0, new BigDecimal(1), new Chocolate());
        Order orangeOrder = new Order(0, new BigDecimal(1), new Orange());

        when(orderRepository.findOrdersByProduct(new Coffee())).thenReturn(Arrays.asList(coffeeOrder));
        when(orderRepository.findOrdersByProduct(new Tea())).thenReturn(Arrays.asList(teaOrder, teaOrder));
        when(orderRepository.findOrdersByProduct(new Chocolate())).thenReturn(Arrays.asList(chocolateOrder, chocolateOrder));
        when(orderRepository.findOrdersByProduct(new Orange())).thenReturn(Arrays.asList(orangeOrder));

        //when
        List<DrinkSoldDetailsDto> details = orderReport.getReportDetails();

        //then
        assertThat(details).isNotEmpty();
        assertThat(details).hasSize(4);
        assertThat(details).contains(
                new DrinkSoldDetailsDto(new Coffee(), 1, new BigDecimal(0.6).setScale(3, BigDecimal.ROUND_HALF_UP)),
                new DrinkSoldDetailsDto(new Tea(), 2, new BigDecimal(0.8).setScale(3, BigDecimal.ROUND_HALF_UP)),
                new DrinkSoldDetailsDto(new Chocolate(), 2, new BigDecimal(1).setScale(3, BigDecimal.ROUND_HALF_UP)),
                new DrinkSoldDetailsDto(new Orange(), 1, new BigDecimal(0.8).setScale(3, BigDecimal.ROUND_HALF_UP)));
    }


    @Test
    public void should_print_report_details_on_console() {
        orderReport.printer = new ConsolePrinter();
        //given
        when(orderRepository.findProductTypes()).thenReturn(Arrays.asList(new Coffee(), new Tea(), new Chocolate(), new Orange()));

        Order coffeeOrder = new Order(0, new BigDecimal(1), new Coffee());
        Order teaOrder = new Order(0, new BigDecimal(1), new Tea());
        Order chocolateOrder = new Order(0, new BigDecimal(1), new Chocolate());
        Order orangeOrder = new Order(0, new BigDecimal(1), new Orange());

        when(orderRepository.findOrdersByProduct(new Coffee())).thenReturn(Arrays.asList(coffeeOrder));
        when(orderRepository.findOrdersByProduct(new Tea())).thenReturn(Arrays.asList(teaOrder, teaOrder));
        when(orderRepository.findOrdersByProduct(new Chocolate())).thenReturn(Arrays.asList(chocolateOrder, chocolateOrder));
        when(orderRepository.findOrdersByProduct(new Orange())).thenReturn(Arrays.asList(orangeOrder));

        //when
        orderReport.printReport();
    }
}
