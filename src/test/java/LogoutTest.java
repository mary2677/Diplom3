

import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import front.pages.*;


import static org.junit.Assert.*;

public class LogoutTest extends BaseTest {
    @Test
    @DisplayName("Выход по кнопке «Выйти» в личном кабинете")
    public void logout() {
        assertTrue(mainPage.isAuthorized());
        AccountPage accountPage = (AccountPage) mainPage.clickHeaderAccountButton();
        accountPage.logout();

        driver.get("https://stellarburgers.nomoreparties.site/");
        mainPage = new MainPage(driver);
        assertTrue(mainPage.isNotAuthorized());
    }

}
