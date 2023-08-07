package com.example.unit_test;

import com.example.unit_test.service.Numbers;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class NumbersTest {
    @ParameterizedTest
    @ValueSource(ints = {1,3,5,-3,15,Integer.MAX_VALUE})
    void isOdd_ShouldReturnTrueForOddNumber(int number){

        assertTrue(Numbers.isOdd(number));
    }
}
