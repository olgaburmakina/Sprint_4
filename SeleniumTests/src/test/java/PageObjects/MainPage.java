package pageobjects;

import constants.Constants;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPage {
    private final String pageURL = Constants.MAIN_PAGE_URL;

    // Конструктор
    private final WebDriver driver;
    public MainPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    // Лого "Яндекс"
    @FindBy(css = "a[class*='LogoYandex']")
    private WebElement yandexLogo;
    // Лого "Самокат"
    @FindBy(css = "a[class*='LogoScooter']")
    private WebElement scooterLogo;
    // Кнопка "Заказать" верхняя
    @FindBy(css = "div[class*='Header_Nav'] > button[class*='Button_Button']")
    private WebElement orderUpperButton;
    // Кнопка "Статус заказа"
    @FindBy(css = "div[class*='Header_Nav'] > button[class*='Header_Link']")
    private WebElement statusButton;
    // Кнопка "Заказать" нижняя
    @FindBy(css = "div[class*='Home_FinishButton'] > button[class*='Button_Middle']")
    private WebElement orderLowerButton;
    // Вопросы о важном
    @FindBy(css = "div.accordion")
    private WebElement FAQAccordion;
    // Кнопка "Да все привыкли"
    @FindBy(id = "rcc-confirm-button")
    private WebElement cookiesConfirmButton;

    public String getURL() {
        return pageURL;
    }
    public void open() {
        driver.get(pageURL);
    }

    public void clickScooterLogo() {
        scooterLogo.click();
    }
    public void clickYandexLogo() {
        yandexLogo.click();
    }
    public void clickCookiesButton() {
        cookiesConfirmButton.click();
    }
    public void clickUpperOrderButton() {
        orderUpperButton.click();
    }
    public void clickStatusButton() {
        statusButton.click();
    }
    public void clickLowerOrderButton() {
        orderLowerButton.click();
    }
    public boolean checkFAQQuestion(int questionNumber, String expectedText) {
        new WebDriverWait(driver, Duration.ofSeconds(1)).until(ExpectedConditions.elementToBeClickable(By.id("accordion__heading-" + (questionNumber-1))));
        driver.findElement(By.id("accordion__heading-" + (questionNumber-1))).click();
        return driver.findElement(By.id("accordion__panel-" + (questionNumber-1))).getText().equals(expectedText);
    }
}
