package shpp.shuba.todo_list.exceptions;

public class ThereIsNoRoleInDB extends RuntimeException{

    public static final String THERE_IS_NO_ROLES_IN_DB_YET = "There is no roles in DB yet.";

    public ThereIsNoRoleInDB() {
        super(THERE_IS_NO_ROLES_IN_DB_YET);
    }
}
