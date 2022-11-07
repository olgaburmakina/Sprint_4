import pageobjects.OrderPage;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

public class OrderPageTest {
    private static WebDriver driver;
    private static OrderPage page;

    @BeforeClass
    public static void setup() {
        System.setProperty("webdriver.chrome.driver", "D:/chromedriver/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.of(10, ChronoUnit.SECONDS));
        page = new OrderPage(driver);
        page.open();
    }

    @Test
    public void orderChrome() {
        Assert.assertTrue(true);
        page.firstStep("Илья", "Скворцов", "Адрес", "Строгино", "890000000000");
        String number = page.secondStep("04.11.22", 1, 1);
        System.out.println("Номер заказа: " + number);
        Assert.assertFalse(number.isEmpty());
    }

    @AfterClass
    public static void quit() {
        driver.quit();
    }
}
// TODO: add parameters