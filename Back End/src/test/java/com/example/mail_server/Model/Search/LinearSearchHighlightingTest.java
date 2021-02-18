package com.example.mail_server.Model.Search;

import org.junit.Rule;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;


public class LinearSearchHighlightingTest {

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();



    @Test
    public void search() {
        LinearSearchHighlighting linearSearchHighlighting = new LinearSearchHighlighting();

        Interval[] test = linearSearchHighlighting.search("Hello World","He" );
        Assertions.assertEquals(1,test.length);
        Assertions.assertEquals(0,test[0].getStart());
        Assertions.assertEquals(2,test[0].getEnd());

    }
}