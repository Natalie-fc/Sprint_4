package practicum.pageobjects;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPage {
    private WebDriver driver;

    // кнопка "Заказать" в верхней части страницы
    private By topButton = By.xpath("//div[contains(@class,'Header_Nav')]/button[text()='Заказать']");

    // кнопка "Заказать" в нижней части страницы
    private By bottomButton = By.xpath("//div[contains(@class,'Home_FinishButton')]/button[text()='Заказать']");

    // выпадающий список в разделе "Вопросы о важном" - стрелочка
    private By[] faqQuestions = new By[8];

    // текст ответа на вопрос (появляется при клике на стрелочку)
    private By[] faqAnswers = new By[8];

    public MainPage(WebDriver driver) {
        this.driver = driver;

        for (int i = 0; i < 8; i++) {
            faqQuestions[i] = By.id("accordion__heading-" + i);
            faqAnswers[i] = By.id("accordion__panel-" + i);
        }
    }
    public void clickTopButton() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(topButton))
                .click();

    }
    public void clickBottomButton() {
        driver.findElement(bottomButton).click();
    }
    public void clickFaqQuestions(int i) {
        driver.findElement(faqQuestions[i]).click();
    }
    public boolean isFaqAnswerVisible(int i) {
        return
        driver.findElement(faqAnswers[i]).isDisplayed();
    }
}
