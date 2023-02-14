package com.kameleoon.trialtask.service.sorter.quote;

import com.kameleoon.trialtask.entity.Quote;
import com.kameleoon.trialtask.service.sorter.Sorter;

import java.util.List;
import java.util.Random;

public class QuoteRandomSorter implements Sorter<Quote> {
    @Override
    public List<Quote> sort(List<Quote> list, int number) {
        if (list.size() <= number) {
            return list;
        }
        int size = list.size();
        Random random = new Random();
        return random.ints(0, number+1).limit(number)
                .mapToObj(list::get).toList();
    }
}
