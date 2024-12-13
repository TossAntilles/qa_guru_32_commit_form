package tests;

import org.junit.jupiter.api.Test;

import java.io.File;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class AutomationPracticeFormTestEnvironment extends TestEnvironmentBase {

    @Test
    void succesfullFormFillTest(){
        open("/automation-practice-form");
        //block ads
        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");

        // Form input

        //Personal data
        $("#userForm #firstName").setValue("John");
        $("#userForm #lastName").setValue("Doe");
        $("#userForm #userEmail").setValue("anonymous@anonymous.com");
        $("#userForm #userNumber").setValue("1234567890");

        //Gender
        $("#userForm #genterWrapper").$(byText("Other")).click();

        //Calendar
        $("#userForm #dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOptionContainingText("January");
        $(".react-datepicker__year-select").selectOptionByValue("1900");
        $(".react-datepicker__day.react-datepicker__day--001").click();

        //subjects string
        $("#userForm #subjectsInput").setValue("Maths");
        $("#userForm .subjects-auto-complete__menu").click();
        $("#userForm #subjectsInput").setValue("Arts");
        $("#userForm .subjects-auto-complete__menu").click();

        //subjects checkbox
        $("#userForm #hobbiesWrapper").$(byText("Sports")).click();
        $("#userForm #hobbiesWrapper").$(byText("Music")).click();

        //File upload
        $("#uploadPicture").uploadFile(new File("src/test/resources/image.jpg"));

        //Address Lists
        $("#userForm #currentAddress").setValue("Random Street, 1138");
        $("#userForm #react-select-3-input").setValue("NCR").pressEnter();
        $("#userForm #react-select-4-input").setValue("Delhi").pressEnter();

        // Form submit

        $("#userForm #submit").click();

        $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));

        $(".table").shouldHave(text("Student Name")).shouldHave(text("John Doe"));
        $(".table").shouldHave(text("Student Email")).shouldHave(text("anonymous@anonymous.com"));
        $(".table").shouldHave(text("Gender")).shouldHave(text("Other"));
        $(".table").shouldHave(text("Mobile")).shouldHave(text("1234567890"));
        $(".table").shouldHave(text("Date of Birth")).shouldHave(text("01 January,1900"));
        $(".table").shouldHave(text("Subjects")).shouldHave(text("Maths, Arts"));
        $(".table").shouldHave(text("Hobbies")).shouldHave(text("Sports, Music"));
        $(".table").shouldHave(text("Picture")).shouldHave(text("image.jpg"));
        $(".table").shouldHave(text("Address")).shouldHave(text("Random Street, 1138"));
        $(".table").shouldHave(text("State and City")).shouldHave(text("NCR Delhi"));

    }

}