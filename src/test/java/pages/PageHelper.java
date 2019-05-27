package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import cucumberTestNG.DriverManager;

public class PageHelper {
    WebDriver driver = DriverManager.getTLDriver();

    public void inputField(WebElement element, String info) {
        element.sendKeys(info);
    }

    public void clickOn(WebElement element) {
        element.click();
    }

    public void waitFor(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void waitUntilUrlBecome(String expect) {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.urlContains(expect));
    }

}
