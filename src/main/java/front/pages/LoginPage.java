package front.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    // Локаторы
    private final By emailFieldLocator = By.xpath("//label[contains(text(),\"Email\")]/following-sibling::input");
    private final By passwordFieldLocator = By.xpath("//label[contains(text(),\"Пароль\")]/following-sibling::input");
    private final By loginButton = By.xpath("//form//button");

    // Методы
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
    @Step("Кликаем по кнопке Войти")
    public final MainPage submitLogin() {
        WebElement element = driver.findElement(loginButton);
        element.click();
        return new MainPage(driver);
    }
}