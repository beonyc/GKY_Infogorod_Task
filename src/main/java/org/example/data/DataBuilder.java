package org.example.data;

import com.github.javafaker.Faker;
import lombok.AccessLevel;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
public class DataBuilder {
    private List<String> allProducts = List.of("Holy", "Colourful", "SuperSport XL", "Crossed", "Figueroa", "Cat socks");
    private String userName;
    private String firstMame;
    private String lastMame;
    private String email;
    private String password;
    @Getter(AccessLevel.PRIVATE)
    private Faker faker = new Faker();

    public DataBuilder() {
        userName = faker.name().username();
        firstMame = faker.name().firstName();
        lastMame = faker.name().lastName();
        email = faker.bothify("???????##@gmail.com");
        password = faker.regexify("[a-z1-9]{10}");
    }
}
