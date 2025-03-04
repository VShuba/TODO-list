package shpp.shuba.todo_list.exceptions;

import org.springframework.context.MessageSource;

public class TryingToTouchSuperAdmin extends BaseLocalizedException {

    public TryingToTouchSuperAdmin(MessageSource messageSource) {
        super(messageSource, "error.superadmin.access");
    }
}
