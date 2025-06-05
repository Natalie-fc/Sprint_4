package practicum.tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import practicum.helpers.DriverFactory;
import practicum.pageobjects.MainPage;
import practicum.pageobjects.OrderPage;

import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class OrderPageTest {

    private WebDriver driver;
    private final String name;
    private final String surname;
    private final String address;
    private final String metroStation;
    private final String phone;

public OrderPageTest(String name, String surname, String address, String metroStation, String phone) {
    this.name = name;
    this.surname = surname;
    this.address = address;
    this.metroStation = metroStation;
    this.phone = phone;
}

@Parameterized.Parameters
public static Object[][] getData() {
    return new Object[][] {
            {"Артём", "Кирсанов", "ул.Пушкина, д.50", "Румянцево", "89161234567"},
            {"Наталья", "Страневская", "ул.Шевченко, д.10", "Митино", "89169876543"}
    };
}

@Before
    public void setUp() {

    driver = DriverFactory.createDriver();
    driver.get("https://qa-scooter.praktikum-services.ru/");
}

@Test
    public void orderShouldBeCreatedSuccessfully() {
    MainPage mainPage = new MainPage(driver);
    mainPage.clickTopButton();

    OrderPage orderPage = new OrderPage(driver);
    orderPage.fillForm(name, surname, address, metroStation, phone);

    orderPage.fillSecondForm("04.06.2025", "двое суток", "black", "Позвоните за час");

    orderPage.confirmOrder();

    assertTrue("Окно подтверждения заказа не появилось", orderPage.isOrderConfirmed());
}
    @After
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
