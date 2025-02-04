package pages.components;

import static com.codeborne.selenide.Selenide.$;

public class AddressComponent {

    // три элемента связаны с адресом

    public void addressField(String address){
        $("#currentAddress").setValue(address);
    }

    public void selectState(String state){

        $("#state").scrollIntoView(true).$("input").type(state).pressEnter();
    }

    public void selectCity(String city){
        $("#city").scrollIntoView(true).$("input").type(city).pressEnter();
    }
}
