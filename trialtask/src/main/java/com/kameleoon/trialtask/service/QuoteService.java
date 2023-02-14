package com.kameleoon.trialtask.service;

import com.kameleoon.trialtask.api.dto.request.ParamContainer;
import com.kameleoon.trialtask.entity.Quote;

import java.util.List;

/**
 * Quote service for quotes
 *
 * @author Yauheni Tstiov
 */
public interface QuoteService {
    Quote save(Quote quote);

    Quote find(long quoteId);

    List<Quote> findByParams(ParamContainer paramContainer);

    void delete(long quoteId);

    Quote update(long id, Quote quote);
}
