package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.regex.Pattern;

public class OrderPage {
    // Конструктор
    private WebDriver driver;
    public OrderPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    // Лого "Яндекс"
    @FindBy(xpath = "//*[@id=\"root\"]/div/div/div[1]/div[1]/a[1]")
    private WebElement yandexLogo;
    // Лого "Самокат"
    @FindBy(xpath = "//*[@id=\"root\"]/div/div/div[1]/div[1]/a[2]")
    private WebElement scooterLogo;
    // Название шага
    @FindBy(xpath = "//*[@id=\"root\"]/div/div[2]/div[1]")
    private WebElement stepTitle;

    public String getStepTitle() {
        return stepTitle.getText();
    }

    // Поле ввода имени
    @FindBy(xpath = "//*[@id=\"root\"]/div/div[2]/div[2]/div[1]/input")
    private WebElement firstNameField;
    // Поле ввода фамилии
    @FindBy(xpath = "//*[@id=\"root\"]/div/div[2]/div[2]/div[2]/input")
    private WebElement lastNameField;
    // Поле ввода адреса
    @FindBy(xpath = "//*[@id=\"root\"]/div/div[2]/div[2]/div[3]/input")
    private WebElement addressField;
    // Меню выбора станции метро
    @FindBy(xpath = "//*[@id=\"root\"]/div/div[2]/div[2]/div[4]/div/div/input")
    private WebElement metroStationSelect;
    // Список станций метро
    @FindBy(xpath = "//*[@id=\"root\"]/div/div[2]/div[2]/div[4]/div/div[2]/ul")
    private WebElement metroStationList;
    // Поле ввода телефона
    @FindBy(xpath = "//*[@id=\"root\"]/div/div[2]/div[2]/div[5]/input")
    private WebElement phoneNumberField;
    // Кнопка "Далее"
    @FindBy(xpath = "//*[@id=\"root\"]/div/div[2]/div[3]/button")
    private WebElement nextButton;

    public void enterFirstName(String firstName) {
        firstNameField.sendKeys(firstName);
    }
    public void enterLastName(String lastName) {
        lastNameField.sendKeys(lastName);
    }
    public void enterAddress(String address) {
        addressField.sendKeys(address);
    }
    public String selectMetroStation(int index) {
        metroStationSelect.click();
        WebElement chosenStation = metroStationList.findElements(By.xpath("./child::*")).get(index);
        String chosenStationName = chosenStation.getText();
        chosenStation.click();
        return chosenStationName;
    }
    public void enterPhoneNumber(String phoneNumber) {
        phoneNumberField.sendKeys(phoneNumber);
    }
    public void pressNext() {
        nextButton.click();
    }

    // Дата аренды
    @FindBy(xpath = "//*[@id=\"root\"]/div/div[2]/div[2]/div[1]/div[1]/div/input")
    private WebElement rentDate;
    // Поле срока аренды
    @FindBy(xpath = "//*[@id=\"root\"]/div/div[2]/div[2]/div[2]/div[1]")
    private WebElement rentLength;
    // Список срока аренды
    @FindBy(xpath = "//*[@id=\"root\"]/div/div[2]/div[2]/div[2]/div[2]")
    private WebElement rentLengthList;
    // Выбор цвета
    @FindBy(xpath = "//*[@id=\"root\"]/div/div[2]/div[2]/div[3]")
    private WebElement colorCheckboxes;
    // Поле комментария курьеру
    @FindBy(xpath = "//*[@id=\"root\"]/div/div[2]/div[2]/div[4]/input")
    private WebElement commentField;
    // Кнопка "Назад"
    @FindBy(xpath = "//*[@id=\"root\"]/div/div[2]/div[3]/button[1]")
    private WebElement backButton;
    // Кнопка "Заказать"
    @FindBy(xpath = "//*[@id=\"root\"]/div/div[2]/div[3]/button[2]")
    private WebElement orderButton;

    /**
    * Формат: dd.MM.yy
    */
    public void chooseRentDay(String day) {
        rentDate.sendKeys(day);
    }
    public String chooseRentLength(int length) {
        rentLength.click();
        WebElement option = rentLengthList.findElements(By.xpath("./child::*")).get(length-1);
        String optionText = option.getText();
        option.click();
        return optionText;
    }
    public String chooseColor(int index) {
        WebElement chosenColor = colorCheckboxes.findElements(By.xpath("./child::*")).get(index);
        String chosenColorText = chosenColor.getText();
        chosenColor.click();
        return chosenColorText;
    }
    public void enterComment(String comment) {
        commentField.sendKeys(comment);
    }
    public void clickBackButton() {
        backButton.click();
    }
    public void clickOrderButton() {
        orderButton.click();
    }

    // Кнопка отмены подтверждения
    @FindBy(xpath = "//*[@id=\"root\"]/div/div[2]/div[5]/div[2]/button[1]")
    private WebElement cancelConfirmationButton;
    // Кнопка подтверждения заказа
    @FindBy(xpath = "//*[@id=\"root\"]/div/div[2]/div[5]/div[2]/button[2]")
    private WebElement confirmButton;

    public void clickConfirmButton() {
        confirmButton.click();
    }

    @FindBy(xpath = "//*[@id=\"root\"]/div/div[2]/div[5]/div[1]/div")
    private WebElement orderText;

    public String getOrderNumber() {
        return orderText.getText().replaceAll("\\D", "");
    }

    public boolean firstStep(String firstName, String lastName, String address, int stationIndex, String phoneNumber) {
        enterFirstName(firstName);
        enterLastName(lastName);
        enterAddress(address);
        selectMetroStation(stationIndex);
        enterPhoneNumber(phoneNumber);
        pressNext();
        return getStepTitle().equals("Про аренду");
    }
    public String secondStep(String day, int length, int colorIndex, String comment) {
        chooseRentLength(length);
        chooseRentDay(day);
        chooseColor(colorIndex);
        enterComment(comment);
        clickOrderButton();
        clickConfirmButton();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.textMatches(By.xpath("//*[@id=\"root\"]/div/div[2]/div[5]/div[1]/div"), Pattern.compile("\\d+")));
        return getOrderNumber();
    }
    public String secondStep(String day, int length, int colorIndex) {
        chooseRentLength(length);
        chooseRentDay(day);
        chooseColor(colorIndex);
        clickOrderButton();
        clickConfirmButton();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.textMatches(By.xpath("//*[@id=\"root\"]/div/div[2]/div[5]/div[1]/div"), Pattern.compile("\\d+")));
        return getOrderNumber();
    }
}
