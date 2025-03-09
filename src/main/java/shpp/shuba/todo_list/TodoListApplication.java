package shpp.shuba.todo_list;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TodoListApplication {

    public static void main(String[] args) {
        SpringApplication.run(TodoListApplication.class, args);
        // aop к контроллерам + логирование + i18n
        // понатыкать эндпоинты для секурити + метод update любой может изменить имя ЛЮБОГО айди, это не оч
        // при создания пользователя он все равно может дать себе роль админа, такое себе
        //
        // todo  переписать RequestBody на парамс чтобы формой ввод был, а не raw json
        // todo name to User
    }

}
