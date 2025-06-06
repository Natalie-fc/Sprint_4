package practicum.tests;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import practicum.helpers.DriverFactory;
import practicum.pageobjects.MainPage;

public abstract class BaseTest {

    protected WebDriver driver;

    @Before
    public void setUp() {
        driver = DriverFactory.createDriver();
        driver.get(MainPage.PAGE_URL);
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
      }
   }
}