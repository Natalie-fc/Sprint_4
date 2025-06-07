package practicum.tests;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import practicum.pageobjects.MainPage;
import practicum.pageobjects.OrderPage;

import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class OrderPageTest extends BaseTest {

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

@Parameterized.Parameters(name = "Тестовые данные: {0}, {1}, {2}, {3}, {4}")
public static Object[][] getData() {
    return new Object[][] {
            {"Артём", "Кирсанов", "ул.Пушкина, д.50", "Румянцево", "89161234567"},
            {"Наталья", "Страневская", "ул.Шевченко, д.10", "Митино", "89169876543"}
    };
}

@Test
    public void orderShouldBeCreatedSuccessfullyTest() {
    MainPage mainPage = new MainPage(driver);
    mainPage.clickTopButton();

    OrderPage orderPage = new OrderPage(driver);
    orderPage.fillForm(name, surname, address, metroStation, phone);

    orderPage.fillSecondForm("04.06.2025", "двое суток", "black", "Позвоните за час");

    orderPage.confirmOrder();

    assertTrue("Окно подтверждения заказа не появилось", orderPage.isOrderConfirmed());

    }
}
