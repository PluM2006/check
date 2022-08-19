package ru.clevertec.app.proxies.heandler;

import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.clevertec.app.constant.Constants;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class LogImplHandler implements InvocationHandler {

    private final Gson gson;
    private Object bean;

    public LogImplHandler(Gson gson) {
        this.gson = gson;
    }

    public void setLofImplHandler(Object bean) {
        this.bean = bean;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Logger log = LoggerFactory.getLogger(bean.getClass());
        Object invoke = method.invoke(bean, args);
        log.info(Constants.METHOD, method.getName());
        log.info(Constants.PARAMETERS, gson.toJson(args));
        log.info(Constants.RESULT, gson.toJson(invoke));
        return invoke;
    }
}
