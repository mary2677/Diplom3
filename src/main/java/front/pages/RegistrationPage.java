package front.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegistrationPage extends BasePage {

    public RegistrationPage(WebDriver driver) {
        super(driver);
    }

    // Локаторы формы регистрации
    private final By nameFieldLocator = By.xpath("//label[contains(text(),\"Имя\")]/following-sibling::input");
    private final By emailFieldLocator = By.xpath("//label[contains(text(),\"Email\")]/following-sibling::input");
    private final By passwordFieldLocator = By.xpath("//label[contains(text(),\"Пароль\")]/following-sibling::input");
    private final By submitRegistrationLocator = By.xpath("//form//button");
    private final By errorMessageLocator = By.xpath("//p[@class=\"input__error text_type_main-default\"]");

    /* Методы */
    @Step("Заполняем поле Имя")
    public final void setNameField(String text) {
        WebElement element = driver.findElement(nameFieldLocator);
        element.sendKeys(text);
    }
    @Step("Заполняем поле Email")
    public final void setEmailField(String text) {
        WebElement element = driver.findElement(emailFieldLocator);
        element.sendKeys(text);
    }
    @Step("Заполняем поле Пароль")
    public final void setPasswordField(String text) {
        WebElement element = driver.findElement(passwordFieldLocator);
        element.sendKeys(text);
    }
    @Step("Кликаем по кнопке Зарегистрироваться")
    public final BasePage submitRegistration() {
        WebElement element = driver.findElement(submitRegistrationLocator);
        element.click();
        try {
            driver.findElement(errorMessageLocator);
        } catch (Exception e) {
            new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.urlToBe("https://stellarburgers.nomoreparties.site/login"));
            return new LoginPage(driver);
        }
        return new RegistrationPage(driver);
    }
    @Step("Проверяем сообщение об ошибке")
    public final String getErrorMessage() {
        try {
            WebElement element = driver.findElement(errorMessageLocator);
            return element.getText();
        } catch (Exception e) {
            return null;
        }
    }
}