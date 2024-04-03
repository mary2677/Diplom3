
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import front.pages.*;

import static org.junit.Assert.assertTrue;

public class HeaderNavigationTest extends BaseTest {

    @Test
    @DisplayName("Переход по клику на «Личный кабинет» в хедере")
    public void AccountButton() {
        AccountPage accountPage = (AccountPage) mainPage.clickHeaderAccountButton();
        assertTrue(accountPage.isAccountPage());
    }

    @Test
    @DisplayName("Переход из личного кабинета в конструктор по клику на логотип Stellar Burgers")
    public void Logo() {
        AccountPage accountPage = (AccountPage) mainPage.clickHeaderAccountButton();
        mainPage = accountPage.clickHeaderLogo();
        assertTrue(mainPage.isMainPage());
    }

    @Test
    @DisplayName("Переход из личного кабинета в конструктор по клику на «Конструктор» в хедере")
    public void ConstructorButton() {
        AccountPage accountPage = (AccountPage) mainPage.clickHeaderAccountButton();
        mainPage = accountPage.clickConstructorButton();
        assertTrue(mainPage.isMainPage());
    }
}
