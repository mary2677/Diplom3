package front.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ForgotPasswordPage extends BasePage {

    public ForgotPasswordPage(WebDriver driver) {
        super(driver);
    }

    //Локаторы
    //поле Email
    private final By emailFieldLocator = By.xpath("//label[contains(text(),\"Email\")]/following-sibling::input");
    //кнопка восстановить
    private final By restoreButton = By.xpath("//form//button");
    //поле пароль
    private final By passwordFieldLocator = By.xpath("//label[contains(text(),\"Пароль\")]/following-sibling::input");
    //поле код из письма
    private final By verifyCodeFieldLocator = By.xpath("//label[contains(text(),\"Введите код из письма\")]/following-sibling::input");
    private final By saveButton = By.xpath("//form//button");

    /* Методы */

    @Step("Заполняем поле Email")
    public final void setEmailField(String text) {
        WebElement element = driver.findElement(emailFieldLocator);
        element.sendKeys(text);
    }
    @Step("Кликаем по кнопке Востановить")
    public final void submitRestore() {
        WebElement element = driver.findElement(restoreButton);
        element.click();
    }
    @Step("Заполняем поле Пароль")
    public final void setPasswordField(String text) {
        WebElement element = driver.findElement(passwordFieldLocator);
        element.sendKeys(text);
    }
    @Step("Заполняем поле код из письма")
    public final void setVerifyCodeField(String text) {
        WebElement element = driver.findElement(verifyCodeFieldLocator);
        element.sendKeys(text);
    }
    @Step("Кликаем по кнопке Сохранить")
    public final LoginPage submitSave() {
        WebElement element = driver.findElement(saveButton);
        element.click();
        return new LoginPage(driver);
    }

}