package ru.netology.web;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;

class RegistrationTest {

    @BeforeEach
    void openUrl() {
        open("http://localhost:9999");
    }

    @AfterEach
    void tearDown() {
        closeWindow();
    }

    @Test
    void correctRegistration() {
        open("http://localhost:9999");
        $("[placeholder='Город']").setValue("Москва");
        $("[name=name]").setValue("Василий Пупкин");
        $("[name='phone']").setValue("+79012345678");
        $(".checkbox__box").click();
        $$("button").find(exactText("Забронировать")).click();
        $(withText("Успешно!")).shouldBe(visible, Duration.ofMillis(15000));

    }

    @Test
    void incorrectTelNumber() {
        open("http://localhost:9999");
        $("[placeholder='Город']").setValue("Москва");
        $("[name=name]").setValue("Василий Пупкин");
        $("[name='phone']").setValue("+7901234");
        $(".checkbox__box").click();
        $$("button").find(exactText("Забронировать")).click();
        $(withText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.")).shouldBe(visible);

    }

    @Test
    void incorrectName() {
        open("http://localhost:9999");
        $("[placeholder='Город']").setValue("Москва");
        $("[name=name]").setValue("flkvkjewfvkj");
        $("[name='phone']").setValue("+79012345678");
        $(".checkbox__box").click();
        $$("button").find(exactText("Забронировать")).click();
        $(withText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.")).shouldBe(visible);

    }

    @Test
    void incorrectСity() {
        open("http://localhost:9999");
        $("[placeholder='Город']").setValue("лваомоцу");
        $("[name=name]").setValue("Василий Пупкин");
        $("[name='phone']").setValue("+79012345678");
        $(".checkbox__box").click();
        $$("button").find(exactText("Забронировать")).click();
        $(withText("Доставка в выбранный город недоступна")).shouldBe(visible);

    }
}
