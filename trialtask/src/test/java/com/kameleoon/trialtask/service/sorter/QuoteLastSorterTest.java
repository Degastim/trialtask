package com.kameleoon.trialtask.service.sorter;

import com.kameleoon.trialtask.entity.Quote;
import com.kameleoon.trialtask.service.sorter.quote.QuoteLastSorter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class QuoteLastSorterTest {
    private Sorter<Quote> sorter;

    @BeforeEach
    void setUp() {
        sorter = new QuoteLastSorter();
    }

    @Test
    void sort() {
        //Given
        List<Quote> quoteList = new ArrayList<>();
        Quote quote1 = new Quote(1, LocalDateTime.of(2021, Month.MARCH, 12, 13, 14));
        Quote quote2 = new Quote(2, LocalDateTime.of(2021, Month.APRIL, 12, 13, 14));
        Quote quote3 = new Quote(3, LocalDateTime.of(2022, Month.APRIL, 12, 13, 14));
        Quote quote4 = new Quote(4, LocalDateTime.of(2021, Month.APRIL, 13, 13, 14));
        Quote quote5 = new Quote(5, LocalDateTime.of(2021, Month.APRIL, 12, 14, 14));
        Quote quote6 = new Quote(6, LocalDateTime.of(2021, Month.APRIL, 12, 13, 15));
        quoteList.add(quote1);
        quoteList.add(quote2);
        quoteList.add(quote3);
        quoteList.add(quote4);
        quoteList.add(quote5);
        quoteList.add(quote6);
        List<Quote> expected = new ArrayList<>();
        expected.add(quote3);
        expected.add(quote4);
        expected.add(quote5);

        //When
        List<Quote> actual = sorter.sort(quoteList, 3);

        //Then
        assertEquals(expected, actual);
    }
}