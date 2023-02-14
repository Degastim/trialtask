package com.kameleoon.trialtask.service.impl;

import com.kameleoon.trialtask.repository.dao.QuoteDao;
import com.kameleoon.trialtask.repository.dao.UserDao;
import com.kameleoon.trialtask.api.dto.request.ParamContainer;
import com.kameleoon.trialtask.entity.Quote;
import com.kameleoon.trialtask.entity.User;
import com.kameleoon.trialtask.exception.Error;
import com.kameleoon.trialtask.exception.Notification;
import com.kameleoon.trialtask.exception.ResourceNotFoundedException;
import com.kameleoon.trialtask.exception.ValidationException;
import com.kameleoon.trialtask.service.QuoteService;
import com.kameleoon.trialtask.service.sorter.quote.QuoteSortOperation;
import com.kameleoon.trialtask.service.sorter.Sorter;
import com.kameleoon.trialtask.service.validator.Validator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class QuoteServiceImpl implements QuoteService {
    private final QuoteDao quoteDao;
    private final UserDao userDao;
    private final Validator<Quote> quoteValidator;
    private final Validator<ParamContainer> paramContainerValidator;
    private final QuoteSortOperation quoteSortOperation;
    @Value("${application.page.defaultSize}")
    private int defaultPageSize;
    @Value("${application.sort.number}")
    private int defaultSortNumber;

    public QuoteServiceImpl(QuoteDao quoteDao, UserDao userDao, Validator<Quote> quoteValidator,
                            Validator<ParamContainer> paramContainerValidator, QuoteSortOperation quoteSortOperation) {
        this.quoteDao = quoteDao;
        this.userDao = userDao;
        this.quoteValidator = quoteValidator;
        this.paramContainerValidator = paramContainerValidator;
        this.quoteSortOperation = quoteSortOperation;
    }

    @Override
    @Transactional
    public Quote save(Quote quote) {
        Notification notification = quoteValidator.validate(quote);
        if (notification.hasErrors()) {
            throw new ValidationException(notification);
        }
        quoteDao.save(quote);
        User user = userDao.findById(quote.getPostingUser().getId()).get();
        quote.setPostingUser(user);
        return quote;
    }

    @Override
    public Quote find(long quoteId) {
        Optional<Quote> optionalQuote = quoteDao.findById(quoteId);
        if (optionalQuote.isEmpty()) {
            Notification notification = new Notification();
            notification.addError(new Error("quote-02", "Quote with this ID not found",
                    "Quote with this ID not found"));
            throw new ResourceNotFoundedException(notification);
        }
        return optionalQuote.get();
    }

    @Override
    @Transactional
    public List<Quote> findByParams(ParamContainer paramContainer) {
        Notification notification = paramContainerValidator.validate(paramContainer);
        if (notification.hasErrors()) {
            throw new ValidationException(notification);
        }
        String type = paramContainer.getType();
        List<Quote> quoteList;
        if (type != null) {
            quoteList = quoteDao.findAll();
            Sorter<Quote> sorter = quoteSortOperation.getMap().get(paramContainer.getType().toLowerCase());
            Integer number = paramContainer.getNumber();
            if (number == null|| number==0) {
                number = defaultSortNumber;
            }
            return sorter.sort(quoteList, number);
        } else {
            Integer size = paramContainer.getSize();
            if (size == null|| size==0) {
                size = defaultPageSize;
            }
            Pageable pageable = Pageable.ofSize(size).withPage(paramContainer.getPage());
            quoteList = quoteDao.findAll(pageable).stream().toList();
            return quoteList;
        }
    }

    @Override
    @Transactional
    public void delete(long quoteId) {
        if (!quoteDao.existsById(quoteId)) {
            Notification notification = new Notification();
            notification.addError(new Error("quote-02", "Quote with this ID not found",
                    "Quote with this ID not found"));
            throw new ResourceNotFoundedException(notification);
        }
        quoteDao.deleteById(quoteId);
    }

    @Override
    @Transactional
    public Quote update(long id, Quote quote) {
        Notification notification = quoteValidator.validate(quote);
        if (notification.hasErrors()) {
            throw new ValidationException(notification);
        }
        Optional<Quote> optionalQuote = quoteDao.findById(id);
        if (optionalQuote.isEmpty()) {
            notification = new Notification();
            notification.addError(new Error("quote-02", "Quote with this ID not found",
                    "Quote with this ID not found"));
            throw new ResourceNotFoundedException(notification);
        }
        String content = quote.getContent();
        Quote daoQuote = optionalQuote.get();
        daoQuote.setContent(content);
        quoteDao.save(daoQuote);
        return daoQuote;
    }
}
