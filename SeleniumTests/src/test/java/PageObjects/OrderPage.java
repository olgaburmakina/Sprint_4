package pageobjects;

import constants.Constants;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.regex.Pattern;

public class OrderPage {
    private final String pageURL = Constants.ORDER_PAGE_URL;

    // Конструктор
    private final WebDriver driver;
    public OrderPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    // Название шага
    @FindBy(css = "div[class*='Order_Header']")
    private WebElement stepTitle;

    // Поле ввода имени
    @FindBy(css = "input[placeholder*='Имя']")
    private WebElement firstNameField;
    // Поле ввода фамилии
    @FindBy(css = "input[placeholder*='Фамилия']")
    private WebElement lastNameField;
    // Поле ввода адреса
    @FindBy(css = "input[placeholder*='Адрес']")
    private WebElement addressField;
    // Поле выбора станции метро
    @FindBy(css = "input[placeholder*='Станция метро']")
    private WebElement metroStationSelect;
    // Поле ввода телефона
    @FindBy(css = "input[placeholder*='Телефон']")
    private WebElement phoneNumberField;
    // Кнопка "Далее"
    @FindBy(css = "div[class*='Order_NextButton'] > button")
    private WebElement nextButton;

    // Дата аренды
    @FindBy(css = "input[placeholder*='Когда']")
    private WebElement rentDate;
    // Поле срока аренды
    @FindBy(css = "div.Dropdown-placeholder")
    private WebElement rentLength;
    // Список срока аренды
    @FindBy(css = "div.Dropdown-menu")
    private WebElement rentLengthList;
    // Выбор цвета
    @FindBy(css = "div[class*='Order_Checkboxes']")
    private WebElement colorCheckboxes;
    // Поле комментария курьеру
    @FindBy(css = "input[placeholder*='Комментарий для курьера']")
    private WebElement commentField;
    // Кнопка "Назад"
    @FindBy(xpath = "//div[contains(@class, 'Order_Buttons')]/button[contains(text(), 'Назад')]")
    private WebElement backButton;
    // Кнопка "Заказать"
    @FindBy(xpath = "//div[contains(@class, 'Order_Buttons')]/button[contains(text(), 'Заказать')]")
    private WebElement orderButton;

    // Кнопка отмены подтверждения
    @FindBy(xpath = "//div[contains(@class, 'Order_Modal')]/div[contains(@class, 'Order_Buttons')]/button[contains(text(), 'Нет')]")
    private WebElement cancelConfirmationButton;
    // Кнопка подтверждения заказа
    @FindBy(xpath = "//div[contains(@class, 'Order_Modal')]/div[contains(@class, 'Order_Buttons')]/button[contains(text(), 'Да')]")
    private WebElement confirmButton;
    // Заголовок окна
    @FindBy(css = "div[class*='Order_ModalHeader']")
    private WebElement modalHeader;
    // Текст окна
    @FindBy(css = "div[class*='Order_Text']")
    private WebElement modalText;

    public String getURL() {
        return pageURL;
    }
    public void open() {
        driver.get(pageURL);
    }
    public String getStepTitle() {
        return stepTitle.getText();
    }

    public void enterFirstName(String firstName) {
        firstNameField.sendKeys(firstName);
    }
    public void enterLastName(String lastName) {
        lastNameField.sendKeys(lastName);
    }
    public void enterAddress(String address) {
        addressField.sendKeys(address);
    }
    public String selectMetroStation(String station) throws InterruptedException {
        metroStationSelect.sendKeys(station);
        Thread.sleep(500);
        metroStationSelect.sendKeys(Keys.ARROW_DOWN, Keys.ENTER);
        return metroStationSelect.getAttribute("value");
    }
    public void enterPhoneNumber(String phoneNumber) {
        phoneNumberField.sendKeys(phoneNumber);
    }
    public void clickNext() {
        nextButton.click();
    }

    /**
     * Формат: dd.MM.yy
     */
    public void chooseRentDay(String day) {
        rentDate.sendKeys(day, Keys.ENTER);
    }
    public String chooseRentLength(int length) {
        rentLength.click();
        WebElement option = rentLengthList.findElement(By.xpath("./div[" + length + "]"));
        String optionText = option.getText();
        option.click();
        return optionText;
    }
    public String chooseColor(int colorNumber) {
        WebElement chosenColor = colorCheckboxes.findElement(By.xpath("./label[" + colorNumber + "]"));
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
    public void clickConfirmButton() {
        confirmButton.click();
    }
    public String getModalHeader() {
        return modalHeader.getText();
    }
    public String getModalText() {
        return modalText.getText();
    }
    public String getOrderNumber() {
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.textMatches(By.xpath("//div[contains(@class, 'Order_Text')]"), Pattern.compile("\\d+")));
        return modalText.getText().replaceAll("\\D", "");
    }

    public boolean firstStep(String firstName, String lastName, String address, String metroStationName, String phoneNumber) throws InterruptedException {
        enterFirstName(firstName);
        enterLastName(lastName);
        enterAddress(address);
        selectMetroStation(metroStationName);
        enterPhoneNumber(phoneNumber);
        clickNext();
        return getStepTitle().equals("Про аренду");
    }
    public String secondStep(String day, int length, int colorIndex, String comment) {
        chooseRentLength(length);
        chooseRentDay(day);
        chooseColor(colorIndex);
        enterComment(comment);
        clickOrderButton();
        clickConfirmButton();
        return getOrderNumber();
    }
    public String secondStep(String day, int length, int colorIndex) {
        chooseRentLength(length);
        chooseRentDay(day);
        chooseColor(colorIndex);
        clickOrderButton();
        clickConfirmButton();
        return getOrderNumber();
    }
}