package pages;

import com.codeborne.selenide.SelenideElement;
import pages.components.AddressComponent;
import pages.components.CalendarComponent;
import pages.components.FieldValidationComponent;
import pages.components.FileUploadComponent;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.executeJavaScript;
import static com.codeborne.selenide.logevents.SelenideLogger.step;

public class RegistrationPage {

    private static SelenideElement firstNameInput = $("#firstName"),
            lastNameInput = $("#lastName"),
            emailInput = $("#userEmail"),
            genderInput = $("#genterWrapper"),
            phoneInput = $("#userNumber"),
            dateOfBirthInput = $("#dateOfBirthInput"),
            subjectsInput = $("#subjectsInput"),
            subjectsDropdown = $(".subjects-auto-complete__menu"),
            hobbyCheckbox = $("#hobbiesWrapper"),
            submitButton = $("#submit"),
            resultsHeader = $(".modal-title"), // хэдер таблицы
            resultsTable = $(".table-responsive"); // сама таблица результатов
    private static String pictureUploadButton = "#uploadPicture";

    CalendarComponent calendarComponent = new CalendarComponent();
    FileUploadComponent fileUploadComponent = new FileUploadComponent();
    AddressComponent addressComponent = new AddressComponent();
    FieldValidationComponent fieldValidationComponent= new FieldValidationComponent();

    static String errorIcon = "url(\"data:image/svg+xml,%3csvg xmlns='http://www.w3.org/2000/svg' width='12' height='12' fill='none' stroke='%23dc3545' viewBox='0 0 12 12'%3e%3ccircle cx='6' cy='6' r='4.5'/%3e%3cpath stroke-linejoin='round' d='M5.8 3.6h.4L6 6.5z'/%3e%3ccircle cx='6' cy='8.2' r='.6' fill='%23dc3545' stroke='none'/%3e%3c/svg%3e\")";

    public RegistrationPage openPage() {
        step("Открываем страницу с формой", () -> {
            open("/automation-practice-form");
            executeJavaScript("$('#fixedban').remove()");
            executeJavaScript("$('#RightSide_Advertisement').remove()");
            executeJavaScript("$('footer').remove()");
        });
        return this;
    }

    //форма ввода

    public RegistrationPage setFirstName(String value) {
        step("Заполняем Имя", () -> {
            firstNameInput.setValue(value);
        });
        return this;
    }

    public RegistrationPage setLastName(String value) {
        step("Заполняем Фамилию", () -> {
            lastNameInput.setValue(value);
        });
        return this;
    }

    public RegistrationPage setEmail(String value) {
        step("Заполняем email", () -> {
            emailInput.setValue(value);
        });
        return this;
    }

    public RegistrationPage setGender(String value) {
        step("Заполняем пол", () -> {
            genderInput.$(byText(value)).click();
        });
        return this;
    }

    public RegistrationPage setPhone(String value) {
        step("Заполняем номер телефона", () -> {
            phoneInput.setValue(value);
        });
        return this;
    }

    public RegistrationPage setDateOfBirth(String day, String month, String year) {
        step("Заполняем дату рождения", () -> {
            dateOfBirthInput.click();
            calendarComponent.setDate(day, month, year);
        });
        return this;
    }

    public RegistrationPage selectSubjectsByInput(String subject) {
        step("Заполняем интересы (поле)", () -> {
            subjectsInput.setValue(subject);
            subjectsDropdown.click();
        });
        return this;
    }

    public RegistrationPage selectHobbyByCheckBox(String hobby) {
        step("Заполняем интересы (чекбоксы)", () -> {
            hobbyCheckbox.$(byText(hobby)).click();
        });
        return this;
    }

    public RegistrationPage uploadPicture(String path) {
        step("Загружаем изображение", () -> {
            fileUploadComponent.fileUpload(pictureUploadButton, path);
        });
        return this;
    }

    public RegistrationPage addressField(String value) {
        step("Заполняем адрес", () -> {
            addressComponent.addressField(value);
        });
        return this;
    }

    public RegistrationPage selectState(String value) {
        step("Выбираем провинцию", () -> {
            addressComponent.selectState(value);
        });
        return this;
    }

    public RegistrationPage selectCity(String value) {
        step("Выбираем город", () -> {
            addressComponent.selectCity(value);
        });
        return this;
    }

    public RegistrationPage submit() {
        step("Отправка формы", () -> {
            submitButton.click();

        });
        return this;
    }

    //успешный сабмит

    public RegistrationPage successfulSubmit(String header) {
        step("Проверка заголовка таблицы с результатами", () -> {
            resultsHeader.shouldHave(text(header));
        });
        return this;
    }

    public RegistrationPage checkResultTable(String key, String value) {
        step("Проверка полей таблицы с результатами", () -> {
            resultsTable.$(byText(key)).sibling(0)
                    .shouldHave(exactText(value));
        });
        return this;
    }

    //неуспешный сабмит: нет таблицы, подсветка полей

    public RegistrationPage unsuccessfulSubmit() {
        step("Проверка отсутствия таблицы при неудачной отправке формы", () -> {
            resultsHeader.shouldNotBe(visible);
            resultsTable.shouldNotBe(visible);
        });
        return this;
    }

    public RegistrationPage firstNameEmpty() {
        step("Валидация поля Имя", () -> {
            fieldValidationComponent.fieldError(firstNameInput);
        });
        return this;
    }

    public RegistrationPage lastNameEmpty() {
        step("Валидация поля Фамилия", () -> {
            fieldValidationComponent.fieldError(lastNameInput);
        });
        return this;
    }

    public RegistrationPage genderNotSelected() {
        step("Валидация выбора пола", () -> {
            genderInput.$("label").shouldHave(cssValue("color", "rgba(220, 53, 69, 1)"));
        });
        return this;
    }

    public RegistrationPage phoneEmpty() {
        step("Валидация номера телефона", () -> {
            fieldValidationComponent.fieldError(phoneInput);
        });
        return this;
    }

}
