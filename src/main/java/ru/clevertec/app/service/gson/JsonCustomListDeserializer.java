package ru.clevertec.app.service.gson;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import ru.clevertec.app.service.CustomList;

import java.lang.reflect.Type;

public class JsonCustomListDeserializer implements JsonSerializer<CustomList<?>> {

    @Override
    public JsonElement serialize(CustomList<?> src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject obj = new JsonObject();
        for (int i = 0; i < src.size(); i++) {
            obj.addProperty("name ",src.get(i).toString());
        }

        return obj;
    }
}
