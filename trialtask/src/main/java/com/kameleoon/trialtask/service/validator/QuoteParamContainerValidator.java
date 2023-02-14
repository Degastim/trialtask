package com.kameleoon.trialtask.service.validator;

import com.kameleoon.trialtask.api.dto.request.ParamContainer;
import com.kameleoon.trialtask.exception.Error;
import com.kameleoon.trialtask.exception.Notification;
import com.kameleoon.trialtask.service.sorter.quote.QuoteSortOperation;
import org.springframework.stereotype.Component;

@Component
public class QuoteParamContainerValidator implements Validator<ParamContainer> {
    private final QuoteSortOperation quoteSortOperation;

    public QuoteParamContainerValidator(QuoteSortOperation quoteSortOperation) {
        this.quoteSortOperation = quoteSortOperation;
    }

    @Override
    public Notification validate(ParamContainer paramContainer) {
        Notification notification = new Notification();
        String type = paramContainer.getType();
        if (type != null && paramContainer.getPage() != null) {
            notification.addError(new Error("validation-001",
                    "You can not specify the type of selection and pagination",
                    "You cannot specify sorting and pagination options. Choose only one type."));
        }
        if (type != null) {
            boolean result = quoteSortOperation.getMap().containsKey(paramContainer.getType().toLowerCase());
            if (!result) {
                notification.addError(new Error("validation-002", "There is no such type of selection",
                        "There is no such type of selection. Select only the type that is possible."));
            }
        }
        return notification;
    }
}
