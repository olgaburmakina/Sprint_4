package textInArrow;

import org.hamcrest.MatcherAssert;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import pom.MainPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class checkTextInArrow {
    private WebDriver driver;
    private final String faqAccordionItemId;
    private final boolean faqAccordionItemPanelHiddenExpected; // true - элемент скрыт, false - элемент видим
    private final String faqAccordionItemPanelTextExpected;

    public checkTextInArrow(String accordionItemId, boolean faqAccordionItemPanelHiddenExpected, String faqAccordionItemPanelText) {
        this.faqAccordionItemId = accordionItemId;
        this.faqAccordionItemPanelHiddenExpected = faqAccordionItemPanelHiddenExpected;
        this.faqAccordionItemPanelTextExpected = faqAccordionItemPanelText;
    }

    @Parameterized.Parameters
    public static Object[][] getInputData() {
        return new Object[][]{
                {"accordion__heading-0", false, "Сутки — 400 рублей. Оплата курьеру — наличными или картой."},
                {"accordion__heading-1", false, "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, " +
                        "можете просто сделать несколько заказов — один за другим."},
                {"accordion__heading-2", false, "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, " +
                        "когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30."},
                {"accordion__heading-3", false, "Только начиная с завтрашнего дня. Но скоро станем расторопнее."},
                {"accordion__heading-4", false, "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку " +
                        "по красивому номеру 1010."},
                {"accordion__heading-5", false, "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — " +
                        "даже если будете кататься без передышек и во сне. Зарядка не понадобится."},
                {"accordion__heading-6", false, "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. " +
                        "Все же свои."},
                {"accordion__heading-7", false, "Да, обязательно. Всем самокатов! И Москве, и Московской области."},
        };
    }

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        //driver = new FirefoxDriver(); для проверки в файрфоксе

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        MainPage mainPage = new MainPage(driver, faqAccordionItemId);
        mainPage.open();
        mainPage.pressCookieButton();
    }

    @Test
    public void checkQuestions() {
        MainPage mainPage = new MainPage(driver, faqAccordionItemId);

        //Кликнуть по вопросу
        mainPage.clickFaqAccordionItemHeading();
        //Проверить, виден ли ответ
        WebElement element = mainPage.getFaqAccordionItemPanel();
        boolean visibleActual = Boolean.parseBoolean(element.getAttribute("hidden"));
        assertEquals(visibleActual, faqAccordionItemPanelHiddenExpected);
        //Проверить текст ответа
        String textActual = mainPage.getFaqAccordionItemPanelText().getText();
        MatcherAssert.assertThat(textActual, containsString(faqAccordionItemPanelTextExpected));
    }


    @After
    public void tearDown() {
        driver.quit();
    }
}
