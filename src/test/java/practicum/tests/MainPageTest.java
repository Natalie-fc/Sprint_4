package practicum.tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import practicum.pageobjects.MainPage;
import java.time.Duration;
import static org.junit.Assert.assertTrue;
import practicum.helpers.DriverFactory;


public class MainPageTest {

    private WebDriver driver;

    @Before
    public void setUp() {

        driver = DriverFactory.createDriver();

        driver.get("https://qa-scooter.praktikum-services.ru/");
    }
    @Test
    public void clickTopButtonShouldOpenOrderForm() {

        MainPage mainPage = new MainPage(driver);
        mainPage.clickTopButton();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        boolean isFormVisible = wait
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[contains(@placeholder,'Имя')]")))
                .isDisplayed();

        assertTrue("Форма заказа не появилась после нажатия на кнопку", isFormVisible);

}

    @Test
    public void clickBottomButtonShouldOpenOrderForm() {
        MainPage mainPage = new MainPage(driver);

        WebElement bottomButton = driver.findElement(By.cssSelector(".Button_Button__ra12g.Button_Middle__1CSJM"));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", bottomButton);

        mainPage.clickBottomButton();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        boolean isFormVisible = wait
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[contains(@placeholder,'Имя')]")))
                .isDisplayed();

        assertTrue("Форма заказа не появилась после нажатия на нижнюю кнопку", isFormVisible);

    }

    @Test
    public void allFaqAnswersShouldBeVisibleAfterClick() {

        MainPage mainPage = new MainPage(driver);

        for (int i = 0; i < 8; i++) {
            WebElement question = driver.findElement(By.id("accordion__heading-" + i));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", question);

            mainPage.clickFaqQuestions(i);

            boolean isAnswerVisible = mainPage.isFaqAnswerVisible(i);
            assertTrue("Ответ на вопрос №" + i + " не отображается после клика", isAnswerVisible);
        }

    }

        @After
        public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}