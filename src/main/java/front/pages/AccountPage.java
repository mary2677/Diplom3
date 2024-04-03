package front.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class AccountPage extends BasePage {


    public AccountPage(WebDriver driver) {
        super(driver);
    }

    // Локаторы логотипа Stellar burger
    private final By logoutLinkLocator = By.xpath("//button[contains(text(),\"Выход\")]/..");

    // Методы
    @Step("Клик по кнопке «конструктор» в хедере")
    public MainPage clickConstructorButton() {
        WebElement element = driver.findElement(headerConstructorButtonLocator);
        element.click();
        return new MainPage(driver);
    }

    @Step("Клик логотипу")
    public MainPage clickHeaderLogo() {
        WebElement element = driver.findElement(headerLogoLocator);
        element.click();
        return new MainPage(driver);
    }
    @Step("Клик кнопке «Выход» в личном кабинете")
    public final LoginPage logout() {
        WebElement element = driver.findElement(logoutLinkLocator);
        element.click();
        return new LoginPage(driver);
    }

    public boolean isAccountPage() {
        WebElement element = driver.findElement(headerAccountButtonLocator);
        List<WebElement> elements = element.findElements(By.xpath("//a[contains(@class, \"AppHeader_header__link_active\")]"));
        return !elements.isEmpty();
    }
}