package utils;

import com.github.javafaker.Faker;

public class RegistrationPageRandomData {

    Faker faker = new Faker();

    public String firstName = faker.name().firstName(),
        lastName = faker.name().lastName(),
        email = faker.internet().emailAddress(),
        gender = faker.options().option("Male", "Female", "Other"),
        phone = faker.number().digits(10),

        day = String.valueOf(faker.number().numberBetween(10,28)), //для упрощения ограничил по длине февраля
        month = faker.options().option("January", "March", "April", "May",
                                                "June", "July", "August", "September",
                                                "October", "November", "December"),
        year = String.valueOf(faker.number().numberBetween(1900,2100)),

        subject = faker.options().option("Hindi", "English", "Maths", "Physics",
                                                "Chemistry", "Biology", "Computer Science",
                                                "Commerce", "Accounting", "Economics", "Arts",
                                                "Social Studies", "History", "Civics"),
        hobby = faker.options().option("Sports", "Reading", "Music"),

        picture = faker.options().option("EliteCobraLanding.jpg", "HexTattoo.jpg", "image.jpg"),
        address = faker.address().fullAddress(),

        state = faker.options().option("NCR", "Uttar Pradesh", "Haryana", "Rajasthan"),
        cityRes = getCity(state);

    public String getCity(String state) {
        switch(state){
            case "NCR":
                faker.options().option("Delhi", "Gurgaon", "Noida");
            case "Uttar Pradesh":
                return faker.options().option("Agra", "Lucknow", "Merrut");
            case "Haryana":
                return faker.options().option("Karnal", "Panipat");
            case "Rajasthan":
                return faker.options().option("Jaipur", "Jaiselmer");
            default:
                return null;
        }

    }
}
