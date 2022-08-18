package ru.clevertec.app.proxies.heandler;

import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.clevertec.app.constant.Constants;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class LogImplHandler implements InvocationHandler {

    private final Gson gson;
    private final Object classBean;

    public LogImplHandler(Object classBean, Gson gson){
        this.classBean = classBean;
        this.gson = gson;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Logger log = LoggerFactory.getLogger(classBean.getClass());
        Object invoke = method.invoke(classBean, args);
        log.info(Constants.METHOD, method.getName());
        log.info(Constants.PARAMETERS, gson.toJson(args));
        log.info(Constants.RESULT, gson.toJson(invoke));
        return invoke;
    }
}
