package shpp.shuba.todo_list.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.Hidden;

public enum RoleName {
    ROLE_USER, ROLE_MODERATOR,

    @JsonIgnore
    @Hidden
    ROLE_ADMIN
}
