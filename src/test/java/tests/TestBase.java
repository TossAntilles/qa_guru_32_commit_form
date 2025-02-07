package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import helpers.Attach;
import io.qameta.allure.Step;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Map;

public class TestBase {

    @BeforeAll
    @Step("Подготовка тестового окружения")
    static void beforeAll(){
        System.setProperty("baseUrl", "https://demoqa.com");
        System.setProperty("browser", "chrome");
        System.setProperty("browserResolution", "1920x1080");
        System.setProperty("webDriverHost", "selenoid.autotests.cloud");

        Configuration.baseUrl = System.getProperty("baseUrl");
        Configuration.browserSize = System.getProperty("browserResolution");
        Configuration.pageLoadStrategy = "eager";
        Configuration.timeout = 5000;

        Configuration.remote = "https://user1:1234@"+ System.getProperty("webDriverHost") +"/wd/hub";
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                "enableVNC", true,
                "enableVideo", true
        ));
        Configuration.browserCapabilities = capabilities;

    }

    @BeforeEach
    void beforeEach(){
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterEach
    void addAttachments() {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();
    }

}
