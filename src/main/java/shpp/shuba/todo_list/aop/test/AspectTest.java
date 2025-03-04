package shpp.shuba.todo_list.aop.test;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AspectTest {

    // якщо є собака то чекне анотацію
    @Pointcut("@within(org.springframework.stereotype.Controller)")
    public void isController(){

    }

    // якщо є БЕЗ собаки то чекне саме імя класса. * типу префікс
    @Pointcut("within(org.springframework.stereotype.*Service)")
    public void isService(){

    }
}
