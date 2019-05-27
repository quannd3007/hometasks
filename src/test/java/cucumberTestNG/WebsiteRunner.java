package cucumberTestNG;

import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;

@RunWith(Cucumber.class)
@CucumberOptions(monochrome = true, features = "src/test/resources/features/web", plugin = {"pretty",
        "html:target/cucumber-html-report"}, glue = {"steps", "cucumberTestNG"}, tags = {"@Web"})
public class WebsiteRunner extends AbstractTestNGCucumberTests {

    @BeforeClass
    @Parameters({"browserName"})
    public void setUp(String browserName) throws Exception {
        switch (browserName) {
            case "chrome":
                DriverManager.setTLDriver(Chrome());
                break;
            case "firefox":
                DriverManager.setTLDriver(Firefox());
                break;
            case "remote_chrome":
                DriverManager.setTLDriver(RemoteChrome());
                break;
            case "remote_firefox":
                DriverManager.setTLDriver(RemoteFirefox());
                break;
        }
    }

    private WebDriver Firefox() {
        WebDriverManager.firefoxdriver().setup();
        FirefoxOptions option = new FirefoxOptions();
        option.addArguments("--headless");

        return new FirefoxDriver(option);
    }

    private WebDriver Chrome() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions option = new ChromeOptions();
        option.addArguments("--headless");

        return new ChromeDriver(option);
    }

    private RemoteWebDriver RemoteChrome() throws Exception {
        WebDriverManager.chromedriver().setup();
        ChromeOptions option = new ChromeOptions();
        option.addArguments("--headless");
        return new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), option);
    }

    private RemoteWebDriver RemoteFirefox() throws Exception {
        WebDriverManager.firefoxdriver().setup();
        FirefoxOptions option = new FirefoxOptions();
        option.addArguments("--headless");
        return new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), option);
    }

    @AfterClass
    public void tearDown() {
        DriverManager.getTLDriver().quit();
    }

}
