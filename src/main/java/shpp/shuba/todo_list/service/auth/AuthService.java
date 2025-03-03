package shpp.shuba.todo_list.service.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import shpp.shuba.todo_list.dto.AuthResponseDTO;
import shpp.shuba.todo_list.dto.LoginDTO;
import shpp.shuba.todo_list.dto.RegisterDTO;
import shpp.shuba.todo_list.exceptions.ThereIsNoRoleInDB;
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

    @Override
    public void register(RegisterDTO registerDTO) {
        Set<Role> roles = roleRepository.findByNameIn(registerDTO.getRoles());
        if (roles.isEmpty()) {
            throw new ThereIsNoRoleInDB();
        }

        MyUser user = MyUser.builder()
                .username(registerDTO.getUsername())
                .email(registerDTO.getEmail())
                .password(encoder.encode(registerDTO.getPassword()))
                .roles(roles)
                .build();

        userRepository.save(user);
    }
}

