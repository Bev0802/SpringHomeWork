package org.example;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
public class PersonSerialization {
    private static final Gson gson = new Gson();

    // Метод для сериализации ArrayList<Person> в JSON
    public static String serializeList(List<Person> personList) {
        return gson.toJson(personList);
    }

    // Метод для десериализации JSON в ArrayList<Person>
    public static List<Person> deserializeList(String json) {
        Type type = new TypeToken<ArrayList<Person>>() {}.getType();
        return gson.fromJson(json, type);
    }
}
