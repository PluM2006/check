package ru.clevertec.app.proxies.service;

import ru.clevertec.app.check.CheckBuilderInterface;
import ru.clevertec.app.entity.Card;
import ru.clevertec.app.proxies.heandler.CheckImplHandler;

import java.lang.reflect.Proxy;
import java.util.Map;


public class CheckBuilderImplProxy implements CheckBuilderInterface {

    private static CheckBuilderInterface checkImpl;

    static {
        ClassLoader classLoader = checkImpl
                .getClass()
                .getClassLoader();
        Class<?>[] interfaces = checkImpl
                .getClass()
                .getInterfaces();
        checkImpl = (CheckBuilderInterface) Proxy.newProxyInstance(classLoader, interfaces, new CheckImplHandler(checkImpl));
    }

    @Override
    public String getCheck(Map<Long, Integer> mapCheckItems, Card card) {
        return checkImpl.getCheck(mapCheckItems, card);
    }

}
