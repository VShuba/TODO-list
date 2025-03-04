package shpp.shuba.todo_list.service.auth;

import shpp.shuba.todo_list.dto.RegisterUserDTO;

public interface IAuthService {
    void register(RegisterUserDTO registerUserDTO);
}
