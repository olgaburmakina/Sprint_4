package pom;

import org.junit.Assert;
import org.openqa.selenium.*;

import static org.hamcrest.core.StringStartsWith.startsWith;

public class OrderPage {

    //Главная страница
    private final String url = "https://qa-scooter.praktikum-services.ru/";

    //Кнопка "Да, все привыкли" во всплывающем окне о куки:
    private final By cookieButton = By.id("rcc-confirm-button");

    //Кнопка "Заказать" вверху:
    private final By orderButtonUp = By.xpath(".//div[contains(@class,'Header_Nav')]/button[@class='Button_Button__ra12g']");

    //Кнопка "Заказать" внизу:
    private final By orderButtonDown = By.xpath(".//div[contains(@class,'Home_FinishButton__1_cWm')]/button[@class='Button_Button__ra12g Button_Middle__1CSJM']");

    //поле "Имя"
    private final By fieldName = By.xpath(".//input[@placeholder='* Имя']");

    //Поле "Фамилия"
    private final By fieldLastName = By.xpath(".//input[@placeholder='* Фамилия']");

    //Поле "Адрес"
    private final By fieldAddress = By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']");

    //Поле "Станция метро"
    private final By fieldMetroStation = By.xpath(".//input[@class='select-search__input']");

    //Поле "Телефон"
    private final By fieldPhoneNumber = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");

    //Кнопка "Далее"
    private final By nextButton = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM']");


    //Поле "Когда привезти самокат"
    private final By deliveryDate = By.xpath(".//input[@placeholder='* Когда привезти самокат']");

    //Поле "Срок Аренды"
    private final By dropdownPeriod = By.xpath(".//div[@class='Dropdown-placeholder']");


    //Кнопка "Заказать" после заполнения всех необходимых полей
    private final By middleOrderButton = By.xpath(".//div[contains(@class,'Order_Buttons__1xGrp')]/button[text()='Заказать']");

    //Кнопка "Да"
    private final By confirmButton = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM' and contains(text(),'Да')]");

    //Всплывающее окно "Заказ оформлен"
    private final By orderIsCreated = By.xpath(".//div[contains(text(),'Заказ оформлен')]");

    private final WebDriver driver;

    public OrderPage(WebDriver driver){

        this.driver = driver;
    }

    //открыть сайт ЯндексСамокат
    public void open() {
        driver.get(url);
    }

    //Убрать всплывающее сообщение про куки
    public void pressCookieButton() {
        driver.findElement(cookieButton).click();
    }

    //Выбрать кнопку "Заказать": 0 - кнопка вверху, 1 - кнопка внизу
    public void clickOrderButton(int numberOfButton) {
        if (numberOfButton == 0) {
            driver.findElement(orderButtonUp).click();
        } else if (numberOfButton == 1) {
            WebElement element = driver.findElement(By.className("accordion"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
            driver.findElement(orderButtonDown).click();
        }
    }

    //Заполнени поля "Имя"
    public void inputName(String text){//ввод имени в поле имени
        driver.findElement(fieldName).sendKeys(text);
    }

    //Заполнение поля "Фамилия"
    public void inputLastName(String text){//ввод фамилии
        driver.findElement(fieldLastName).sendKeys(text);
    }

    //Заполнение поля "Адрес"
    public void inputAddress(String text){//ввод адреса
        driver.findElement(fieldAddress).sendKeys(text);
    }

    //Заполнение поля "Станция метро"
    public void selectMetroStation(String metro){
        driver.findElement(fieldMetroStation).click();
        driver.findElement(fieldMetroStation).sendKeys(metro);
        driver.findElement(fieldMetroStation).sendKeys(Keys.ARROW_DOWN, Keys.ENTER);
    }

    //Заполнение поля "Телефон"
    public void inputPhoneNumber(String text){//ввод номера телефона
        driver.findElement(fieldPhoneNumber).sendKeys(text);
    }

    //Нажать на кнопку "Далее"
    public void clickNextButton(){//кликнуть на кнопку "Далее"
        driver.findElement(nextButton).click();
    }

    //Заполнение поля "Когда привезти самокат"
    public void selectDeliveryDate(String text){
        driver.findElement(deliveryDate).click();
        driver.findElement(deliveryDate).sendKeys(text, Keys.ENTER);
    }

    //Заполнение поля "Срок аренды"
    public void selectDropdownPeriod(String period){
        driver.findElement(dropdownPeriod).click();
        driver.findElement(By.xpath(".//div[@class='Dropdown-menu']/div[text()='" +period+"']")).click();
    }

    //Нажать на кнопку "Заказать"
    public void clickMiddleOrderButton(){//клик на кнопку "Заказать"
        driver.findElement(middleOrderButton).click();
    }

    //Подтверждение оформления заказа
    public void clickConfirmButton(){//клик на "Да" в окне подтверждения заказа
        driver.findElement(confirmButton).click();
    }

    //Проверить, создан ли заказ
    public void checkOrderIsCreated(){
        String textOfCreated = driver.findElement(orderIsCreated).getText();
        Assert.assertThat("Ошибка, заказ не оформлен, как же так, ёжкин кот!?!", textOfCreated, startsWith("Заказ оформлен"));
    }
}