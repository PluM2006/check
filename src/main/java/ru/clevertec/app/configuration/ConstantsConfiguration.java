package ru.clevertec.app.configuration;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("application")
@Getter
@Setter
@ToString
public class ConstantsConfiguration {

    private String pathProduct;
    private String pathCard;
    private String pathInvalidFileName;
    private String fileName;
    private String allDiscount;
}
