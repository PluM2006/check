package ru.clevertec.app.proxies.heandler;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
@Slf4j
public class LogImplHandler implements InvocationHandler {

    private final Gson gson;
    private static final String confidentialMarkerText = "LOG_PROXY";
    private Object bean;

    public LogImplHandler(Gson gson) {
        this.gson = gson;
    }
    public void setLogImplHandler(Object bean) {
        this.bean = bean;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Marker marker = MarkerFactory.getMarker(confidentialMarkerText);
        Object invoke = method.invoke(bean, args);
        log.info(marker, "Метод: {}", method.getName());
        log.info(marker, "Параметры: {}", gson.toJson(args));
        log.info(marker, "Результат: {}", gson.toJson(invoke));
        return invoke;
    }
}
