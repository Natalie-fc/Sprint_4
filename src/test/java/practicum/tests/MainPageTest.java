package practicum.tests;


import org.junit.Test;
import practicum.pageobjects.MainPage;
import static org.junit.Assert.assertTrue;



public class MainPageTest extends BaseTest {


    @Test
    public void clickTopButtonShouldOpenOrderForm() {

        MainPage mainPage = new MainPage(driver);
        mainPage.clickTopButton();

        assertTrue("Форма заказа не появилась после нажатия на кнопку", mainPage.isOrderFormVisible());

}

    @Test
    public void clickBottomButtonShouldOpenOrderForm() {
        MainPage mainPage = new MainPage(driver);

        mainPage.scrollToBottomButton();
        mainPage.clickBottomButton();

        assertTrue("Форма заказа не появилась после нажатия на нижнюю кнопку", mainPage.isOrderFormVisible());

    }

    @Test
    public void allFaqAnswersShouldBeVisibleAfterClick() {

        MainPage mainPage = new MainPage(driver);

        for (int i = 0; i < 8; i++) {

            mainPage.scrollToFaqQuestion(i);
            mainPage.clickFaqQuestions(i);

            boolean isAnswerVisible = mainPage.isFaqAnswerVisible(i);
            assertTrue("Ответ на вопрос №" + i + " не отображается после клика", isAnswerVisible);
        }

    }

}