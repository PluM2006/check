package ru.clevertec.app.aspects;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import ru.clevertec.app.anatations.LoggerLog;
import ru.clevertec.app.service.CustomList;
import ru.clevertec.app.service.gson.JsonCustomListSerializer;
import ru.clevertec.app.service.impl.CustomArrayList;

@Aspect
public class LoggerAspect {
    @Pointcut("execution(@ru.clevertec.app.anatations.* * *(..))")
    private void methodsToBeProfiled() {
    }

    @AfterReturning(pointcut = "methodsToBeProfiled()", returning = "o")
    public void sendLog(JoinPoint joinPoint, Object o) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        LoggerLog annotation = ((MethodSignature) joinPoint.getSignature()).getMethod().getAnnotation(LoggerLog.class);
        Logger log = LogManager.getLogger(joinPoint.getTarget());
        Gson gson = new
                GsonBuilder()
                .registerTypeAdapter(CustomList.class, new JsonCustomListSerializer())
                .registerTypeAdapter(CustomArrayList.class, new JsonCustomListSerializer())
                .create();
        if (signature.getMethod().isAnnotationPresent(LoggerLog.class)) {
//            LoggerLog annotation = signature.getMethod().getAnnotation(LoggerLog.class);
            log.warn("Метод: " + signature.getMethod().getName());
            log.info("Параметры: " + gson.toJson(joinPoint.getArgs()));
            log.info("Результат: " + gson.toJson(o));
        }
    }
}