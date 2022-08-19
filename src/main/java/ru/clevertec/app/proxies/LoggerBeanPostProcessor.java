package ru.clevertec.app.proxies;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;
import ru.clevertec.app.annatation.Log;
import ru.clevertec.app.proxies.heandler.LogImplHandler;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

@Component
public class LoggerBeanPostProcessor implements BeanPostProcessor {

    private final Map<String, Class<?>> beansMap = new HashMap<>();
    @Lookup
    public LogImplHandler getLogImplHandler() {
        return null;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        Class<?> beanClass = bean.getClass();
        Method[] methods = beanClass.getMethods();
        for (Method method : methods) {
            Log annotation = method.getAnnotation(Log.class);
            if (annotation != null) {
                beansMap.put(beanName, beanClass);
            }
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Class<?> beanClass = beansMap.get(beanName);
        if (beanClass != null) {
            LogImplHandler logImplHandler = getLogImplHandler();
            logImplHandler.setLofImplHandler(bean);
            return Proxy.newProxyInstance(beanClass.getClassLoader(), beanClass.getInterfaces(), logImplHandler);
        }
        return bean;
    }
}
