package tests;

import helpers.Attach;
import io.qameta.allure.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import pages.RegistrationPage;
import utils.RegistrationPageRandomData;

public class AutomationPracticeFormObjectsRandomDataTestEnvironment extends BeforeAll {

    RegistrationPage registrationPage = new RegistrationPage();
    RegistrationPageRandomData randData = new RegistrationPageRandomData();

    @AfterEach
    void addAttachments() {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();
    }

    String firstName = randData.firstName(),
            lastName = randData.lastName(),
            email = randData.email(),
            gender = randData.gender(),
            phone = randData.phone(),
            day = randData.day(),
            month = randData.month(),
            year = randData.year(),
            subject = randData.subject(),
            hobby = randData.hobby(),
            picture = randData.picture(),
            address = randData.address(),
            state = randData.state(),
            cityRes = randData.cityRes(state);

    @Test
    @Feature("Проверка формы")
    @Issue("Валидация заполнения формы")
    @Story("Полное заполнение случайными данными")
    @Owner("Toss Antilles")
    @Severity(SeverityLevel.NORMAL)
    @Link(value = "Страница формы", url = "https://demoqa.com/automation-practice-form")
    @DisplayName("Полное заполнение случайными данными.")
    void succesfullFullFormFillTest(){

        registrationPage.openPage()
                //заполнение формы
                .setFirstName(firstName)
                .setLastName(lastName)
                .setEmail(email)
                .setGender(gender)
                .setPhone(phone)
                .setDateOfBirth(day, month, year)
                .selectSubjectsByInput(subject)
                .selectHobbyByCheckBox(hobby)
                .uploadPicture(picture)
                .addressField(address)
                .selectState(state)
                .selectCity(cityRes)
                .submit()
                //результаты
                .successfulSubmit("Thanks for submitting the form")
                .checkResultTable("Student Name", firstName + " " + lastName)
                .checkResultTable("Student Email", email)
                .checkResultTable("Gender", gender)
                .checkResultTable("Mobile", phone)
                .checkResultTable("Date of Birth", day + " " + month + "," + year)
                .checkResultTable("Subjects", subject)
                .checkResultTable("Hobbies", hobby)
                .checkResultTable("Picture", picture)
                .checkResultTable("Address", address)
                .checkResultTable("State and City", state + " " + cityRes);
    }

    @Test
    @Feature("Проверка формы")
    @Issue("Валидация заполнения формы")
    @Story("Заполнение минимальным набором данных")
    @Owner("Toss Antilles")
    @Severity(SeverityLevel.NORMAL)
    @Link(value = "Страница формы", url = "https://demoqa.com/automation-practice-form")
    @DisplayName("Зполнение минимальным набором данных.")
    void succesfullMinimalFormFillTest() {

        registrationPage.openPage()
                //заполнение формы
                .setFirstName(firstName)
                .setLastName(lastName)
                .setGender(gender)
                .setPhone(phone)
                .submit()
                //результаты, в том числе пустые поля
                .successfulSubmit("Thanks for submitting the form")
                .checkResultTable("Student Name", firstName + " " + lastName)
                .checkResultTable("Student Email", "")
                .checkResultTable("Gender", gender)
                .checkResultTable("Mobile", phone)
                .checkResultTable("Subjects", "")
                .checkResultTable("Hobbies", "")
                .checkResultTable("Picture", "")
                .checkResultTable("Address", "")
                .checkResultTable("State and City", "");
    }

    @Test
    @Feature("Проверка формы")
    @Issue("Валидация заполнения формы")
    @Story("Сабмит пустой формы")
    @Owner("Toss Antilles")
    @Severity(SeverityLevel.NORMAL)
    @Link(value = "Страница формы", url = "https://demoqa.com/automation-practice-form")
    @DisplayName("Сабмит пустой формы.")
    void requiredFieldsEmptyTest() {
        registrationPage.openPage()
                //пустая форма
                .submit()
                .unsuccessfulSubmit()
                //подсветка полей
                .firstNameEmpty()
                .lastNameEmpty()
                .genderNotSelected()
                .phoneEmpty();

    }

}
