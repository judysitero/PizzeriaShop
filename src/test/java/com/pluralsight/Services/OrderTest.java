package com.pluralsight.Services;

import com.pluralsight.Products.Drink;
import com.pluralsight.Products.Pizza;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderTest {
    @Test
    void testOrderTotalCalculation() {
        // arrange
        Order order =  new Order();
        Pizza pizza = new Pizza("Medium", "Regular", "Marinara", false);
        Drink drink = new Drink("Small", "Coke");

        // act
        order.addItem(pizza);
        order.addItem(drink);

        // assert
        assertEquals(14.00, order.calculateTotal(), 0.001);

    }
}