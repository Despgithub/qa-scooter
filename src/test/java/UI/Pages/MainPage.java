package UI.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MainPage {

    private final static By CONFIRM_COOKIE_BUTTON = By.xpath("//button[@id='rcc-confirm-button']");
    private final static By QUESTIONS_BLOCK = By.xpath("//div[@class='accordion']");
    private final static By QUESTIONS = By.xpath("//div[contains(@id,'accordion__heading-')]");
    private final static String QUESTION = "//div[@id='accordion__heading-%d']";
    private final static String ANSWER = "//div[@id='accordion__panel-%d']";
    private final static String ORDER_BUTTONS = "//button[text()='Заказать']";
    private final static By HEADER_ORDER_BUTTON = By.xpath("//div[contains(@class,'Header_Nav')]" + ORDER_BUTTONS);
    private final static By HOME_ORDER_BUTTON = By.xpath("//div[contains(@class,'Home_FinishButton')]" + ORDER_BUTTONS);
    private final static By YANDEX_LOGO = By.xpath("//img[@alt='Yandex']");
    private final static By ORDER_STATUS_BUTTON = By.xpath("//button[text()='Статус заказа']");
    private final static By INPUT_ORDER_FIELD = By.xpath("//input[@placeholder='Введите номер заказа']");
    private final static By GO_BUTTON = By.xpath("//button[text()='Go!']");
    private final static By NOT_FOUND_IMAGE = By.xpath("//div[contains(@class,'Track_NotFound')]//img[@alt='Not found']");


    private final WebDriver driver;

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickConfirmCookieButton() {
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(CONFIRM_COOKIE_BUTTON));
        driver.findElement(CONFIRM_COOKIE_BUTTON).click();
    }

    public void open() {
        driver.get("https://qa-scooter.praktikum-services.ru/");
    }

    public void checkQuestions() {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(QUESTIONS_BLOCK));
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(QUESTIONS_BLOCK));
        List<WebElement> questions = driver.findElements(QUESTIONS);
        assertEquals("Количество вопросов изменилось", questions.size(), 8);
        for (int i = 0; i < questions.size(); i++) {
            driver.findElement(By.xpath(String.format(QUESTION, i))).click();
            new WebDriverWait(driver, Duration.ofSeconds(2))
                    .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format(ANSWER, i))));
            String question = driver.findElement(By.xpath(String.format(QUESTION, i))).getText();
            String answer = driver.findElement(By.xpath(String.format(ANSWER, i))).getText();
            if (i == 0) {
                assertEquals(String.format("Ответ на вопрос \"%s\" не соответствует ожидаемому!", question), "Сутки — 400 рублей. Оплата курьеру — наличными или картой.", answer);
            } else if (i == 1) {
                assertEquals(String.format("Ответ на вопрос \"%s\" не соответствует ожидаемому!", question), "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим.", answer);
            } else if (i == 2) {
                assertEquals(String.format("Ответ на вопрос \"%s\" не соответствует ожидаемому!", question), "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30.", answer);
            } else if (i == 3) {
                assertEquals(String.format("Ответ на вопрос \"%s\" не соответствует ожидаемому!", question), "Только начиная с завтрашнего дня. Но скоро станем расторопнее.", answer);
            } else if (i == 4) {
                assertEquals(String.format("Ответ на вопрос \"%s\" не соответствует ожидаемому!", question), "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010.", answer);
            } else if (i == 5) {
                assertEquals(String.format("Ответ на вопрос \"%s\" не соответствует ожидаемому!", question), "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится.", answer);
            } else if (i == 6) {
                assertEquals(String.format("Ответ на вопрос \"%s\" не соответствует ожидаемому!", question), "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои.", answer);
            } else if (i == 7) {
                assertEquals(String.format("Ответ на вопрос \"%s\" не соответствует ожидаемому!", question), "Да, обязательно. Всем самокатов! И Москве, и Московской области.", answer);
            }
        }
    }

    public void clickOrderButton() {
        List<WebElement> buttons = driver.findElements(By.xpath(ORDER_BUTTONS));
        assertEquals("Количество кнопок на странице не равно 2", 2, buttons.size());
        int button = (int) ((Math.random() * (2)));
        if (button == 0) {
            driver.findElement(HEADER_ORDER_BUTTON).click();
        } else {
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(HOME_ORDER_BUTTON));
            new WebDriverWait(driver, Duration.ofSeconds(2))
                    .until(ExpectedConditions.visibilityOfElementLocated(HOME_ORDER_BUTTON));
            driver.findElement(HOME_ORDER_BUTTON).click();
        }
    }

    public void checkYandexLink() {
        String originalWindow = driver.getWindowHandle();
        driver.findElement(YANDEX_LOGO).click();
        for (String windowHandle : driver.getWindowHandles()) {
            if (!originalWindow.contentEquals(windowHandle)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='Новости']")));
        String currentUrl = driver.getCurrentUrl();
        assertEquals("Ссылка не совпадает", "https://dzen.ru/?yredirect=true", currentUrl);
    }

    public void clickStatusOrderButton() {
        driver.findElement(ORDER_STATUS_BUTTON).click();
    }

    public void inputOrderNumber(String orderNumber) {
        new WebDriverWait(driver, Duration.ofSeconds(2))
                .until(ExpectedConditions.visibilityOfElementLocated(INPUT_ORDER_FIELD));
        driver.findElement(INPUT_ORDER_FIELD).sendKeys(orderNumber);
    }

    public void clickGoButton() {
        driver.findElement(GO_BUTTON).click();
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//Button[text()='Посмотреть']")));
    }

    public void checkErrorOrderNumber() {
        clickStatusOrderButton();
        inputOrderNumber("000");
        clickGoButton();
        assertTrue(driver.findElement(NOT_FOUND_IMAGE).isDisplayed());
    }

}
