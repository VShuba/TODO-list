package shpp.shuba.todo_list.service.auth;

import shpp.shuba.todo_list.dto.AuthResponseDTO;
import shpp.shuba.todo_list.dto.LoginDTO;
import shpp.shuba.todo_list.dto.RegisterDTO;

public interface IAuthService {
    AuthResponseDTO register(RegisterDTO registerDTO);

    AuthResponseDTO login(LoginDTO loginDTO);
}
