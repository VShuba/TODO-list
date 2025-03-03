package shpp.shuba.todo_list.service.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import shpp.shuba.todo_list.config.JwtProvider;
import shpp.shuba.todo_list.dto.AuthResponseDTO;
import shpp.shuba.todo_list.dto.LoginDTO;
import shpp.shuba.todo_list.dto.RegisterDTO;
import shpp.shuba.todo_list.exceptions.UserNotFoundException;
import shpp.shuba.todo_list.models.MyUser;
import shpp.shuba.todo_list.models.Role;
import shpp.shuba.todo_list.repository.RoleRepository;
import shpp.shuba.todo_list.repository.UserRepository;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * AuthService (окремий сервіс для аутентифікації)
 * Логін та видача (JWT-токену)
 * Валідація токена
 */
@Service
@RequiredArgsConstructor
public class AuthService implements IAuthService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder encoder;
    private final JwtProvider jwtProvider;
    private final AuthenticationManager authenticationManager;

    @Override
    public AuthResponseDTO register(RegisterDTO registerDTO) {
        Set<Role> roles = roleRepository.findByNameIn(registerDTO.getRoles());
        // admin - 1; user - 2
        MyUser user = MyUser.builder() // USERS - 3; SET<ROLES> - 1,2
                .username(registerDTO.getUsername())
                .email(registerDTO.getEmail())
                .password(encoder.encode(registerDTO.getPassword()))
                .roles(roles)
                .build();

        userRepository.save(user);

        String token = jwtProvider.generateToken(user.getUsername());

        return AuthResponseDTO.builder()
                .accessToken(token)
                .username(user.getUsername())
                .roles(user.getRoles().stream().map(Role::getName).collect(Collectors.toSet()))
                .build();
    }

    @Override
    public AuthResponseDTO login(LoginDTO loginDTO) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword())
        );

        MyUser user = userRepository.findByUsername(loginDTO.getUsername())
                .orElseThrow(UserNotFoundException::new);

        String token = jwtProvider.generateToken(user.getUsername());

        return AuthResponseDTO.builder()
                .accessToken(token)
                .username(user.getUsername())
                .roles(user.getRoles().stream().map(Role::getName).collect(Collectors.toSet()))
                .build();
    }
}
