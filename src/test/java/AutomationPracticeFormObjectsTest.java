import org.junit.jupiter.api.Test;
import pages.RegistrationPage;

public class AutomationPracticeFormObjectsTest extends TestBase {

    RegistrationPage registrationPage = new RegistrationPage();

    @Test
    void succesfulFormFill(){
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
                .uploadPicture("#uploadPicture","src/test/resources/image.jpg")
                .addressField("Random Street, 1138")
                .selectState("NCR")
                .selectCity("Delhi")
                .submit()
                //результаты
                .succesfulSubmit("Thanks for submitting the form")
                .checkResultTable("Student Name", "anonymous@anonymous.com")
                .checkResultTable("Student Email", "John Doe")
                .checkResultTable("Gender", "Other")
                .checkResultTable("Mobile", "1234567890")
                .checkResultTable("Date of Birth", "J01 January,1900")
                .checkResultTable("Subjects", "Maths, Arts")
                .checkResultTable("Hobbies", "Sports, Music")
                .checkResultTable("Picture", "image.jpg")
                .checkResultTable("Address", "Random Street, 1138")
                .checkResultTable("State and City", "NCR Delhi");

    }

}
