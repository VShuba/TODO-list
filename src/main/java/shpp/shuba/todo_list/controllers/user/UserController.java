package shpp.shuba.todo_list.controllers.user;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import shpp.shuba.todo_list.dto.ResponseUserDTO;
import shpp.shuba.todo_list.dto.RequestUserDTO;
import shpp.shuba.todo_list.service.user.IUserService;

import java.util.List;

@Tag(name = "Users", description = "Operations with users")
@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final IUserService userService;

    @Operation(summary = "Find user by ID", description = "Returns user by given ID if exists.")
    @ApiResponse(responseCode = "200", description = "Successfully found user")
    @ApiResponse(responseCode = "404", description = "User not found")
    @PreAuthorize("#id == authentication.principal.id or hasAnyRole('ADMIN','MODERATOR')")
    @GetMapping("/{id}")
    public ResponseEntity<ResponseUserDTO> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @Operation(summary = "Find all users", description = "Returns all users in DB with pagination.")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved list of users")
    @ApiResponse(responseCode = "404", description = "No users found")
    @PreAuthorize("hasAnyRole('ADMIN', 'MODERATOR')")
    @GetMapping
    public ResponseEntity<List<ResponseUserDTO>> getAllUsers(
            @Parameter(description = "Page number starts from 0", example = "0")
            @RequestParam(defaultValue = "0") int page,

            @Parameter(description = "Number of elements per page", example = "10")
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(userService.getAllUsers(page, size));
    }

    @Operation(summary = "Update user", description = "Updates user information")
    @ApiResponse(responseCode = "200", description = "User successfully updated")
    @ApiResponse(responseCode = "404", description = "User not found")
    @PreAuthorize("#id == authentication.principal.id or hasAnyRole('ADMIN','MODERATOR')")
    @PutMapping("/{id}")
    public ResponseEntity<ResponseUserDTO> updateUser(@PathVariable Long id, @Valid @RequestBody RequestUserDTO requestUserDTO) {
        return ResponseEntity.ok(userService.updateUser(id, requestUserDTO));
    }

    @Operation(summary = "Delete user", description = "Deletes a user by ID")
    @ApiResponse(responseCode = "204", description = "User successfully deleted")
    @ApiResponse(responseCode = "404", description = "User not found")
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
