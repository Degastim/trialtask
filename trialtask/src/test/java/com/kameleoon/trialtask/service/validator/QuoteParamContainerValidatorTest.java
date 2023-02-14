package com.kameleoon.trialtask.service.validator;

import com.kameleoon.trialtask.api.dto.request.ParamContainer;
import com.kameleoon.trialtask.exception.Error;
import com.kameleoon.trialtask.exception.Notification;
import com.kameleoon.trialtask.service.sorter.quote.QuoteSortOperation;
import com.kameleoon.trialtask.service.validator.QuoteParamContainerValidator;
import com.kameleoon.trialtask.service.validator.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class QuoteParamContainerValidatorTest {
    private Validator<ParamContainer> paramContainerValidator;
    @Mock
    private QuoteSortOperation quoteSortOperation;

    @BeforeEach
    void setUp() {
        paramContainerValidator = new QuoteParamContainerValidator(quoteSortOperation);
    }

    @Test
    void validate_withoutTypeAndPage_notThrowException() {
        String type = null;
        Integer number = null;
        Integer page = null;
        Integer size = null;
        ParamContainer paramContainer = new ParamContainer(type, number, page, size);
        Notification expected = new Notification();

        //When
        Notification actual = paramContainerValidator.validate(paramContainer);

        //Then
        assertEquals(expected, actual);
    }

    @Test
    void validate_notValidTypeAndWithoutPage_throwException() {
        String type = "ABC";
        Integer number = null;
        Integer page = null;
        Integer size = null;
        ParamContainer paramContainer = new ParamContainer(type, number, page, size);
        Notification expected = new Notification();
        expected.addError(new Error("validation-002", "There is no such type of selection",
                "There is no such type of selection. Select only the type that is possible."));

        //When
        Notification actual = paramContainerValidator.validate(paramContainer);

        //Then
        assertEquals(expected, actual);
    }

    @Test
    void validate_validTypeAndWithPage_throwException() {
        //Given
        String type = "random";
        Integer number = null;
        int page = 1;
        Integer size = null;
        ParamContainer paramContainer = new ParamContainer(type, number, page, size);
        Notification expected = new Notification();
        expected.addError(new Error("validation-001",
                "You can not specify the type of selection and pagination",
                "You cannot specify sorting and pagination options. Choose only one type."));
        expected.addError(new Error("validation-002", "There is no such type of selection",
                "There is no such type of selection. Select only the type that is possible."));

        //When
        Notification actual = paramContainerValidator.validate(paramContainer);

        //Then
        assertEquals(expected, actual);
    }

    @Test
    void validate_withoutTypeAndWithPage_throwException() {
        //Given
        String type = null;
        Integer number = null;
        int page = 1;
        int size = 1;
        ParamContainer paramContainer = new ParamContainer(type, number, page, size);
        Notification expected = new Notification();

        //When
        Notification actual = paramContainerValidator.validate(paramContainer);

        //Then
        assertEquals(expected, actual);
    }
}