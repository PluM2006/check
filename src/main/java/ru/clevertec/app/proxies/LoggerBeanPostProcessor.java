package ru.clevertec.app.proxies;

import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;
import ru.clevertec.app.annatation.Log;
import ru.clevertec.app.proxies.heandler.LogImplHandler;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

@Component
@RequiredArgsConstructor
public class LoggerBeanPostProcessor implements BeanPostProcessor {

    private final Map<String, Class<?>> beansMap = new HashMap<>();
    private final Gson gson;

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
            return Proxy.newProxyInstance(beanClass.getClassLoader(), beanClass.getInterfaces(), new LogImplHandler(bean, gson));
        }
        return bean;
    }
}
