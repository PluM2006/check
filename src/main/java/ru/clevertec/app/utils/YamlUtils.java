package ru.clevertec.app.utils;

import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import java.io.InputStream;

public class YamlUtils {

    private static final Yaml yaml = new Yaml(new Constructor(YamlProperties.class));
    private static final String PATH_YAML = "application.yaml";
    private static final YamlProperties YAML_PROPERTIES;

    static {
        InputStream inputStream = YamlUtils.class
                .getClassLoader()
                .getResourceAsStream(PATH_YAML);
        YAML_PROPERTIES = yaml.load(inputStream);
    }

    public static YamlProperties getYamlProperties() {
        return YAML_PROPERTIES;
    }


}
