package shpp.shuba.todo_list.service.user;

import shpp.shuba.todo_list.dto.RegisterDTO;
import shpp.shuba.todo_list.dto.UserDTO;

import java.util.List;

public interface IUserService {
    UserDTO registerUser(RegisterDTO registerDTO);
    UserDTO getUserById(Long id);
    List<UserDTO> getAllUsers(int page, int size);
    UserDTO updateUser(Long id, UserDTO userDTO);
    void deleteUser(Long id);
}
// use Pageable
