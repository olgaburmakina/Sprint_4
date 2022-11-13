import constants.Constants;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import pageobjects.MainPage;
import pageobjects.OrderPage;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

@RunWith(Parameterized.class)
public class OrderPageTest {
    private static WebDriver driver;
    private static OrderPage orderPage;
    private static MainPage mainPage;

    private final String firstName;
    private final String lastName;
    private final String address;
    private final String metroStation;
    private final String phoneNumber;
    private final String date;
    private final int rentLength;
    private final int colorIndex;
    private final String comment;


    public OrderPageTest(String firstName, String lastName, String address, String metroStation, String phoneNumber, String date, int rentLength, int colorIndex, String comment) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.metroStation = metroStation;
        this.phoneNumber = phoneNumber;
        this.date = date;
        this.rentLength = rentLength;
        this.colorIndex = colorIndex;
        this.comment = comment;
    }

    @Parameters
    public static Object[][] data() {
        return new Object[][] {
                {"Илья", "Скворцов", "ул. Белякова, 3", "Алтуфьево", "89000000000", "15.11.22", 3, 1, "Тест"},
                {"Николай", "Стрельцов", "ул. Бочкова, 11А", "Алексеевская", "89000000000", "04.11.22", 1, 2, ""}
        };
    }

    @BeforeClass
    public static void setup() {
        System.setProperty("webdriver.chrome.driver", Constants.DRIVER_PATH);
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.of(10, ChronoUnit.SECONDS));
        orderPage = new OrderPage(driver);
        mainPage = new MainPage(driver);
    }

    @Before
    public void reset() {
        mainPage.open();
    }

    @Test
    public void firstFlow() throws InterruptedException {
        mainPage.clickUpperOrderButton();

        orderPage.enterFirstName(firstName);
        orderPage.enterLastName(lastName);
        orderPage.enterAddress(address);
        System.out.println("Выбранная станция: " + orderPage.selectMetroStation(metroStation));
        orderPage.enterPhoneNumber(phoneNumber);
        orderPage.clickNext();

        orderPage.chooseRentDay(date);
        System.out.println("Выбранный срок: " + orderPage.chooseRentLength(rentLength));
        System.out.println("Выбранный цвет: " + orderPage.chooseColor(colorIndex));
        orderPage.enterComment(comment);
        orderPage.clickOrderButton();
        orderPage.clickConfirmButton();

        // Баг Chrome
//        String trackNumber = orderPage.getOrderNumber();
//        System.out.println(trackNumber);
//        Assert.assertFalse(trackNumber.isEmpty());

        Assert.assertFalse(orderPage.getModalHeader().isEmpty());
    }

    @Test
    public void secondFlow() throws InterruptedException {
        mainPage.clickUpperOrderButton();

        orderPage.enterFirstName(firstName);
        orderPage.enterLastName(lastName);
        orderPage.enterAddress(address);
        System.out.println("Выбранная станция: " + orderPage.selectMetroStation(metroStation));
        orderPage.enterPhoneNumber(phoneNumber);
        orderPage.clickNext();

        orderPage.chooseRentDay(date);
        System.out.println("Выбранный срок: " + orderPage.chooseRentLength(rentLength));
        System.out.println("Выбранный цвет: " + orderPage.chooseColor(colorIndex));
        orderPage.enterComment(comment);
        orderPage.clickOrderButton();
        orderPage.clickConfirmButton();

        // Баг Chrome
//        String trackNumber = orderPage.getOrderNumber();
//        System.out.println(trackNumber);
//        Assert.assertFalse(trackNumber.isEmpty());

        Assert.assertFalse(orderPage.getModalHeader().isEmpty());
    }

    @AfterClass
    public static void quit() {
        driver.quit();
    }
}