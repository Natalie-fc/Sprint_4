package practicum.pageobjects;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPage {
    public static final String PAGE_URL = "https://qa-scooter.praktikum-services.ru/";
    private final WebDriver driver;
    private final WebDriverWait wait;

    // кнопка "Заказать" в верхней части страницы
    private final By topButton = By.xpath("//div[contains(@class,'Header_Nav')]/button[text()='Заказать']");

    // кнопка "Заказать" в нижней части страницы
    private final By bottomButton = By.xpath("//div[contains(@class,'Home_FinishButton')]/button[text()='Заказать']");

    // Поле "Имя" в форме заказа - для проверки отображения формы
    private final By nameInput = By.xpath("//input[contains(@placeholder, 'Имя')]");

    // выпадающий список в разделе "Вопросы о важном" - стрелочка
    private final By[] faqQuestions = new By[8];

    // текст ответа на вопрос (появляется при клике на стрелочку)
    private final By[] faqAnswers = new By[8];

    public MainPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        for (int i = 0; i < 8; i++) {
            faqQuestions[i] = By.id("accordion__heading-" + i);
            faqAnswers[i] = By.id("accordion__panel-" + i);
        }
    }

    public void clickTopButton() {
        wait.until(ExpectedConditions.elementToBeClickable(topButton)).click();
    }

    public void clickBottomButton() {
        wait.until(ExpectedConditions.elementToBeClickable(bottomButton)).click();
    }

    public boolean isOrderFormVisible() {
        return
               wait.until(ExpectedConditions.visibilityOfElementLocated(nameInput)).isDisplayed();
    }

    public void scrollToBottomButton() {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(bottomButton));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public void scrollToFaqQuestion(int i) {
        WebElement question = wait.until(ExpectedConditions.visibilityOfElementLocated(faqQuestions[i]));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", question);
    }

    public void clickFaqQuestions(int i) {
        wait.until(ExpectedConditions.elementToBeClickable(faqQuestions[i])).click();
    }
    public boolean isFaqAnswerVisible(int i) {
        return
                wait.until(ExpectedConditions.visibilityOfElementLocated(faqAnswers[i])).isDisplayed();

    }
}
