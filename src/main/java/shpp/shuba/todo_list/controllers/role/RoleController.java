package shpp.shuba.todo_list.controllers.role;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shpp.shuba.todo_list.models.Role;
import shpp.shuba.todo_list.service.role.IRoleService;

import java.util.List;

@Tag(name = "Roles", description = "Operations with roles")
@RestController
@RequestMapping("/api/v1/roles")
public class RoleController {

    private final IRoleService roleService;

    public RoleController(IRoleService roleService) {
        this.roleService = roleService;
    }

    @Operation(summary = "Get all roles", description = "Returns all available roles.")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved roles")
    @GetMapping
    public ResponseEntity<List<Role>> getAllRoles() {
        return ResponseEntity.ok(roleService.getAllRoles());
    }

    @Operation(summary = "Add a new role", description = "Creates a new role (only for admins).")
    @ApiResponse(responseCode = "201", description = "Role successfully created")
    @ApiResponse(responseCode = "403", description = "Forbidden - Only admins can create roles")
    @PostMapping
    public ResponseEntity<Role> createRole(@Valid @RequestBody Role roleDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(roleService.createRole(roleDTO));
    }
}
