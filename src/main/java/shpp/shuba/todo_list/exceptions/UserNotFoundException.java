package shpp.shuba.todo_list.exceptions;

public class UserNotFoundException extends RuntimeException {

    private static final String USER_NOT_FOUND = "USER_NOT_FOUND";

    public UserNotFoundException() {
        super(USER_NOT_FOUND);
    }
}
