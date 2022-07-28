package ru.clevertec.app.proxies.heandler;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.clevertec.app.service.check.interfaces.CheckBuilderInterface;
import ru.clevertec.app.service.customlist.CustomList;
import ru.clevertec.app.gson.JsonCustomListSerializer;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class CheckImplHandler implements InvocationHandler {

    Gson gson = new
            GsonBuilder()
            .registerTypeAdapter(CustomList.class, new JsonCustomListSerializer())
            .create();
    private static final Logger log = LoggerFactory.getLogger(CheckImplHandler.class);
    private final CheckBuilderInterface checkImpl;

    public CheckImplHandler(CheckBuilderInterface checkImpl) {
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
