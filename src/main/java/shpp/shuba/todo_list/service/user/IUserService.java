package shpp.shuba.todo_list.service.user;

import shpp.shuba.todo_list.dto.ResponseUserDTO;
import shpp.shuba.todo_list.dto.UserDTO;

import java.util.List;

public interface IUserService {

    ResponseUserDTO getUserById(Long id);

    List<ResponseUserDTO> getAllUsers(int page, int size);

    ResponseUserDTO updateUser(Long id, UserDTO userDTO);

    void deleteUser(Long id);
}
// use Pageable
