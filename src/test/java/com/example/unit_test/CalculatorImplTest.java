package com.example.unit_test;

import com.example.unit_test.tdd.Calculator;
import com.example.unit_test.tdd.CalculatorImpl;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class CalculatorImplTest {

    private Calculator calculator;

    @Test
    public void givenAAndBAddThenC() {
        //Arrange
        calculator = new CalculatorImpl();
        int a = 5;
        int b = 2;
        int c = 7;

        //Act
        int result = calculator.add(a, b);

        //Assert

        assertThat(result).isEqualTo(c);

    }

    @Test
    public void givenAAndBWhenSubtractThenC() {
        //Arrange
        calculator = new CalculatorImpl();
        int a = 7;
        int b = 2;
        int c = 5;

        //Act
        int result = calculator.subtract(a, b);

        //Assert

        assertThat(result).isEqualTo(c);
    }

    @Test
    public void givenAAndBWhenMultiplyThenC() {
        //Arrange
        calculator = new CalculatorImpl();
        int a = 7;
        int b = 2;
        int c = 14;

        //Act
        int result = calculator.multiply(a, b);

        //Assert

        assertThat(result).isEqualTo(c);
    }

    @Test
    public void givenAAndBWhenDivideThenC() {
        //Arrange
        calculator = new CalculatorImpl();
        int a = 14;
        int b = 2;
        int c = 7;

        //Act
        int result = calculator.divide(a, b);

        //Assert

        assertThat(result).isEqualTo(c);
    }

    @Test
    public void givenAAndBWhenDivideByZeroThenException() {

        //Arrange
        calculator = new CalculatorImpl();
        int a = 14;
        int b = 0;

        //Act
        assertThatThrownBy(() -> calculator.divide(a, b))
                .isInstanceOf(ArithmeticException.class)
                .hasMessage("/ by zero");

    }




}
