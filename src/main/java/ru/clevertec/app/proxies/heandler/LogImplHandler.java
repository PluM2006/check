package ru.clevertec.app.proxies.heandler;

import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.clevertec.app.constant.Constants;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

@RequiredArgsConstructor
public class LogImplHandler implements InvocationHandler {

    private final Gson gson;
    private final Object bean;


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
