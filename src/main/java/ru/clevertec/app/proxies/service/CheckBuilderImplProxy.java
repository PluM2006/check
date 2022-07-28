package ru.clevertec.app.proxies.service;

import ru.clevertec.app.entity.Card;
import ru.clevertec.app.entity.Cashier;
import ru.clevertec.app.entity.CheckItem;
import ru.clevertec.app.entity.Shop;
import ru.clevertec.app.service.check.interfaces.CheckBuilderInterface;
import ru.clevertec.app.service.customlist.CustomList;
import ru.clevertec.app.service.check.impl.CheckBuilderImpl;
import ru.clevertec.app.proxies.heandler.CheckImplHandler;

import java.lang.reflect.Proxy;

public class CheckBuilderImplProxy implements CheckBuilderInterface {
    private static CheckBuilderInterface checkImpl;

    static {
        checkImpl = new CheckBuilderImpl();
        ClassLoader classLoader = checkImpl
                .getClass()
                .getClassLoader();
        Class<?>[] interfaces = checkImpl
                .getClass()
                .getInterfaces();
        checkImpl = (CheckBuilderInterface) Proxy.newProxyInstance(classLoader, interfaces, new CheckImplHandler(checkImpl));
    }

    @Override
    public String getCheck(CustomList<CheckItem>checkItems, Card card, Shop shop, Cashier cashier) {
        return checkImpl.getCheck(checkItems, card, shop, cashier);
    }

}
