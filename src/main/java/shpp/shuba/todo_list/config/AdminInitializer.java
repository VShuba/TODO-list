package shpp.shuba.todo_list.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import shpp.shuba.todo_list.models.MyUser;
import shpp.shuba.todo_list.models.Role;
import shpp.shuba.todo_list.models.RoleName;
import shpp.shuba.todo_list.repository.RoleRepository;
import shpp.shuba.todo_list.repository.UserRepository;

import java.util.Set;

@Component
@RequiredArgsConstructor
public class AdminInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {

        Role adminRole = roleRepository.findByName(RoleName.ROLE_ADMIN)
                .orElseGet(() -> roleRepository.save(Role.builder()
                        .name(RoleName.ROLE_ADMIN)
                        .build()));

        if (!userRepository.existsByUsername("admin")) {
            MyUser admin = MyUser.builder()
                    .username("admin")
                    .email("admin@example.com")
                    .password(passwordEncoder.encode("admin"))
                    .roles(Set.of(adminRole))
                    .build();

            userRepository.save(admin);
        }
    }

}
