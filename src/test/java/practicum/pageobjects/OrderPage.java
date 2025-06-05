package practicum.pageobjects;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class OrderPage {
    private WebDriver driver;
    private WebDriverWait wait;

    public OrderPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    // Первый шаг формы

    // Поле Имя
    private By nameInput = By.xpath("//input[@placeholder='* Имя']");

    // Поле Фамилия
    private By surnameInput = By.xpath("//input[@placeholder='* Фамилия']");

    // Поле Адрес
    private By addressInput = By.xpath("//input[@placeholder='* Адрес: куда привезти заказ']");

    // Поле Станция Метро
    private By metroStationInput = By.xpath("//input[@placeholder='* Станция метро']");

    // Поле Телефон
    private By phoneInput = By.xpath("//input[@placeholder='* Телефон: на него позвонит курьер']");

    // Кнопка "Далее"
    private By nextButton = By.xpath("//button[text()='Далее']");

    // Второй шаг формы
// Поле Когда привезти самокат
private By dateInput = By.xpath("//input[@placeholder='* Когда привезти самокат']");

// Поле Срок аренды
private By rentalPeriodDropdown = By.className("Dropdown-control");
private By rentalPeriodOption = By.xpath("//div[@class='Dropdown-option' and text()='двое суток']");

// Чек-бокс с выбором цвета
private By blackColorCheckbox = By.id("black");
private By greyColorCheckbox = By.id("grey");

// Поле Комментарий для курьера
private By commentInput = By.xpath("//input[@placeholder='Комментарий для курьера']");

// Кнопка "Заказать"
private By orderButton = By.xpath("//div[contains(@class, 'Order_Buttons')]/button[text()='Заказать']");

// Кнопка подтверждения "Да"
private By confirmYesButton = By.xpath("//button[contains(@class,'Button_Button__ra12g') and contains(@class,'Button_Middle__1CSJM') and text()='Заказать']");

// Окно с подтверждением заказа
private By orderConfirmation = By.xpath("//*[contains(text(), 'Заказ оформлен')]");


    public void acceptCookiesIfPresent() {
        try {
            WebElement cookieButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("rcc-confirm-button")));
            cookieButton.click();
        } catch (TimeoutException | NoSuchElementException ignored) {

        }
    }
public void fillForm(String name, String surname, String address, String metro, String phone) {
acceptCookiesIfPresent();

    wait.until(ExpectedConditions.visibilityOfElementLocated(nameInput)).sendKeys(name);

    driver.findElement(surnameInput).sendKeys(surname);

    driver.findElement(addressInput).sendKeys(address);

    driver.findElement(metroStationInput).click();
    driver.findElement(metroStationInput).sendKeys(metro);
    By metroOption = By.xpath("//div[contains(text(), '" + metro + "')]");
    wait.until(ExpectedConditions.elementToBeClickable(metroOption)).click();

    driver.findElement(phoneInput).sendKeys(phone);

    driver.findElement(nextButton).click();
    }

    public void fillSecondForm(String date, String rentalPeriod, String color, String comment) {
WebElement dateElement = wait.until(ExpectedConditions.visibilityOfElementLocated(dateInput));
    dateElement.sendKeys(date + Keys.ENTER);

        wait.until(ExpectedConditions.elementToBeClickable(rentalPeriodDropdown)).click();
        By rentalOption = By.xpath("//div[@class='Dropdown-option' and text()='" + rentalPeriod + "']");
        wait.until(ExpectedConditions.elementToBeClickable(rentalOption)).click();


        if (color != null) {
            if (color.equalsIgnoreCase("black")) {
                wait.until(ExpectedConditions.elementToBeClickable(blackColorCheckbox)).click();
            } else if (color.equalsIgnoreCase("grey")) {
                wait.until(ExpectedConditions.elementToBeClickable(greyColorCheckbox)).click();

            }
        }
        wait.until(ExpectedConditions.visibilityOfElementLocated(commentInput)).sendKeys(comment);

        wait.until(ExpectedConditions.elementToBeClickable(orderButton)).click();

    }
    public void confirmOrder() {
        By modalText = By.className("Order_ModalHeader__3FDaJ");
        wait.until(ExpectedConditions.visibilityOfElementLocated(modalText));

        WebElement confirmButton = wait.until(ExpectedConditions.elementToBeClickable(confirmYesButton));

        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", confirmButton);

    }

    public boolean isOrderConfirmed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(orderConfirmation))
                .getText().contains("Заказ оформлен");


    }

    }