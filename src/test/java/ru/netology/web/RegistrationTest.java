package ru.netology.web;

import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

class RegistrationTest {

    @Test
    void correctRegistration() {
        open("http://localhost:9999");
        $("[class=input__control]").setValue("Москва");
        $("[name=name]").setValue("Василий Пупкин");
        $("[name='phone']").setValue("+79012345678");
        $("span.checkbox__box").click();
        $$("button").find(exactText("Забронировать")).click();
        $(withText("Успешно!")).shouldBe(visible, Duration.ofMillis(15000));

    }
    @Test
    void incorrectTelNumber() {



    }


}

