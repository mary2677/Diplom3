package front.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class MainPage extends BasePage {
    /* Конструктор */
    public MainPage(WebDriver driver) {
        super(driver);
    }

    // Локаторы
    private final By checkoutButton = By.xpath("//button[text()=\"Оформить заказ\"]"); // когда залогинен
    private final By accountButton = By.xpath("//button[text()=\"Войти в аккаунт\"]"); // когда не залогинен
    private final By chooseBunLocator = By.xpath("//div[contains(@class, \"tab_tab\")]//span[contains(text(),\"Булки\")]//parent::div");
    private final By chooseSauceLocator = By.xpath("//div[contains(@class, \"tab_tab\")]//span[contains(text(),\"Соусы\")]//parent::div");
    private final By chooseFillingLocator = By.xpath("//div[contains(@class, \"tab_tab\")]//span[contains(text(),\"Начинки\")]//parent::div");
    private final By chooseActiveTabLocator = By.xpath("//section[contains(@class, \"BurgerIngredients_ingredients\")]//div[contains(@class, \"tab_tab_type_current\")]");

    /* Методы */
    @Step("Переход к разделу «Булки» в конструкторе")
    public final void chooseBun() {
        WebElement element = driver.findElement(chooseBunLocator);
        element.click();
    }
    @Step("Переход к разделу «Соусы» в конструкторе")
    public final void chooseSauce() {
        WebElement element = driver.findElement(chooseSauceLocator);
        element.click();
    }
    @Step("Переход к разделу «Начинки» в конструкторе")
    public final void chooseFilling() {
        WebElement element = driver.findElement(chooseFillingLocator);
        element.click();
    }

    public final WebElement getActiveChooseTab() {
        return driver.findElement(chooseActiveTabLocator);
    }

    @Step("Клик по кнопке «Личный Кабинет» в хедере")
    public BasePage clickHeaderAccountButton() {
        WebElement element = driver.findElement(headerAccountButtonLocator);
        if (isAuthorized()) {
            element.click();
            return new AccountPage(driver);
        } else {
            element.click();
            return new LoginPage(driver);
        }
    }

    @Step("Клик по кнопке «Войти в аккаунт» на главной")
    public LoginPage clickMainAccountButton() {
        WebElement element = driver.findElement(accountButton);
        element.click();
        return new LoginPage(driver);
    }

    public boolean isMainPage() {
        WebElement element = driver.findElement(headerConstructorButtonLocator);
        List<WebElement> elements = element.findElements(By.xpath("//a[contains(@class, \"AppHeader_header__link_active\")]"));
        return !elements.isEmpty();
    }

    public boolean isAuthorized() {
        List<WebElement> elements = driver.findElements(checkoutButton);
        return !elements.isEmpty();
    }
    public boolean isNotAuthorized() {
        List<WebElement> elements = driver.findElements(accountButton);
        return !elements.isEmpty();
    }
}