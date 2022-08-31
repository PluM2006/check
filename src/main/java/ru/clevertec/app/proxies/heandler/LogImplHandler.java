package ru.clevertec.app.proxies.heandler;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.clevertec.app.constant.Constants;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
@Slf4j
public class LogImplHandler implements InvocationHandler {

    private final Gson gson;
    private Object bean;

    public LogImplHandler(Gson gson) {
        this.gson = gson;
    }

    public void setLogImplHandler(Object bean) {
        this.bean = bean;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object invoke = method.invoke(bean, args);
        log.info("Метод: {}", method.getName());
        log.info("Параметры: {}", gson.toJson(args));
        log.info("Результат: {}", gson.toJson(invoke));
        return invoke;
    }
}
