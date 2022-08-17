package ru.clevertec.app.proxies;

import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;
import ru.clevertec.app.annatation.Logger;
import ru.clevertec.app.check.impl.CheckBuilderImpl;
import ru.clevertec.app.proxies.heandler.CheckImplHandler;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class LoggerPostProcessor implements BeanPostProcessor {

    private final Map<String, Class<?>> beansMap = new HashMap<>();
    private final Gson gson;

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        Class<?> beanClass = bean.getClass();
        Method[] methods = beanClass.getMethods();
        for (Method method : methods) {
            Logger annotation = method.getAnnotation(Logger.class);
            if (annotation!=null){
                beansMap.put(beanName, beanClass);
            }
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Class<?> beanClass = beansMap.get(beanName);
        if (beanClass!=null){
            return Proxy.newProxyInstance(beanClass.getClassLoader(), beanClass.getInterfaces(), (proxy, method, args) -> {
                org.slf4j.Logger log = LoggerFactory.getLogger(bean.getClass());
                Object invoke = method.invoke(bean, args);
                log.info("Метод: {}", method.getName());
                log.info("Параметры: {}", gson.toJson(args));
                log.info("Результат: {}", gson.toJson(invoke));
                return invoke;
            });
        }
        return bean;
    }
}
