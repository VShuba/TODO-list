package shpp.shuba.todo_list.service.auth;

import shpp.shuba.todo_list.dto.RegisterDTO;

public interface IAuthService {
    void register(RegisterDTO registerDTO);
}
