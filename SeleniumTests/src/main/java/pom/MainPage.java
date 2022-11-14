package pom;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class MainPage {
    private final WebDriver driver;
    private final String url = "https://qa-scooter.praktikum-services.ru/";

    //Кнопка "Да, все привыкли" во всплывающем окне о куки:
    private final By cookieButton = By.id("rcc-confirm-button");

    private By faqAccordionItemHeading;
    private By faqAccordionItemPanel;
    private By faqAccordionItemPanelText;


    public MainPage(WebDriver driver, String accordionItemId) {
        this.driver = driver;
        this.faqAccordionItemHeading = By.xpath(".//div[@id='"+ accordionItemId +"']");
        this.faqAccordionItemPanel = By.xpath(".//div[@aria-labelledby='"+ accordionItemId +"']");
        this.faqAccordionItemPanelText = By.xpath(".//div[@aria-labelledby='"+ accordionItemId +"']/p");
    }

    //Открыть сайт ЯндексСамокат
    public void open() {
        driver.get(url);
    }

    //Нажать на кнопку "Да, все привыкли"
    public void pressCookieButton() {
        driver.findElement(cookieButton).click();
    }

    public void clickFaqAccordionItemHeading() {
        WebElement element = driver.findElement(faqAccordionItemHeading);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
        new Actions(driver).moveToElement(element).click().perform();
    }

    public WebElement getFaqAccordionItemPanel() {
        return driver.findElement(faqAccordionItemPanel);
    }

    public WebElement getFaqAccordionItemPanelText() {
        return driver.findElement(faqAccordionItemPanelText);
    }


}
