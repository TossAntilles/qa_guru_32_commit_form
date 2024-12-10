package pages;

import com.codeborne.selenide.SelenideElement;
import pages.components.AddressComponent;
import pages.components.CalendarComponent;
import pages.components.FileUploadComponent;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.executeJavaScript;

public class RegistrationPage {

    private static SelenideElement firstNameInput = $("#userForm #first_name"),
            lastNameInput = $("#userForm #last_name"),
            emailInput = $("#userForm #userEmail"),
            genderInput = $("#userForm #genterWrapper"),
            phoneInput = $("#userForm #userNumber"),
            dateOfBirthInput = $("userForm #dateOfBirthInput"),
            subjectsInput = $("#subjectsInput"),
            hobbyCheckbox = $("#hobbiesWrapper"),
            submitButton = $("#userForm #submit"),
            resultsHeader = $(".modal-title"), // хэдер таблицы
            resultsTable = $(".table-responsive"); // сама таблица результатов


    CalendarComponent calendarComponent = new CalendarComponent();
    FileUploadComponent fileUploadComponent = new FileUploadComponent();
    AddressComponent addressComponent = new AddressComponent();

    public RegistrationPage openPage() {
        open("/automation-practice-form");
        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");

        return this;
    }

    //форма ввода

    public RegistrationPage setFirstName(String value) {
        firstNameInput.setValue(value);

        return this;
    }

    public RegistrationPage setLastName(String value) {
        lastNameInput.setValue(value);

        return this;
    }

    public RegistrationPage setEmail(String value) {
        emailInput.setValue(value);

        return this;
    }

    public RegistrationPage setGender(String value) {
        genderInput.$(byText(value)).click();

        return this;
    }

    public RegistrationPage setPhone(String value) {
        phoneInput.setValue(value);

        return this;
    }

    public RegistrationPage setDateOfBirth(String day, String month, String year) {
        dateOfBirthInput.click();
        calendarComponent.setDate(day, month, year);

        return this;
    }

    public RegistrationPage selectSubjectsByInput(String subject) {
        subjectsInput.setValue(subject);

        return this;
    }

    public RegistrationPage selectHobbyByCheckBox(String hobby) {
        hobbyCheckbox.$(byText(hobby)).click();

        return this;
    }

    public RegistrationPage uploadPicture(String picture, String path) {
        fileUploadComponent.fileUpload(picture, path);

        return this;
    }

    public RegistrationPage addressField(String value) {
        addressComponent.addressField(value);

        return this;
    }

    public RegistrationPage selectState(String value) {
        addressComponent.selectState(value);

        return this;
    }

    public RegistrationPage selectCity(String value) {
        addressComponent.selectCity(value);

        return this;
    }

    public RegistrationPage submit() {
        submitButton.click();

        return this;
    }

    //результаты

    //успешный сабмит
    public RegistrationPage succesfulSubmit(String header) {
        resultsHeader.shouldHave(text(header));

        return this;
    }

    public RegistrationPage checkResultTable(String key, String value) {
        resultsTable.$(byText(key)).sibling(0)
                .shouldHave(text(value));

        return this;
    }

    //таблица - заголовок
    //таблица - значения

    //не успешный сабмит - ошибки, нет таблицы
}
