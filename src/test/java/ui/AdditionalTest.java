package ui;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import ui.pages.MainPage;
import ui.pages.OrderPage;

public class AdditionalTest {
    private WebDriver driver;

    @Before
    public void setup() {
        driver = WebDriverManager.chromedriver().create();
        //driver = WebDriverManager.firefoxdriver().create();
        driver.manage().window().maximize();
    }

    @Test
    public void clickScooterLogo() {
        MainPage mainPage = new MainPage(driver);
        mainPage.open();
        mainPage.clickOrderButton(true);
        OrderPage orderPage = new OrderPage(driver);
        orderPage.checkScooterLink();
    }

    @Test
    public void clickYandexLogo() {
        MainPage mainPage = new MainPage(driver);
        mainPage.open();
        mainPage.checkYandexLink();
    }

    @Test
    public void orderErrors() {
        MainPage mainPage = new MainPage(driver);
        mainPage.open();
        mainPage.clickOrderButton(true);
        OrderPage orderPage = new OrderPage(driver);
        orderPage.checkErrors();
    }

    @Test
    public void findErrorOrderNumber() {
        MainPage mainPage = new MainPage(driver);
        mainPage.open();
        mainPage.clickConfirmCookieButton();
        mainPage.checkErrorOrderNumber();
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
