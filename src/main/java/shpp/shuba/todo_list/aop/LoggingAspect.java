package shpp.shuba.todo_list.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
@Slf4j
public class LoggingAspect {

    @Pointcut("@within(org.springframework.stereotype.Controller)")
    public void isController() {
    }
    @Pointcut("@within(org.springframework.web.bind.annotation.RestControllerAdvice)")
    public void isRestController() {
    }

    @Pointcut("within(@org.springframework.stereotype.Service *)")
    public void isService() {
    }

    // логую всі методи перед викликом
    @Before("isController() || isService() || isRestController()")
    public void logBefore(JoinPoint joinPoint) {
        log.info("Call method: {}  - with args: {}", joinPoint.getSignature().toShortString(), Arrays.toString(joinPoint.getArgs()));
    }

    // логую успішне виконання
    @AfterReturning(pointcut = "isController() || isService()", returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        log.info("Called method: {} - Result: {}", joinPoint.getSignature().toShortString(), result);
    }
}

