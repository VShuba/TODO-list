package shpp.shuba.todo_list.service.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import shpp.shuba.todo_list.dto.RegisterUserDTO;
import shpp.shuba.todo_list.models.MyUser;
import shpp.shuba.todo_list.repository.UserRepository;


/**
 * AuthService (окремий сервіс для реєстрації)
 * Логін та видача (JWT-токену)
 * Валідація токена
 */
@Service
@RequiredArgsConstructor
public class AuthService implements IAuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    @Override
    public void register(RegisterUserDTO registerUserDTO) {

        MyUser user = MyUser.builder()
                .username(registerUserDTO.getUsername())
                .email(registerUserDTO.getEmail())
                .password(encoder.encode(registerUserDTO.getPassword()))
                .build();

        userRepository.save(user);
    }
}

