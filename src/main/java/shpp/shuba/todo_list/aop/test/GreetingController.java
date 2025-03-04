//package shpp.shuba.todo_list.aop.test;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.MessageSource;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.Locale;
//
//@RestController
//public class GreetingController {
//
//    @Autowired
//    private MessageSource messageSource;
//
//    @GetMapping("/greet")
//    public String greet(@RequestParam String name, @RequestParam(defaultValue = "en") String lang) {
//        Locale locale = new Locale(lang);
//        return messageSource.getMessage("welcome.message", new Object[]{name}, locale);
//    }
//}