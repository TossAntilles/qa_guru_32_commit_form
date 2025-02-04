package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Step;
import io.qameta.allure.selenide.AllureSelenide;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Map;

public class BeforeAll {

    @org.junit.jupiter.api.BeforeAll
    @Step("Подготовка тестового окружения")
    static void beforeAll(){
        System.setProperty("baseUrl", "https://demoqa.com");
        System.setProperty("browser", "chrome");
        System.setProperty("browserResolution", "1920x1080");
        System.setProperty("WebDriverHost", "selenoid.autotests.cloud");

        Configuration.baseUrl = System.getProperty("baseUrl");
        Configuration.browserSize = System.getProperty("browserResolution");
        Configuration.pageLoadStrategy = "eager";
        Configuration.timeout = 5000;

        Configuration.remote = "https://user1:1234@"+ System.getProperty("WebDriverHost") +"/wd/hub";
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                "enableVNC", true,
                "enableVideo", true
        ));
        Configuration.browserCapabilities = capabilities;

        SelenideLogger.addListener("allure", new AllureSelenide());

    }
}
