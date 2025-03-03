package shpp.shuba.todo_list.service.user;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import shpp.shuba.todo_list.dto.UserDTO;
import shpp.shuba.todo_list.exceptions.UserNotFoundException;
import shpp.shuba.todo_list.models.MyUser;
import shpp.shuba.todo_list.repository.UserRepository;

import java.util.List;
import java.util.Optional;
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

    private final UserRepository userRepository;

    @Override
    public UserDTO getUserById(Long id) {
        return userRepository.findById(id)
                .map(this::userToDto)
                .orElseThrow(UserNotFoundException::new);
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
                .orElseThrow(UserNotFoundException::new);

        Optional.ofNullable(userDTO.username())
                .ifPresentOrElse(
                        username -> {
                            if (!username.isEmpty())
                                user.setUsername(username);
                        },
                        () -> user.setUsername(null)
                );


        Optional.ofNullable(userDTO.email())
                .ifPresentOrElse(
                        email -> {
                            if (!email.isEmpty())
                                user.setEmail(email);
                        },
                        () -> user.setEmail(null)
                );

        Optional.ofNullable(userDTO.roles())
                .ifPresentOrElse(
                        roles -> {
                            if (!roles.isEmpty()) user.setRoles(roles);
                        },
                        () -> user.setRoles(null)
                );

        return userToDto(userRepository.save(user));
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    private UserDTO userToDto(MyUser user) {
        return new UserDTO(user.getUsername(), user.getEmail(), user.getRoles());
    }
}

