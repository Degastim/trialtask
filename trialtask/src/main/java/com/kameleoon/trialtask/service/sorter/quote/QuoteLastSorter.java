package com.kameleoon.trialtask.service.sorter.quote;

import com.kameleoon.trialtask.entity.Quote;
import com.kameleoon.trialtask.service.sorter.Sorter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class QuoteLastSorter implements Sorter<Quote> {
    @Override
    public List<Quote> sort(List<Quote> quoteList, int number) {
        return quoteList.stream().sorted((o1, o2) -> o2.getCreationDate().compareTo(o1.getCreationDate())).limit(number)
                .toList();
    }
}
