package ru.netology.data;

import com.github.javafaker.Faker;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import java.util.Locale;

import static io.restassured.RestAssured.given;

public class DataGenerator {
    private DataGenerator() {
    }

    public static class ValidUser {
        public ValidUser() {
        }

        public static RegistrationDto generateValidUser() {
            return new RegistrationDto(
                    "vasya",
                    "password",
                    "active"
            );
        }
    }

    public static class InvalidUser {
        private InvalidUser() {
        }
        public static RegistrationDto generateInvalidUser(String locale) {
            Faker faker = new Faker(new Locale(locale));
            return new RegistrationDto(
                    faker.name().username(),
                    faker.internet().password(),
                    "blocked"
            );
        }

    }

    public static RequestSpecification requestSpec = new RequestSpecBuilder()
            .setBaseUri("http://localhost")
            .setPort(9999)
            .setAccept(ContentType.JSON)
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();

    void setUpValid() {
        given().spec(requestSpec)
                .body(new ValidUser())
        .when()
                .post("/api/system/users")
        .then()
                 .statusCode(200);
    }

    void setUpInvalid() {
        given().spec(requestSpec)
                .body(new InvalidUser())
                .when()
                .post("/api/system/users")
                .then()
                .statusCode(200);
    }
}
