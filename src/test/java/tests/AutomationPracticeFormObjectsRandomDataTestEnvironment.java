package tests;

import org.junit.jupiter.api.Test;
import pages.RegistrationPage;
import utils.RegistrationPageRandomData;

public class AutomationPracticeFormObjectsRandomDataTestEnvironment extends TestEnvironmentBase {

    RegistrationPage registrationPage = new RegistrationPage();
    RegistrationPageRandomData randData = new RegistrationPageRandomData();

    @Test
    void succesfullFullFormFillTest(){
        registrationPage.openPage()
                //заполнение формы
                .setFirstName(randData.firstName)
                .setLastName(randData.lastName)
                .setEmail(randData.email)
                .setGender(randData.gender)
                .setPhone(randData.phone)
                .setDateOfBirth(randData.day, randData.month, randData.year)
                .selectSubjectsByInput(randData.subject)
                .selectHobbyByCheckBox(randData.hobby)
                .uploadPicture(randData.picture)
                .addressField(randData.address)
                .selectState(randData.state)
                .selectCity(randData.cityRes)
                .submit()
                //результаты
                .successfulSubmit("Thanks for submitting the form")
                .checkResultTable("Student Name", randData.firstName + " " + randData.lastName)
                .checkResultTable("Student Email", randData.email)
                .checkResultTable("Gender", randData.gender)
                .checkResultTable("Mobile", randData.phone)
                .checkResultTable("Date of Birth", randData.day + " " + randData.month + "," + randData.year)
                .checkResultTable("Subjects", randData.subject)
                .checkResultTable("Hobbies", randData.hobby)
                .checkResultTable("Picture", randData.picture)
                .checkResultTable("Address", randData.address)
                .checkResultTable("State and City", randData.state + " " + randData.cityRes);
    }

    @Test
    void succesfullMinimalFormFillTest() {
        registrationPage.openPage()
                //заполнение формы
                .setFirstName(randData.firstName)
                .setLastName(randData.lastName)
                .setGender(randData.gender)
                .setPhone(randData.phone)
                .submit()
                //результаты, в том числе пустые поля
                .successfulSubmit("Thanks for submitting the form")
                .checkResultTable("Student Name", randData.firstName + " " + randData.lastName)
                .checkResultTable("Student Email", "")
                .checkResultTable("Gender", randData.gender)
                .checkResultTable("Mobile", randData.phone)
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
