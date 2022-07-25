package ru.clevertec.app.service.proxies.heandler;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.clevertec.app.service.interfaces.CheckInterface;
import ru.clevertec.app.service.interfaces.CustomList;
import ru.clevertec.app.service.gson.JsonCustomListSerializer;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class CheckImplHandler implements InvocationHandler {

    Gson gson = new
            GsonBuilder()
            .registerTypeAdapter(CustomList.class, new JsonCustomListSerializer())
            .create();
    private static final Logger log = LoggerFactory.getLogger(CheckImplHandler.class);
    private final CheckInterface checkImpl;

    public CheckImplHandler(CheckInterface checkImpl) {
        this.checkImpl = checkImpl;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object invoke = method.invoke(checkImpl, args);
        log.info("Метод: {}",method.getName());
        log.info("Параметры: {}",gson.toJson(args));
        log.info("Результат: {}",gson.toJson(invoke));
        return invoke;
    }
}
