package tests;

import org.junit.jupiter.api.Test;
import pages.RegistrationPage;
import utils.RegistrationPageRandomData;

public class AutomationPracticeFormObjectsRandomDataTestEnvironment extends TestEnvironmentBase {

    RegistrationPage registrationPage = new RegistrationPage();
    RegistrationPageRandomData randData = new RegistrationPageRandomData();

    @Test
    void succesfullFullFormFillTest(){

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
    void succesfullMinimalFormFillTest() {
        String firstName = randData.firstName();
        String lastName = randData.lastName();
        String gender = randData.gender();
        String phone = randData.phone();

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
