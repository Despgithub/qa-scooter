package ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ConfirmPopup {

    private final static By CONFIRM_BUTTON = By.xpath("//button[text()='Да']");

    private final WebDriver driver;

    public ConfirmPopup(WebDriver driver) {
        this.driver = driver;
    }

    public void clickConfirmButton() {
        driver.findElement(CONFIRM_BUTTON).click();
    }
}
