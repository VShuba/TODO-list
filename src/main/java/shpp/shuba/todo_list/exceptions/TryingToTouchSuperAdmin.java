package shpp.shuba.todo_list.exceptions;

public class TryingToTouchSuperAdmin extends RuntimeException {

    private static final String YOU_CAN_NOT_DELETE_SUPER_ADMIN = "YOU CAN NOT TOUCH SUPER ADMIN =)";

    public TryingToTouchSuperAdmin() {
        super(YOU_CAN_NOT_DELETE_SUPER_ADMIN);
    }
}
