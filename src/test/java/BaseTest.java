
import api.User;
import api.UserAndClient;
import api.UserGenerator;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import front.pages.*;

import java.time.Duration;

import static api.UserAndClient.*;
import static front.DriverCreat.createWebDriver;



public abstract class BaseTest {
    WebDriver driver;
    String accessToken;
    MainPage mainPage;

    @Before
    public void setUp() {
        driver = createWebDriver();
        User user = UserGenerator.randomUser();
        create(user);
        ValidatableResponse loginResponse = login(user);
        accessToken = loginResponse.extract().path("accessToken");

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://stellarburgers.nomoreparties.site/login");

        LoginPage loginPage = new LoginPage(driver);
        loginPage.setEmailField(user.getEmail());
        loginPage.setPasswordField(user.getPassword());
        mainPage = loginPage.submitLogin();
    }

    @After
    public void tearDown() {
        delete(accessToken);
        driver.quit();
    }
}
