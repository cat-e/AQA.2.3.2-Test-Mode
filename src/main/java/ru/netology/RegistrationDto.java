package ru.netology;

import com.github.javafaker.Faker;

import java.util.Locale;
import java.util.Random;

public class RegistrationDto {
    protected RegistrationDto(String name, String password, String s) {
    }

    public static class Login {
        public static String name(String locale) {
            Faker faker = new Faker(new Locale("en-US"));
            return new String(
                    faker.name().username()
            );
        }

        public static String password(String locale) {
            Faker faker = new Faker(new Locale("en-US"));
            return new String(
                    faker.internet().password()
            );
        }

        public static String generateRandomStatus() {
            final String[] strings = {
                    "active", "blocked"
            };
            Random random = new Random();
            String status = strings[random.nextInt(strings.length)];

            return status;
        }
    }
}