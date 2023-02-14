package com.kameleoon.trialtask.service.sorter.quote;

import com.kameleoon.trialtask.entity.Quote;
import com.kameleoon.trialtask.entity.Vote;
import com.kameleoon.trialtask.service.sorter.Sorter;

import java.util.List;

public class QuoteTopSorter implements Sorter<Quote> {
    @Override
    public List<Quote> sort(List<Quote> quoteList, int number) {
        return quoteList.stream()
                .sorted((o1, o2) -> o2.getVoteList().stream().mapToInt(Vote::getMark).sum()
                        - o1.getVoteList().stream().mapToInt(Vote::getMark).sum()).limit(number).toList();
    }
}
