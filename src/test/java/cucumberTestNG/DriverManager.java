package cucumberTestNG;

import org.openqa.selenium.WebDriver;

public class DriverManager {

	private static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();

    public synchronized static void setTLDriver(WebDriver driver) {
        tlDriver.set(driver);
    }

    public synchronized static WebDriver getTLDriver() {
        return tlDriver.get();
    }
}
