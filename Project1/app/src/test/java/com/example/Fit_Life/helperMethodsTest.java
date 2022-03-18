package com.example.Fit_Life;

import static com.example.Fit_Life.helperMethods.round;
import static org.junit.Assert.*;

import org.junit.Test;

public class helperMethodsTest {

    @Test
    public void test_round()
    {
        assertEquals(4.5, round(4.48, 1), 1);
        assertEquals(11.7, round(11.72, 1), 1);
        assertEquals(7.84, round(7.836, 2), 1);
        assertEquals(3.33, round(3.331, 2), 1);
    }
}