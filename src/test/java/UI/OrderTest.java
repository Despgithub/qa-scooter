package UI;

import UI.Pages.ConfirmPopup;
import UI.Pages.MainPage;
import UI.Pages.OrderPage;
import UI.Pages.SuccessOrderPopup;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;

@RunWith(Parameterized.class)
public class OrderTest {

    private final String name;
    private final String surname;
    private final String address;
    private final String metro;
    private final String phoneNumber;
    private final String deliveryDate;
    private final String rentalPeriod;
    private final String colour;
    private final String comment;
    private WebDriver driver;

    public OrderTest(String name, String surname, String address, String metro, String phoneNumber,
                     String deliveryDate, String rentalPeriod, String colour, String comment) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.metro = metro;
        this.phoneNumber = phoneNumber;
        this.deliveryDate = deliveryDate;
        this.rentalPeriod = rentalPeriod;
        this.colour = colour;
        this.comment = comment;
    }

    @Before
    public void setup() {
        driver = WebDriverManager.chromedriver().create();
        //driver = WebDriverManager.firefoxdriver().create();
        driver.manage().window().maximize();
    }

    @Parameterized.Parameters
    public static Object[][] getOrderData() {
        return new Object[][]{
                {"Остап", "Бендер", "Тверская д.1 кв.1", "Тверская", "+79111111111", "17.09.2023", "сутки",
                        "чёрный жемчуг", "Почём опиум для народа?"},
                {"Киса", "Воробьянинов", "Фрунзенская д.13 кв.13", "Фрунзенская", "+79111111112", "10.09.2023",
                        "двое суток", "серая безысходность", "Же не манж па сис жур"}
        };
    }

    @Test
    public void checkOrder() {
        MainPage mainPage = new MainPage(driver);
        mainPage.open();
        mainPage.clickConfirmCookieButton();
        mainPage.clickOrderButton();
        OrderPage orderPage = new OrderPage(driver);
        orderPage.makeOrder(name, surname, address, metro, phoneNumber, deliveryDate, rentalPeriod, colour, comment);
        ConfirmPopup confirmPopup = new ConfirmPopup(driver);
        confirmPopup.clickConfirmButton();
        SuccessOrderPopup successOrderPopup = new SuccessOrderPopup(driver);
        successOrderPopup.checkOrderNumber();
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}