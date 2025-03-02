package shpp.shuba.todo_list.service.user;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import shpp.shuba.todo_list.dto.RegisterDTO;
import shpp.shuba.todo_list.dto.UserDTO;
import shpp.shuba.todo_list.models.MyUser;
import shpp.shuba.todo_list.models.Role;
import shpp.shuba.todo_list.projections.UserProjection;
import shpp.shuba.todo_list.repository.RoleRepository;
import shpp.shuba.todo_list.repository.UserRepository;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/**
 * UserService
 * Реєстрація користувача
 * Отримання користувача за ID
 * Отримання списку всіх користувачів
 * Оновлення профілю користувача (за необхідності)
 * Видалення користувача (якщо потрібно)
 */
@Service
@RequiredArgsConstructor
public class UserService implements IUserService {

    public static final String USER_NOT_FOUND = "User not found";
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder encoder;

    @Override
    public UserDTO registerUser(RegisterDTO registerDTO) {
        Set<Role> roles = roleRepository.findByNameIn(registerDTO.getRoles());

        MyUser user = MyUser.builder()
                .username(registerDTO.getUsername())
                .email(registerDTO.getEmail())
                .password(encoder.encode(registerDTO.getPassword()))
                .roles(roles)
                .build();

        MyUser savedUser = userRepository.save(user);
        return userToDto(savedUser);
    }

    @Override
    public UserDTO getUserById(Long id) {
        return userRepository.findById(id)
                .map(this::userToDto)
                .orElseThrow(() -> new RuntimeException(USER_NOT_FOUND));
    }

    @Override
    public List<UserDTO> getAllUsers(int page, int size) {
        Pageable pageRequest = PageRequest.of(page, size);

        Page<UserDTO> all = userRepository.findAllBy(pageRequest); // getAll
        Stream<UserDTO> stream = all.get();

        return stream.toList();
    }

    @Override
    public UserDTO updateUser(Long id, UserDTO userDTO) {
        MyUser user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(USER_NOT_FOUND));

        user.setUsername(userDTO.username());
        user.setEmail(userDTO.email());
        user.setRoles(userDTO.roles());

        MyUser updatedUser = userRepository.save(user);
        return userToDto(updatedUser);
    }

    @Override
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new RuntimeException(USER_NOT_FOUND);
        }
        userRepository.deleteById(id);
    }

    private UserDTO userToDto(MyUser user) {
        return new UserDTO(user.getUsername(), user.getEmail(), user.getRoles());
    }
}

