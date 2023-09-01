package UI.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.Assert.assertTrue;

public class SuccessOrderPopup {

    private final static By ORDER_TEXT = By.xpath("//div[contains(@class,'Order_Text')]");

    private final WebDriver driver;

    public SuccessOrderPopup(WebDriver driver) {
        this.driver = driver;
    }

    public void checkOrderNumber() {
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(ORDER_TEXT));
        String orderMessage = driver.findElement(ORDER_TEXT).getText();
        assertTrue("Сообщение об успешном заказе не появилось", orderMessage.matches("Номер заказа: [0-9]{1,6}.  Запишите его:\n" +
                "пригодится, чтобы отслеживать статус"));
    }
}
