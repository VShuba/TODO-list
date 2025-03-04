package shpp.shuba.todo_list.controllers.auth;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import shpp.shuba.todo_list.dto.RegisterUserDTO;
import shpp.shuba.todo_list.service.auth.IAuthService;

@Tag(name = "Registration", description = "User registration")
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final IAuthService authService;

    @Operation(summary = "Register new user", description = "Registers a new user")
    @PostMapping("/register")
    public ResponseEntity<String> register(@Valid @RequestBody RegisterUserDTO registerUserDTO) {
        authService.register(registerUserDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully");
    }
}
