package ru.netology.test;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataGenerator;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class AuthTest {

    @BeforeAll
    static void setUpValid() {
    }

    @Test
    void shouldLogin() {
        open("http://localhost:9999/");
        $("[data-test-id=login] input").setValue(DataGenerator.ValidUser.generateValidUser().getLogin());
        $("[data-test-id=password] input").setValue(DataGenerator.ValidUser.generateValidUser().getPassword());
        $("button.button").click();
        $("h2").should(exist);
    }


    @Test
    void shouldNotLoginAsBlocked() {
        open("http://localhost:9999/");
        $("[data-test-id=login] input").setValue(DataGenerator.InvalidUser.generateInvalidUser("en").getLogin());
        $("[data-test-id=password] input").setValue(DataGenerator.InvalidUser.generateInvalidUser("en").getPassword());
        $("button.button").click();
        $("[data-test-id=error-notification]").shouldHave(text("Ошибка"));
    }

    @Test
    void shouldNotLoginWithWrongPassword() {
        open("http://localhost:9999/");
        $("[data-test-id=login] input").setValue(DataGenerator.ValidUser.generateValidUser().getLogin());
        $("[data-test-id=password] input").setValue(DataGenerator.InvalidUser.generateInvalidUser("en").getPassword());
        $("button.button").click();
        $("[data-test-id=error-notification]").shouldHave(text("Ошибка"));
    }

    @Test
    void shouldNotLoginWithWrongLogin() {
        open("http://localhost:9999/");
        $("[data-test-id=login] input").setValue(DataGenerator.InvalidUser.generateInvalidUser("en").getLogin());
        $("[data-test-id=password] input").setValue(DataGenerator.ValidUser.generateValidUser().getPassword());
        $("button.button").click();
        $("[data-test-id=error-notification]").shouldHave(text("Ошибка"));
    }
}