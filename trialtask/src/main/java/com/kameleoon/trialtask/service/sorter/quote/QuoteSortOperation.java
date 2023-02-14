package com.kameleoon.trialtask.service.sorter.quote;

import com.kameleoon.trialtask.entity.Quote;
import com.kameleoon.trialtask.service.sorter.Sorter;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class QuoteSortOperation {
    private final Map<String, Sorter<Quote>> map = new HashMap<>();

    public QuoteSortOperation() {
        map.put("random", new QuoteRandomSorter());
        map.put("top", new QuoteTopSorter());
        map.put("worse", new QuoteWorseSorter());
        map.put("last", new QuoteLastSorter());
    }

    public Map<String, Sorter<Quote>> getMap() {
        return new HashMap<>(map);
    }
}
