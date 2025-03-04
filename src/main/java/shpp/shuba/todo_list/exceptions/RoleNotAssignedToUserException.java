package shpp.shuba.todo_list.exceptions;

public class RoleNotAssignedToUserException extends RuntimeException {

    public static final String THE_USER_HAS_NO_SUCH_ROLE_TO_DELETE = "The user has no such role to delete";

    public RoleNotAssignedToUserException() {
        super(THE_USER_HAS_NO_SUCH_ROLE_TO_DELETE);
    }
}
