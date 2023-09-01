package UI.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class OrderPage {

    private final static By NAME_Field = By.xpath("//input[@placeholder='* Имя']");
    private final static By SURENAME_FIELD = By.xpath("//input[@placeholder='* Фамилия']");
    private final static By ADDRESS_FIELD = By.xpath("//input[@placeholder='* Адрес: куда привезти заказ']");
    private final static By METRO_STATION_FIELD = By.xpath("//input[@placeholder='* Станция метро']");
    private final static By PHONE_NUMBER_FIELD = By.xpath("//input[@placeholder='* Телефон: на него позвонит курьер']");
    private final static By NEXT_BUTTON = By.xpath("//button[text()='Далее']");
    private final static By DELIVERY_DATE = By.xpath("//input[@placeholder='* Когда привезти самокат']");
    private final static By RENTAL_INPUT = By.xpath("//div[@class='Dropdown-placeholder']");
    private final static String RENTAL_PERIOD = "//div[@class='Dropdown-menu']/div[text()='%s']";
    private final static String SCOOTER_COLOUR = "//label[text()='%s']";
    private final static By COMMENT_FIELD = By.xpath("//input[@placeholder='Комментарий для курьера']");
    private final static By ORDER_BUTTON = By.xpath("//div[contains(@class,'Order_Buttons')]//button[text()='Заказать']");

    private final WebDriver driver;

    public OrderPage(WebDriver driver) {
        this.driver = driver;
    }

    public void inputName(String name) {
        driver.findElement(NAME_Field).sendKeys(name);
    }

    public void inputSurname(String surname) {
        driver.findElement(SURENAME_FIELD).sendKeys(surname);
    }

    public void inputAddress(String address) {
        driver.findElement(ADDRESS_FIELD).sendKeys(address);
    }

    public void selectMetroStation(String metro) {
        driver.findElement(METRO_STATION_FIELD).click();
        driver.findElement(METRO_STATION_FIELD).sendKeys(metro);
        driver.findElement(METRO_STATION_FIELD).sendKeys(Keys.ARROW_DOWN, Keys.ENTER);
    }

    public void inputPhoneNumber(String phoneNumber) {
        driver.findElement(PHONE_NUMBER_FIELD).sendKeys(phoneNumber);
    }

    public void clickNextButton() {
        driver.findElement(NEXT_BUTTON).click();
    }

    public void selectDeliveryDate(String deliveryDate) {
        driver.findElement(DELIVERY_DATE).click();
        driver.findElement(DELIVERY_DATE).sendKeys(deliveryDate, Keys.ENTER);
    }

    public void selectRentalPeriod(String rentalPeriod) {
        driver.findElement(RENTAL_INPUT).click();
        driver.findElement(By.xpath(String.format(RENTAL_PERIOD, rentalPeriod))).click();
    }

    public void chooseScooterColour(String colour) {
        driver.findElement(By.xpath(String.format(SCOOTER_COLOUR, colour))).click();
    }

    public void inputComment(String comment) {
        driver.findElement(COMMENT_FIELD).sendKeys(comment);
    }

    public void clickOrderButton() {
        driver.findElement(ORDER_BUTTON).click();
    }

    public void makeOrder(String name, String surname, String address, String metro, String phoneNumber, String deliveryDate, String rentalPeriod, String colour, String comment) {
        inputName(name);
        inputSurname(surname);
        inputAddress(address);
        selectMetroStation(metro);
        inputPhoneNumber(phoneNumber);
        clickNextButton();
        selectDeliveryDate(deliveryDate);
        selectRentalPeriod(rentalPeriod);
        chooseScooterColour(colour);
        inputComment(comment);
        clickOrderButton();
    }

}
