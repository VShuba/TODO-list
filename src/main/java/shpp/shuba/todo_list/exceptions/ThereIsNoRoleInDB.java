package shpp.shuba.todo_list.exceptions;

import org.springframework.context.MessageSource;

public class ThereIsNoRoleInDB extends BaseLocalizedException {

    public ThereIsNoRoleInDB(MessageSource messageSource) {
        super(messageSource, "error.role.not.found");
    }
}
