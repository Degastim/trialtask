package com.kameleoon.trialtask.repository.dao;

import com.kameleoon.trialtask.entity.Quote;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Interface used for interactions with quote table.
 *
 * @author Yauheni Tstiov
 */
@Repository
public interface QuoteDao extends PagingAndSortingRepository<Quote, Long> {
    Optional<Quote> findById(long id);

    List<Quote> findAll();

    void save(Quote quote);

    void deleteById(long id);

    boolean existsById(long id);
}
