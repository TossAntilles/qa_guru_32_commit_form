import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;

import static com.codeborne.selenide.Selenide.*;

public class AutomationPracticeFormTest {

    @BeforeAll
    static void beforeAll(){
        Configuration.browserSize = "1920x1080";
        Configuration.pageLoadStrategy = "eager";
        Configuration.baseUrl = "https://demoqa.com";
    }

    @Test
    void succesfullFormFill(){
        open("/automation-practice-form");
        $("#userForm #firstName").setValue("John");
        $("#userForm #lastName").setValue("Doe");
        $("#userForm #userEmail").setValue("anonymous@anonymous.com");

        //$("#userForm #gender-radio-3").click(); //Cannot change invisible element?
        $("#userForm [for=gender-radio-3]").click();

        $("#userForm #userNumber").setValue("1234567890");

        //Calendar
        $("#userForm #dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOptionByValue("0");
        $(".react-datepicker__year-select").selectOptionByValue("1900");
        $(".react-datepicker__day.react-datepicker__day--001").click();

        //subjects
        $("#userForm #subjectsInput").setValue("Maths");
        $("#userForm .subjects-auto-complete__menu").click();
        $("#userForm #subjectsInput").setValue("Arts");
        $("#userForm .subjects-auto-complete__menu").click();

        //Checkbox
        //$("#userForm #hobbies-checkbox-1").setSelected(true);  //Cannot change invisible element?
        //$("#userForm #hobbies-checkbox-3").setSelected(true);
        $("#userForm [for=hobbies-checkbox-1]").click();
        $("#userForm [for=hobbies-checkbox-3]").click();

        //File upload
        $("#uploadPicture").uploadFile(new File("src/test/data/image.jpg"));

        //Address Lists
        $("#userForm #currentAddress").setValue("Random Street, 1138");
        $("#userForm #react-select-3-input").setValue("NCR").pressEnter();
        $("#userForm #react-select-4-input").setValue("Delhi").pressEnter();

        sleep(10000);


    }
}
