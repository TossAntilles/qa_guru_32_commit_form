package tests;

import org.junit.jupiter.api.Test;
import pages.RegistrationPage;

public class AutomationPracticeFormObjectsTestEnvironment extends TestEnvironmentBase {

    RegistrationPage registrationPage = new RegistrationPage();

    @Test
    void succesfullFullFormFillTest(){
        registrationPage.openPage()
                //заполнение формы
                .setFirstName("John")
                .setLastName("Doe")
                .setEmail("anonymous@anonymous.com")
                .setGender("Other")
                .setPhone("1234567890")
                .setDateOfBirth("01", "January", "1900")
                .selectSubjectsByInput("Math")
                .selectSubjectsByInput("Arts")
                .selectHobbyByCheckBox("Sports")
                .selectHobbyByCheckBox("Music")
                .uploadPicture("#uploadPicture","image.jpg")
                .addressField("Random Street, 1138")
                .selectState("NCR")
                .selectCity("Delhi")
                .submit()
                //результаты
                .successfulSubmit("Thanks for submitting the form")
                .checkResultTable("Student Name", "John Doe")
                .checkResultTable("Student Email", "anonymous@anonymous.com")
                .checkResultTable("Gender", "Other")
                .checkResultTable("Mobile", "1234567890")
                .checkResultTable("Date of Birth", "01 January,1900")
                .checkResultTable("Subjects", "Maths, Arts")
                .checkResultTable("Hobbies", "Sports, Music")
                .checkResultTable("Picture", "image.jpg")
                .checkResultTable("Address", "Random Street, 1138")
                .checkResultTable("State and City", "NCR Delhi");
    }

    @Test
    void succesfullMinimalFormFillTest() {
        registrationPage.openPage()
                //заполнение формы
                .setFirstName("John")
                .setLastName("Doe")
                .setGender("Other")
                .setPhone("1234567890")
                .submit()
                //результаты, в том числе пустые поля
                .successfulSubmit("Thanks for submitting the form")
                .checkResultTable("Student Name", "John Doe")
                .checkResultTable("Student Email", "")
                .checkResultTable("Gender", "Other")
                .checkResultTable("Mobile", "1234567890")
                .checkResultTable("Subjects", "")
                .checkResultTable("Hobbies", "")
                .checkResultTable("Picture", "")
                .checkResultTable("Address", "")
                .checkResultTable("State and City", "");
    }

    @Test
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
