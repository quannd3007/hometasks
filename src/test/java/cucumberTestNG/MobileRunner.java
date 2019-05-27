package cucumberTestNG;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import cucumber.api.testng.AbstractTestNGCucumberTests;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import org.junit.runner.RunWith;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.*;

import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeoutException;

@RunWith(Cucumber.class)
@CucumberOptions(monochrome = true, features = "src/test/resources/features/mobile", plugin = {"pretty",
        "html:target/cucumber-html-report"}, glue = {"steps", "cucumberTestNG"}, tags = {"@Mobile"})
public class MobileRunner extends AbstractTestNGCucumberTests {

    AppiumDriverLocalService service;

    public AppiumDriverLocalService startServer() {
        AppiumServiceBuilder builder = new AppiumServiceBuilder().usingPort(4723)
                .withArgument(GeneralServerFlag.SESSION_OVERRIDE)
                .withArgument(GeneralServerFlag.LOG_LEVEL, "error");
        service = AppiumDriverLocalService.buildService(builder);
        service.start();
        return service;
    }

    public void stopServer() {
        try {
            service.stop();
            System.out.println("Success, Server stopped.");
        } catch (Exception e) {
            System.out.println("Appium server could not be stopped.");
        }
    }

    @BeforeClass
    @Parameters({"platformName", "udid"})
    public void setUp(String platformName, String udid) throws Exception {
        startServer();
        waitAppiumStarted();

        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setCapability(MobileCapabilityType.UDID, udid);
        cap.setCapability(MobileCapabilityType.PLATFORM_NAME, platformName);
        cap.setCapability(MobileCapabilityType.DEVICE_NAME, udid);
        cap.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");

        switch (platformName.toLowerCase()) {
            case "android":
                cap.setCapability("app", System.getProperty("user.dir") + "/app/AndroidCalculator.apk");
                DriverManager.setTLDriver(new AndroidDriver(service.getUrl(), cap));
                break;
            case "ios":
                break;
            default:
                throw new Exception("Currently just only support Android and iOS!");
        }
    }

    @AfterClass
    public void tearDown() {
        DriverManager.getTLDriver().quit();
        stopServer();
    }

    public void waitFor(int waitForTimeOut, int waitForInterval, Callable<?> callable) throws TimeoutException {
        double currentTime = System.currentTimeMillis();
        double endTime = currentTime + (waitForTimeOut * 1000);

        Boolean boo = true;
        while (boo) {
            if (System.currentTimeMillis() > endTime) {
                boo = false;
                throw new TimeoutException("Time Out!");
            }

            try {
                if (callable.call() != null && callable.call().toString().equals("true")) {
                    boo = false;
                } else {
                    Thread.sleep(1000 * waitForInterval);
                }
            } catch (Exception e) {
            }
        }
    }

    public void waitAppiumStarted() {
        Callable<Object> callable = new Callable<Object>() {
            @Override
            public Boolean call() {
                return isAppiumStarted();
            }
        };

        try {
            waitFor(60, 1, callable);
        } catch (Exception e) {
            System.out.println(service.getUrl() + " not started\n" + e.getMessage());
            e.printStackTrace();
        }

    }

    private boolean isAppiumStarted() {
        try {
            URL url = new URL(service.getUrl().toString() + "/status");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            return connection.getResponseCode() == 200;
        } catch (MalformedURLException e1) {
        } catch (ConnectException e) {
        } catch (Exception ex) {
            System.out.println("Can not start Appium!");
        }
        return false;
    }

}
