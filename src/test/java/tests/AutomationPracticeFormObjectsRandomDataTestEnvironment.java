package tests;

import helpers.Attach;
import io.qameta.allure.*;
import org.junit.jupiter.api.*;
import pages.RegistrationPage;
import utils.RegistrationPageRandomData;

import static io.qameta.allure.Allure.step;

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
    @Tag("submitTest")
    @Feature("Проверка формы")
    @Issue("Валидация заполнения формы")
    @Story("Полное заполнение случайными данными")
    @Owner("Toss Antilles")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Полное заполнение случайными данными.")
    void successfulFullFormFillTest(){

        step("Открытие формы", () -> {
            registrationPage.openPage();
        });
        step("Заполнениее формы", () -> {
            registrationPage.setFirstName(firstName)
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
                    .submit();
        });
        step("Валидация сабмита", () -> {
            registrationPage.successfulSubmit("Thanks for submitting the form")
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
        });

    }

    @Test
    @Tag("submitTest")
    @Feature("Проверка формы")
    @Issue("Валидация заполнения формы")
    @Story("Заполнение минимальным набором данных")
    @Owner("Toss Antilles")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Зполнение минимальным набором данных.")
    void succesfullMinimalFormFillTest() {

        step("Открытие формы", () -> {
            registrationPage.openPage();
        });
        step("Заполнениее формы", () -> {
            registrationPage.setFirstName(firstName)
                    .setLastName(lastName)
                    .setGender(gender)
                    .setPhone(phone)
                    .submit();
        });
        step("Валидация сабмита", () -> {
            registrationPage.successfulSubmit("Thanks for submitting the form")
                    .checkResultTable("Student Name", firstName + " " + lastName)
                    .checkResultTable("Student Email", "")
                    .checkResultTable("Gender", gender)
                    .checkResultTable("Mobile", phone)
                    .checkResultTable("Subjects", "")
                    .checkResultTable("Hobbies", "")
                    .checkResultTable("Picture", "")
                    .checkResultTable("Address", "")
                    .checkResultTable("State and City", "");
        });
    }

    @Test
    @Tag("validationTest")
    @Feature("Проверка формы")
    @Issue("Валидация заполнения формы")
    @Story("Сабмит пустой формы")
    @Owner("Toss Antilles")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Сабмит пустой формы.")
    void requiredFieldsEmptyTest() {
        step("Открытие формы", () -> {
            registrationPage.openPage();
        });
        step("Сабмит пустой формы", () -> {
            registrationPage.submit();
        });
        step("Проверка отсутствия результатов и валидации полей", () -> {
            registrationPage.unsuccessfulSubmit()
                    .firstNameEmpty()
                    .lastNameEmpty()
                    .genderNotSelected()
                    .phoneEmpty();
        });
    }

}
