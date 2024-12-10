package pages.components;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.cssValue;

public class FieldValidationComponent {

    static String errorIcon = "url(\"data:image/svg+xml,%3csvg xmlns='http://www.w3.org/2000/svg' width='12' height='12' fill='none' stroke='%23dc3545' viewBox='0 0 12 12'%3e%3ccircle cx='6' cy='6' r='4.5'/%3e%3cpath stroke-linejoin='round' d='M5.8 3.6h.4L6 6.5z'/%3e%3ccircle cx='6' cy='8.2' r='.6' fill='%23dc3545' stroke='none'/%3e%3c/svg%3e\")";
    static String correctIcon = "url(\"data:image/svg+xml,%3csvg xmlns='http://www.w3.org/2000/svg' width='12' height='12' fill='none' stroke='%23dc3545' viewBox='0 0 12 12'%3e%3ccircle cx='6' cy='6' r='4.5'/%3e%3cpath stroke-linejoin='round' d='M5.8 3.6h.4L6 6.5z'/%3e%3ccircle cx='6' cy='8.2' r='.6' fill='%23dc3545' stroke='none'/%3e%3c/svg%3e\")";

    public void fieldError(SelenideElement field) {
        field.shouldHave(cssValue("background-image", errorIcon));
        field.shouldHave(cssValue("border-bottom-color", "rgba(220, 53, 69, 1)"));
        field.shouldHave(cssValue("border-left-color", "rgba(220, 53, 69, 1)"));
        field.shouldHave(cssValue("border-right-color", "rgba(220, 53, 69, 1)"));
        field.shouldHave(cssValue("border-top-color", "rgba(220, 53, 69, 1)"));

    }

    public void fieldErrorFixed(SelenideElement field) {
        field.shouldHave(cssValue("background-image", correctIcon));
        field.shouldHave(cssValue("border-bottom-color", "rgba(40, 167, 69, 1)"));
        field.shouldHave(cssValue("border-left-color", "rgba(40, 167, 69, 1)"));
        field.shouldHave(cssValue("border-right-color", "rgba(40, 167, 69, 1)"));
        field.shouldHave(cssValue("border-top-color", "rgba(40, 167, 69, 1)"));

    }
}
