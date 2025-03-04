package shpp.shuba.todo_list.service.user;

import shpp.shuba.todo_list.dto.ResponseUserDTO;
import shpp.shuba.todo_list.dto.RequestUserDTO;

import java.util.List;

public interface IUserService {

    ResponseUserDTO getUserById(Long id);

    List<ResponseUserDTO> getAllUsers(int page, int size);

    ResponseUserDTO updateUser(Long id, RequestUserDTO requestUserDTO);

    void deleteUser(Long id);

}
// use Pageable
