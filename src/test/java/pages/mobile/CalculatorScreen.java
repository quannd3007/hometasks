package pages.mobile;

import cucumberTestNG.DriverManager;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class CalculatorScreen {
    WebDriver driver = DriverManager.getTLDriver();

    public CalculatorScreen() {
        PageFactory.initElements(new AppiumFieldDecorator(DriverManager.getTLDriver()), this);
    }

    @FindBy(id = "com.android2.calculator3:id/cling_dismiss") private WebElement btnOK;
    @FindBy(id = "com.android2.calculator3:id/plus") private WebElement btnPlus;
    @FindBy(id = "com.android2.calculator3:id/equal") private WebElement btnEqual;
    @FindBy(xpath = "//android.widget.EditText[@index=0]") private WebElement result;

    public void add(String number1,String number2){
        driver.findElement(By.xpath("//android.widget.Button[@text='"+number1+"']")).click();
        btnPlus.click();
        driver.findElement(By.xpath("//android.widget.Button[@text='"+number2+"']")).click();
        btnEqual.click();
    }

    public void clickOnOKButton() {
        btnOK.click();
    }

    public void result(String rs) {
        Assert.assertEquals(result.getText(), rs);
    }

}
