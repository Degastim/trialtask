package com.kameleoon.trialtask.service.sorter;

import com.kameleoon.trialtask.entity.Quote;
import com.kameleoon.trialtask.entity.Vote;
import com.kameleoon.trialtask.service.sorter.quote.QuoteTopSorter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class QuoteTopSorterTest {
    private Sorter<Quote> sorter;

    @BeforeEach
    void setUp() {
        sorter = new QuoteTopSorter();
    }

    @Test
    void sort() {
        //Given
        List<Quote> quoteList = new ArrayList<>();
        Vote vote1 = new Vote(1L, (byte) 1);
        List<Vote> voteList1 = new ArrayList<>();
        voteList1.add(vote1);
        Quote quote1 = new Quote(1, voteList1);
        Quote quote2 = new Quote(2, new ArrayList<>());
        Vote vote2 = new Vote(1L, (byte) -1);
        List<Vote> voteList2 = new ArrayList<>();
        voteList2.add(vote2);
        Quote quote3 = new Quote(3, voteList2);
        quoteList.add(quote1);
        quoteList.add(quote2);
        quoteList.add(quote3);
        List<Quote> expected = new ArrayList<>();
        expected.add(quote1);
        expected.add(quote2);
        expected.add(quote3);


        //When
        List<Quote> actual = sorter.sort(quoteList, 3);

        //Then
        assertEquals(expected, actual);
    }
}