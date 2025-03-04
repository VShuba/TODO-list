//package shpp.shuba.todo_list.aop;
//
//import jakarta.servlet.http.HttpServletRequest;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.annotation.*;
//import org.springframework.context.i18n.LocaleContextHolder;
//import org.springframework.stereotype.Component;
//
//import java.util.Locale;
//
//@Aspect
//@Component
//@Slf4j
//@RequiredArgsConstructor
//public class LocaleAspect {
//
//    private final HttpServletRequest request;
//
//    @Pointcut("@within(org.springframework.web.bind.annotation.RestController)")
//    public void isRestController() {
//    }
//
//    @Before("isRestController()")
//    public void setLocale(JoinPoint joinPoint) {
//        String language = request.getHeader("Accept-Language");
//        if (language == null || language.isBlank()) {
//            language = request.getParameter("lang"); // альтернативний варіант
//        }
//
//        Locale locale = (language != null) ? Locale.forLanguageTag(language) : Locale.ENGLISH;
//        LocaleContextHolder.setLocale(locale);
//
//        log.info("Set locale to {} for method {}", locale, joinPoint.getSignature().toShortString());
//    }
//
//    @After("isRestController()")
//    public void clearLocale(JoinPoint joinPoint) {
//        LocaleContextHolder.resetLocaleContext();
//        log.info("Cleared locale after method {}", joinPoint.getSignature().toShortString());
//    }
//}
