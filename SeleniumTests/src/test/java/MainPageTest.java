import pageobjects.MainPage;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

public class MainPageTest {
    private static WebDriver driver;
    private static MainPage page;

    @BeforeClass
    public static void setup() {
        System.setProperty("webdriver.chrome.driver", "D:/chromedriver/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.of(10, ChronoUnit.SECONDS));
        driver.get("https://qa-scooter.praktikum-services.ru/");
        page = new MainPage(driver);
    }

    @Test
    public void scooterLogoClick() {
        page.clickScooterLogo();
        Assert.assertEquals("https://qa-scooter.praktikum-services.ru/", driver.getCurrentUrl());
    }

    @Test
    public void yandexLogoClick() throws Exception {
        if (driver.getWindowHandles().size() > 1) {
            throw new Exception();
        }
        page.clickYandexLogo();
        new WebDriverWait(driver, Duration.ofSeconds(5)).wait();
        Assert.assertEquals(2, driver.getWindowHandles().size());
    }

    @AfterClass
    public static void quit() {
        driver.quit();
    }
}
