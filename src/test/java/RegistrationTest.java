
import api.User;
import api.UserAndClient;
import api.UserGenerator;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import front.pages.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static front.DriverCreat.createWebDriver;
public class RegistrationTest {
    WebDriver driver;
    RegistrationPage page;
    User user;

    @Before
    public void setUp() {
        driver = createWebDriver();
        driver.get("https://stellarburgers.nomoreparties.site/register");
        page = new RegistrationPage(driver);
        user = UserGenerator.randomUser();
    }

    @Test
    @DisplayName("Ошибка при регистрации. Минимальный пароль — шесть символов")
    public void shortPassword() {
        String shortPassword = user.getPassword().substring(0, 5);
        user.setPassword(shortPassword);

        page.setNameField(user.getName());
        page.setEmailField(user.getEmail());
        page.setPasswordField(user.getPassword());
        page.submitRegistration();

        String expectedErrorMessage = "Некорректный пароль";
        String actualErrorMessage = page.getErrorMessage();
        assertEquals(expectedErrorMessage, actualErrorMessage);

        ValidatableResponse loginResponse = UserAndClient.login(user);
        assertEquals("Неверное описание в теле ответа", "email or password are incorrect", loginResponse.extract().path("message"));
    }

    @Test
    @DisplayName("Успешная регистрация")
    public void correctPassword() {
        page.setNameField(user.getName());
        page.setEmailField(user.getEmail());
        page.setPasswordField(user.getPassword());
        page.submitRegistration();

        assertNull(page.getErrorMessage());

        ValidatableResponse loginResponse = UserAndClient.login(user);
        assertEquals("Несоответствие email в теле ответа", user.getEmail(), loginResponse.extract().path("user.email"));
        assertEquals("Несоответствие name в теле ответа", user.getName(), loginResponse.extract().path("user.name"));
        String accessToken = loginResponse.extract().path("accessToken");
        UserAndClient.delete(accessToken);
    }

    @After
    public void tearDown() {
        driver.close();
    }
}
