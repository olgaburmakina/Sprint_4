import constants.Constants;
import pageobjects.MainPage;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

public class MainPageTest {
    private static WebDriver driver;
    private static MainPage page;

    @BeforeClass
    public static void setup() {
        System.setProperty("webdriver.chrome.driver", Constants.DRIVER_PATH);
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.of(10, ChronoUnit.SECONDS));
        page = new MainPage(driver);
        page.open();
    }

    @Test
    public void scooterLogoClick() {
        page.clickScooterLogo();
        Assert.assertEquals("https://qa-scooter.praktikum-services.ru/", driver.getCurrentUrl());
    }

    @Test
    public void FAQText() {
        page.clickCookiesButton();
        boolean flag = true;
        if (!page.checkFAQQuestion(1, "Сутки — 400 рублей. Оплата курьеру — наличными или картой.")) {
            System.out.println("Тест 1 вопроса не прошел");
            flag = false;
        }
        if (!page.checkFAQQuestion(2, "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим.")) {
            System.out.println("Тест 2 вопроса не прошел");
            flag = false;
        }
        if (!page.checkFAQQuestion(3, "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30.")) {
            System.out.println("Тест 3 вопроса не прошел");
            flag = false;
        }
        if (!page.checkFAQQuestion(4, "Только начиная с завтрашнего дня. Но скоро станем расторопнее.")) {
            System.out.println("Тест 4 вопроса не прошел");
            flag = false;
        }
        if (!page.checkFAQQuestion(5, "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010.")) {
            System.out.println("Тест 5 вопроса не прошел");
            flag = false;
        }
        if (!page.checkFAQQuestion(6, "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится.")) {
            System.out.println("Тест 6 вопроса не прошел");
            flag = false;
        }
        if (!page.checkFAQQuestion(7, "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои.")) {
            System.out.println("Тест 7 вопроса не прошел");
            flag = false;
        }
        if (!page.checkFAQQuestion(8, "Да, обязательно. Всем самокатов! И Москве, и Московской области.")) {
            System.out.println("Тест 8 вопроса не прошел");
            flag = false;
        }
        Assert.assertTrue(flag);
    }

    @AfterClass
    public static void quit() {
        driver.quit();
    }
}
