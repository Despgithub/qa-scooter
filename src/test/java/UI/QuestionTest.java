package UI;

import UI.Pages.MainPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

public class QuestionTest {
    private WebDriver driver;

    @Before
    public void setup() {
        driver = WebDriverManager.chromedriver().create();
        //driver = WebDriverManager.firefoxdriver().create();
        driver.manage().window().maximize();
    }

    @Test
    public void checkQuestions() {
        MainPage mainPage = new MainPage(driver);
        mainPage.open();
        mainPage.clickConfirmCookieButton();
        mainPage.checkQuestions();
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
