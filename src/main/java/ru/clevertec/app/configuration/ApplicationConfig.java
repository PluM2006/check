package ru.clevertec.app.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;


@Configuration
@ComponentScan(basePackages = "ru.clevertec.app")
public class ApplicationConfig {

    @Bean
    public ResourceBundleMessageSource messageSource(){
        var source = new ResourceBundleMessageSource();
//        source.setDefaultEncoding("UTF-8");
        source.setBasenames("messages/messages");
        source.setUseCodeAsDefaultMessage(true);
        source.setDefaultEncoding("UTF-8");
        return source;
    }
}
