package com.example.calculatordemo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    private Calculator calculator;

    @BeforeEach
    void setUp() {
        calculator = new Calculator();
    }

    @Test
    @DisplayName("Testing addition")
    void testAdd() {
        assertEquals(20, calculator.add(15, 5));
    }

    @Test
    @DisplayName("Testing for subtraction: positive result")
    void testSubtract() {
        assertEquals(10, calculator.subtract(15, 5));
    }

    @Test
    @DisplayName("Testing for subtraction: negative result")
    void testSubtract_NegativeResult() {
        assertEquals(-5, calculator.subtract(5, 10));
    }

    @Test
    @DisplayName("Testing multiplication with positive numbers")
    void testMultiply() {
        assertEquals(25, calculator.multiply(5, 5));
    }

    @Test
    @DisplayName("Testing multiplication with negative numbers")
    void testMultiply_WithNegativeNumber() {
        assertEquals(30, calculator.multiply(-3, -10));
    }

    @Test
    @DisplayName("Testing division with normal numbers")
    void testDivide() {
        assertEquals(2.5, calculator.divide(5, 2));
    }

    @Test
    @DisplayName("Division by zero must throw an exception.")
    void testDivideByZero() {
        assertThrows(IllegalArgumentException.class, () -> {
            calculator.divide(10, 0);
        });
    }

    @Test
    @DisplayName("Overflow testing for addition (Upper limit)")
    void testAdd_Overflow() {
        assertEquals(Integer.MIN_VALUE, calculator.add(Integer.MAX_VALUE, 1));
    }

    @Test
    @DisplayName("Testing for underflow during subtraction (Lower limit)")
    void testSubtract_Underflow() {
        assertEquals(Integer.MAX_VALUE, calculator.subtract(Integer.MIN_VALUE, 1));
    }

    @Test
    @DisplayName("Testing division resulting in an infinite decimal expansion")
    void testDivide_InfiniteDecimal() {
        assertEquals(0.3333333333333333, calculator.divide(1, 3), 0.0000000000000001);
    }

    @Test
    @DisplayName("Multiplication by zero")
    void testMultiply_WithZero() {
        assertEquals(0, calculator.multiply(0, 150));
        assertEquals(0, calculator.multiply(-999, 0));
    }
}
