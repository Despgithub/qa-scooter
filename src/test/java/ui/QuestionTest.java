package ui;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import ui.pages.MainPage;

@RunWith(Parameterized.class)
public class QuestionTest {

    private final int questionNumber;
    private WebDriver driver;

    public QuestionTest(int questionNumber) {
        this.questionNumber = questionNumber;
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
                {1},
                {2},
                {3},
                {4},
                {5},
                {6},
                {7},
                {8}
        };
    }

    @Test
    public void checkQuestion() {
        MainPage mainPage = new MainPage(driver);
        mainPage.open();
        mainPage.clickConfirmCookieButton();
        mainPage.checkQuestion(questionNumber);
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
