package practicum.tests;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import practicum.helpers.DriverFactory;

public abstract class BaseTest {

    protected WebDriver driver;

    @Before
    public void setUp() {
        driver = DriverFactory.createDriver();
        driver.get("https://qa-scooter.praktikum-services.ru/");
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
      }
   }
}