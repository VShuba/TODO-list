package shpp.shuba.todo_list.controllers.role;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import shpp.shuba.todo_list.dto.ResponseUserDTO;
import shpp.shuba.todo_list.models.Role;
import shpp.shuba.todo_list.models.RoleName;
import shpp.shuba.todo_list.service.role.IRoleService;

import java.util.List;

@Tag(name = "Roles", description = "Operations with roles")
@RestController
@RequestMapping("/api/v1/roles")
@RequiredArgsConstructor
public class RoleController {
    private final IRoleService roleService;

    @Operation(summary = "Get all roles", description = "Returns all available roles.")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved roles")
    @PreAuthorize("hasAnyRole('ADMIN','MODERATOR')")
    @GetMapping
    public ResponseEntity<List<Role>> getAllRoles() {
        return ResponseEntity.ok(roleService.getAllRoles());
    }

    @Operation(summary = "Add a new role", description = "Creates a new role (only for admins).")
    @ApiResponse(responseCode = "201", description = "Role successfully created")
    @ApiResponse(responseCode = "403", description = "Forbidden - Only admins can create roles")
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<Role> createRole(@Valid @RequestBody Role roleDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(roleService.createRole(roleDTO));
    }

    @Operation(summary = "Add new role to user", description = "Adds a new role without removing existing ones")
    @ApiResponse(responseCode = "200", description = "User successfully updated")
    @ApiResponse(responseCode = "404", description = "User not found, role not found in DB")
    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/{id}/{roleName}")
    public ResponseEntity<ResponseUserDTO> setNewRoleById(@PathVariable Long id, @PathVariable RoleName roleName) {
        return ResponseEntity.ok(roleService.addUserRoleById(id, roleName));
    }

    @Operation(summary = "Remove role from user", description = "Removes a specific role from the user")
    @ApiResponse(responseCode = "204", description = "Role successfully deleted")
    @ApiResponse(responseCode = "404", description = "Role not found")
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}/{roleName}")
    public ResponseEntity<ResponseUserDTO> removeRoleById(@PathVariable Long id, @PathVariable RoleName roleName) {
        return ResponseEntity.ok(roleService.removeUserRoleById(id, roleName));
    }
}
