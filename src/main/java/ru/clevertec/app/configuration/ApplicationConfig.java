package ru.clevertec.app.configuration;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import ru.clevertec.app.customlist.CustomList;
import ru.clevertec.app.gson.JsonCustomListSerializer;


@Configuration
@ComponentScan(basePackages = "ru.clevertec.app")
public class ApplicationConfig {

    @Bean
    public ResourceBundleMessageSource messageSource() {
        var source = new ResourceBundleMessageSource();
        source.setBasenames("messages/messages");
        source.setUseCodeAsDefaultMessage(true);
        source.setDefaultEncoding("UTF-8");
        return source;
    }

    @Bean
    public Gson gson() {
        return new
                GsonBuilder()
                .registerTypeAdapter(CustomList.class, new JsonCustomListSerializer())
                .create();
    }
}
