package work.liyue.aspect;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


/**
 * Created by hzliyue1 on 2016/7/13,0013.
 */
@Aspect
@Component
public class LogAspect {
    private static final Logger logger = LoggerFactory.getLogger(LogAspect.class);

    @Before("execution(* work.liyue.controller.IndexController.*(..))")
    public void beforeMethod(JoinPoint joinPoint) {
        StringBuilder sb = new StringBuilder();
        for (Object arg : joinPoint.getArgs()){
            sb.append("arg" + arg.toString() + "|");

        }

            logger.info("before Method" + sb.toString());

    }

    @After("execution(* work.liyue.controller.IndexController.*(..))")
    public void afterMethod(JoinPoint joinPoint) {
        logger.info("after Method");

    }
}
