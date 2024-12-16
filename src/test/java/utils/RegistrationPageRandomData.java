package utils;

import com.github.javafaker.Faker;

public class RegistrationPageRandomData {

    Faker faker = new Faker();

    public String firstName(){
        return faker.name().firstName();
    }

    public String lastName(){
        return faker.name().lastName();
    }

    public String email(){
        return faker.internet().emailAddress();
    }

    public String gender(){
        return faker.options().option("Male", "Female", "Other");
    }

    public String phone(){
        return faker.number().digits(10);
    }

    public String day(){
        return  String.valueOf(faker.number().numberBetween(10,28));
    }

    public String month(){
        return  faker.options().option("January", "March", "April", "May",
                "June", "July", "August", "September",
                "October", "November", "December");
    }

    public String year(){
        return  String.valueOf(faker.number().numberBetween(1900,2100));
    }

    public String subject(){
        return  faker.options().option("Hindi", "English", "Maths", "Physics",
                "Chemistry", "Biology", "Computer Science",
                "Commerce", "Accounting", "Economics", "Arts",
                "Social Studies", "History", "Civics");
    }

    public String hobby(){
        return faker.options().option("Sports", "Reading", "Music");
    }

    public String picture(){
        return faker.options().option("EliteCobraLanding.jpg", "HexTattoo.jpg", "image.jpg");
    }

    public String address(){
        return faker.address().fullAddress();
    }

    public String state(){
        return  faker.options().option("NCR", "Uttar Pradesh", "Haryana", "Rajasthan");
    }

    public String cityRes(String state){
        return getCity(state);
    }

    public String getCity(String state) {
        return switch (state) {
            case "NCR" -> faker.options().option("Delhi", "Gurgaon", "Noida");
            case "Uttar Pradesh" -> faker.options().option("Agra", "Lucknow", "Merrit");
            case "Haryana" -> faker.options().option("Karnal", "Panipat");
            case "Rajasthan" -> faker.options().option("Jaipur", "Jaiselmer");
            default -> throw new IllegalStateException("Unexpected value: " + state);
        };
    }

}
