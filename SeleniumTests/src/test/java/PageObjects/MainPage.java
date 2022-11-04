package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MainPage {
    // Конструктор
    private WebDriver driver;
    public MainPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    // Лого "Яндекс"
    @FindBy(xpath = "//*[@id=\"root\"]/div/div/div[1]/div[1]/a[1]")
    private WebElement yandexLogo;
    // Лого "Самокат"
    @FindBy(xpath = "//*[@id=\"root\"]/div/div/div[1]/div[1]/a[2]")
    private WebElement scooterLogo;
    // Вопросы о важном
    @FindBy(xpath = "//*[@id=\"root\"]/div/div/div[5]/div[2]/div")
    private WebElement FAQAccordion;
    // Кнопка "Заказать" верхняя
    @FindBy(xpath = "//*[@id=\"root\"]/div/div/div[1]/div[2]/button[1]")
    private WebElement orderUpperButton;
    // Кнопка "Статус заказа"
    @FindBy(xpath = "//*[@id=\"root\"]/div/div/div[1]/div[2]/button[2]")
    private WebElement statusButton;
    // Кнопка "Заказать" нижняя
    @FindBy(xpath = "//*[@id=\"root\"]/div/div/div[4]/div[2]/div[5]/button")
    private WebElement orderLowerButton;

    public void clickScooterLogo() {
        scooterLogo.click();
    }
    public void clickYandexLogo() {
        yandexLogo.click();
    }
}
