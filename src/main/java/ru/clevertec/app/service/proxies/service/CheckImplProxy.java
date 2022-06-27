package ru.clevertec.app.service.proxies.service;

import ru.clevertec.app.entity.Check;
import ru.clevertec.app.service.CheckInterface;
import ru.clevertec.app.service.impl.CheckImpl;
import ru.clevertec.app.service.proxies.heandler.CheckImplHandler;

import java.lang.reflect.Proxy;

public class CheckImplProxy implements CheckInterface {
    private static CheckInterface checkImpl;

    static {
        checkImpl = new CheckImpl();
        ClassLoader classLoader = checkImpl
                .getClass()
                .getClassLoader();
        Class<?>[] interfaces = checkImpl
                .getClass()
                .getInterfaces();
        checkImpl = (CheckInterface) Proxy.newProxyInstance(classLoader, interfaces, new CheckImplHandler(checkImpl));
    }

    @Override
    public Check getCheck(String[] args) {
        return checkImpl.getCheck(args);
    }

}
