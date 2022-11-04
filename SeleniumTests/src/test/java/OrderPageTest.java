import PageObjects.OrderPage;
import org.junit.*;
import org.junit.runner.OrderWith;
import org.junit.runners.MethodSorters;
import org.junit.runners.Parameterized.Parameters;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Collection;

//@RunWith(Parameterized.class)
public class OrderPageTest {
    private static WebDriver firefoxDriver;
    private static WebDriver chromeDriver;
    private static OrderPage firefoxPage;
    private static OrderPage chromePage;

    @Parameters
    public static Collection<Object[]> data() {
        Object[][] data = new Object[][] {};
        return Arrays.asList(data);
    }

    @BeforeClass
    public static void setup() {
        System.setProperty("webdriver.chrome.driver", "D:/chromedriver/chromedriver.exe");
        chromeDriver = new ChromeDriver();
        chromeDriver.manage().timeouts().implicitlyWait(Duration.of(10, ChronoUnit.SECONDS));
        chromeDriver.get("https://qa-scooter.praktikum-services.ru/order");

        System.setProperty("webdriver.gecko.driver", "D:/geckodriver/geckodriver.exe");
        FirefoxOptions options = new FirefoxOptions();
        options.setBinary("C:/Program Files/Mozilla Firefox/firefox.exe");
        firefoxDriver = new FirefoxDriver(options);
        firefoxDriver.manage().timeouts().implicitlyWait(Duration.of(10, ChronoUnit.SECONDS));
        firefoxDriver.get("https://qa-scooter.praktikum-services.ru/order");

        firefoxPage = new OrderPage(firefoxDriver);
        chromePage = new OrderPage(chromeDriver);
    }

    @Test
    public void orderFirefox() {
        firefoxPage.firstStep("Илья", "Скворцов", "Адрес", 3, "890000000000");
        String number = firefoxPage.secondStep("04.11.22", 1, 1);
        System.out.println("Firefox::Номер заказа: " + number);
        Assert.assertFalse(number.isEmpty());
    }

    @Test
    public void orderChrome() {
        chromePage.firstStep("Илья", "Скворцов", "Адрес", 3, "890000000000");
        String number = chromePage.secondStep("04.11.22", 1, 1);
        System.out.println("Chrome::Номер заказа: " + number);
        Assert.assertFalse(number.isEmpty());
    }
    
    @AfterClass
    public static void quit() {
        chromeDriver.quit();
        firefoxDriver.quit();
    }
}
