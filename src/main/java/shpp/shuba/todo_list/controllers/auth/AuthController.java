package shpp.shuba.todo_list.controllers.auth;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import shpp.shuba.todo_list.dto.AuthResponseDTO;
import shpp.shuba.todo_list.dto.LoginDTO;
import shpp.shuba.todo_list.dto.RegisterDTO;
import shpp.shuba.todo_list.service.auth.IAuthService;

/**
 * AuthController (/auth)
 * POST /register – реєстрація
 * POST /login – аутентифікація
 */
@Tag(name = "Authentication", description = "User authentication and registration")
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final IAuthService authService;

    public AuthController(IAuthService authService) {
        this.authService = authService;
    }

    @Operation(summary = "Register new user", description = "Registers a new user")
    @PostMapping("/register")
    public ResponseEntity<String> register(@Valid @RequestBody RegisterDTO registerDTO) {
        authService.register(registerDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully");
    }
}
