package shpp.shuba.todo_list.exceptions;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

public class BaseLocalizedException extends RuntimeException {

    protected BaseLocalizedException(MessageSource messageSource, String messageKey) {
        super(messageSource.getMessage(messageKey, null, LocaleContextHolder.getLocale()));
    }
}