package shpp.shuba.todo_list.exceptions;

public class ThereIsNoRoleInDB extends RuntimeException{
    public ThereIsNoRoleInDB() {
        super("There is no roles in DB yet.");
    }
}
