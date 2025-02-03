package pages.components;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class AddressComponent {

    // три элемента связаны с адресом

    public void addressField(String address){
        $("#currentAddress").setValue(address);
    }

    public void selectState(String state){

        $("#state").click();
        $("#stateCity-wrapper").click();
        $("#stateCity-wrapper").type(state).pressEnter();

    }

    public void selectCity(String city){
        $("#city").click();
        $("#stateCity-wrapper").type(city).pressEnter();
    }
}
