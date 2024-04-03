
import api.*;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import java.time.Duration;
import front.pages.*;
import static front.DriverCreat.createWebDriver;

import static org.junit.Assert.assertTrue;


public class LoginTest {
    WebDriver driver;
    String accessToken;
    MainPage mainPage;
    User user;

    @Before
    public void setUp() {
        driver = createWebDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        user = UserGenerator.randomUser();
        UserAndClient.create(user);
        ValidatableResponse loginResponse = UserAndClient.login(user);
        accessToken = loginResponse.extract().path("accessToken");
    }

    @Test
    @DisplayName("Вход по кнопке «Войти в аккаунт» на главной")
    public void MainAccountButton() {
        driver.get("https://stellarburgers.nomoreparties.site/");
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = mainPage.clickMainAccountButton();
        loginPage.setEmailField(user.getEmail());
        loginPage.setPasswordField(user.getPassword());
        mainPage = loginPage.submitLogin();
        assertTrue(mainPage.isAuthorized());
    }

    @Test
    @DisplayName("Вход через кнопку «Личный кабинет» в хедере")
    public void HeaderAccountButton() {
        driver.get("https://stellarburgers.nomoreparties.site/");
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = (LoginPage) mainPage.clickHeaderAccountButton();
        loginPage.setEmailField(user.getEmail());
        loginPage.setPasswordField(user.getPassword());
        mainPage = loginPage.submitLogin();
        assertTrue(mainPage.isAuthorized());
    }

    @Test
    @DisplayName("Вход через кнопку в форме регистрации")
    public void RegistrationPage() {
        driver.get("https://stellarburgers.nomoreparties.site/register");
        RegistrationPage page = new RegistrationPage(driver);

        User newRegistrationUser = UserGenerator.randomUser();
        page.setNameField(newRegistrationUser.getName());
        page.setEmailField(newRegistrationUser.getEmail());
        page.setPasswordField(newRegistrationUser.getPassword());
        LoginPage loginPage = (LoginPage) page.submitRegistration();
        loginPage.setEmailField(newRegistrationUser.getEmail());
        loginPage.setPasswordField(newRegistrationUser.getPassword());
        mainPage = loginPage.submitLogin();

        assertTrue(mainPage.isAuthorized());

        ValidatableResponse loginResponse = UserAndClient.login(user);
        String accessToken = loginResponse.extract().path("accessToken");
        UserAndClient.delete(accessToken);

    }

    @Test
    @DisplayName("Вход через кнопку в форме восстановления пароля")
    public void ForgotPasswordPage() throws InterruptedException {
        driver.get("https://stellarburgers.nomoreparties.site/forgot-password");
        ForgotPasswordPage page = new ForgotPasswordPage(driver);
        page.setEmailField(user.getEmail());
        page.submitRestore();

        Thread.sleep(6000); // Задержка на пересылку письма между почтовыми сервисами

        user.setPassword(UserGenerator.randomUser().getPassword());
        page.setPasswordField(user.getPassword());
        String login = user.getEmail().split("@")[0];
        int id = EmailClient.getAll(login)[0].getId();
        ValidatableResponse getEmailResponse = EmailClient.get(login, id);
        String body = getEmailResponse.extract().path("body");
        page.setVerifyCodeField(EmailUtil.verifyCodeExtraction(body));
        LoginPage loginPage = page.submitSave();

        loginPage.setEmailField(user.getEmail());
        loginPage.setPasswordField(user.getPassword());
        mainPage = loginPage.submitLogin();
        assertTrue(mainPage.isAuthorized());
    }

    @After
    public void tearDown() {
        UserAndClient.delete(accessToken);
        driver.close();
    }
}
