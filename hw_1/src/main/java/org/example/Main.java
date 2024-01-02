package org.example;

import java.util.ArrayList;
import java.util.List;

import static org.example.PersonSerialization.deserializeList;
import static org.example.PersonSerialization.serializeList;

public class Main {
    public static void main(String[] args) {

        // Создаем список объектов Person
        List<Person> persons = new ArrayList<>();
        persons.add (new Person("John", "Doe", 25));
        persons.add (new Person("Jane", "Smith", 30));
        persons.add (new Person("Bob", "Johnson", 35));
        persons.add (new Person("Alice", "Williams", 40));
        persons.add (new Person("David", "Brown", 45));

        // Сериализация списка в JSON
        String json = serializeList(persons);
        System.out.println("Serialized List JSON: " + json);

        // Десериализация из JSON в список
        List<Person> deserializedList = deserializeList(json);
        System.out.println("Deserialized List: " + deserializedList);

        for (Person person : deserializedList) {
          System.out.println(person.toString());
        }
    }
}