package com.kameleoon.trialtask.api.controller;

import com.kameleoon.trialtask.api.dto.request.ParamContainer;
import com.kameleoon.trialtask.api.dto.request.QuoteDataDTO;
import com.kameleoon.trialtask.api.dto.response.QuoteResponseDTO;
import com.kameleoon.trialtask.entity.Quote;
import com.kameleoon.trialtask.api.hateoas.QuoteResponseDTOHateoas;
import com.kameleoon.trialtask.api.mapper.request.DTORequestMapper;
import com.kameleoon.trialtask.api.mapper.response.DTOResponseMapper;
import com.kameleoon.trialtask.service.QuoteService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/quotes")
public class QuoteController {
    private final QuoteService quoteService;
    private final DTORequestMapper<Quote, QuoteDataDTO> quoteDataDTOMapper;
    private final DTOResponseMapper<Quote, QuoteResponseDTO> quoteResponseDTOMapper;
    private final QuoteResponseDTOHateoas quoteResponseDTOHateoas;

    public QuoteController(QuoteService quoteService, DTORequestMapper<Quote, QuoteDataDTO> quoteDataDTOMapper,
                           DTOResponseMapper<Quote, QuoteResponseDTO> quoteResponseDTOMapper,
                           QuoteResponseDTOHateoas quoteResponseDTOHateoas) {
        this.quoteService = quoteService;
        this.quoteResponseDTOMapper = quoteResponseDTOMapper;
        this.quoteResponseDTOHateoas = quoteResponseDTOHateoas;
        this.quoteDataDTOMapper = quoteDataDTOMapper;
    }

    @PostMapping
    public QuoteResponseDTO save(@RequestBody QuoteDataDTO quoteDataDTO) {
        Quote quote = quoteDataDTOMapper.toEntity(quoteDataDTO);
        quote = quoteService.save(quote);
        QuoteResponseDTO quoteResponseDTO = quoteResponseDTOMapper.toDTO(quote);
        quoteResponseDTOHateoas.build(quoteResponseDTO);
        return quoteResponseDTO;
    }

    @GetMapping
    public List<QuoteResponseDTO> findByParam(@ModelAttribute ParamContainer paramContainer) {
        List<Quote> quoteList = quoteService.findByParams(paramContainer);
        return quoteList.stream().map(quoteResponseDTOMapper::toDTO).peek(quoteResponseDTOHateoas::build).toList();
    }

    @GetMapping("/{quoteId}")
    public QuoteResponseDTO find(@PathVariable long quoteId) {
        Quote quote = quoteService.find(quoteId);
        QuoteResponseDTO quoteResponseDTO = quoteResponseDTOMapper.toDTO(quote);
        quoteResponseDTOHateoas.build(quoteResponseDTO);
        return quoteResponseDTO;
    }

    @PutMapping("/{quoteId}")
    public QuoteResponseDTO update(@PathVariable long quoteId, @RequestBody QuoteDataDTO quoteDataDTO) {
        Quote quote = quoteDataDTOMapper.toEntity(quoteDataDTO);
        Quote resultQuote = quoteService.update(quoteId, quote);
        QuoteResponseDTO quoteResponseDTO = quoteResponseDTOMapper.toDTO(resultQuote);
        quoteResponseDTOHateoas.build(quoteResponseDTO);
        return quoteResponseDTO;
    }

    @DeleteMapping("/{quoteId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable long quoteId) {
        quoteService.delete(quoteId);
    }
}
