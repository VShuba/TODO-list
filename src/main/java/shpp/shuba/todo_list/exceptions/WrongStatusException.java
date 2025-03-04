package shpp.shuba.todo_list.exceptions;

import org.springframework.context.MessageSource;

public class WrongStatusException extends BaseLocalizedException {

    public WrongStatusException(MessageSource messageSource) {
        super(messageSource, "error.wrong.status");
    }
}