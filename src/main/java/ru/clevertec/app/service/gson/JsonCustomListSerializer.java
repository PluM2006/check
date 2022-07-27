package ru.clevertec.app.service.gson;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import ru.clevertec.app.service.interfaces.CustomList;

import java.lang.reflect.Type;

public class JsonCustomListSerializer implements JsonSerializer<CustomList<?>> {

    @Override
    public JsonElement serialize(CustomList<?> src, Type typeOfSrc, JsonSerializationContext context) {
        JsonElement jsonElement = new JsonArray();
        for (int i = 0; i < src.size(); i++) {
            jsonElement
                    .getAsJsonArray()
                    .add((context.serialize(src.get(i))));
        }
        return jsonElement;
    }
}
