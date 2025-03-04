package shpp.shuba.todo_list.controllers.exceptions;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import shpp.shuba.todo_list.exceptions.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // @Valid
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail handleValidEx(MethodArgumentNotValidException e) {
        return ProblemDetail.forStatusAndDetail(
                HttpStatus.BAD_REQUEST, "Validation failed due to: " + e.getMessage()
        );
    }

    // Incorrect input JSON
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ProblemDetail handleNotReadableHttpEx(HttpMessageNotReadableException e) {
        return ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, "Invalid Input: " + e.getMessage());
    }

    // Failed to save to DB. Its DB(Entity) Exceptions not DTO's
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ProblemDetail handleDataIntegrityViolation(DataIntegrityViolationException e) {
        return ProblemDetail.forStatusAndDetail(
                HttpStatus.BAD_REQUEST, "Database error: possible duplicate email or other constraint violation : "
                        + e.getMessage()
        );
    }

    // --------------------- My ex

    @ExceptionHandler(ThereIsNoRoleInDB.class)
    public ProblemDetail handleRoleEx(ThereIsNoRoleInDB e) {
        return ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, e.getMessage());
    }

    @ExceptionHandler(TaskNotFoundException.class)
    public ProblemDetail handleRoleEx(TaskNotFoundException e) {
        return ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, e.getMessage());
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ProblemDetail handleRoleEx(UserNotFoundException e) {
        return ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, e.getMessage());
    }

    @ExceptionHandler(WrongStatusException.class)
    public ProblemDetail handleRoleEx(WrongStatusException e) {
        return ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, e.getMessage());
    }

    @ExceptionHandler(RoleNotAssignedToUserException.class)
    public ProblemDetail handleRoleEx(RoleNotAssignedToUserException e) {
        return ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, e.getMessage());
    }

    @ExceptionHandler(TryingToTouchSuperAdmin.class)
    public ProblemDetail handleRoleEx(TryingToTouchSuperAdmin e) {
        return ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, e.getMessage());
    }
}
