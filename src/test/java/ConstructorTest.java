
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

// проверка переходов между вкладками
public class ConstructorTest extends BaseTest {

    @Test
    @DisplayName("Проверка переходов на вкладку булки")
    public void tabsChooseBunTest() {
        String expectedTabName;
        String actualTabName;

        mainPage.chooseFilling(); // Булки выбраны по-умолчанию, поэтому меняем начальное состояние на начинки
        mainPage.chooseBun();
        expectedTabName = "Булки";
        actualTabName = mainPage.getActiveChooseTab().getText();
        assertEquals(expectedTabName, actualTabName);
    }

    @Test
    @DisplayName("Проверка перехода на вкладку соусы")
    public void tabsChooseSauceTest() {
        String expectedTabName;
        String actualTabName;

        mainPage.chooseSauce();
        expectedTabName = "Соусы";
        actualTabName = mainPage.getActiveChooseTab().getText();
        assertEquals(expectedTabName, actualTabName);
    }

    @Test
    @DisplayName("Проверка переходов на вкладку начинки")
    public void tabsChooseFillingTest() {
        String expectedTabName;
        String actualTabName;

        mainPage.chooseFilling();
        expectedTabName = "Начинки";
        actualTabName = mainPage.getActiveChooseTab().getText();
        assertEquals(expectedTabName, actualTabName);
    }
}
