package com.kameleoon.trialtask.service.sorter.quote;

import com.kameleoon.trialtask.entity.Quote;
import com.kameleoon.trialtask.entity.Vote;
import com.kameleoon.trialtask.service.sorter.Sorter;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;

@Component
public class QuoteWorseSorter implements Sorter<Quote> {
    @Override
    public List<Quote> sort(List<Quote> quoteList, int number) {
        return quoteList.stream().sorted(Comparator.comparingInt(o ->o.getVoteList().stream().mapToInt(Vote::getMark).sum()))
                .limit(number).toList();
    }
}
