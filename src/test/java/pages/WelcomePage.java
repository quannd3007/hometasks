package pages;

import cucumberTestNG.DriverManager;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class WelcomePage extends PageHelper{
    public WelcomePage() {
        PageFactory.initElements(DriverManager.getTLDriver(), this);
    }

    @FindBy(id = "header-link-email") private WebElement headerEmail;

    public void shouldBeOnWelcomePage(){ waitUntilUrlBecome("/welcome/index"); }

    public void shouldSeeHeaderEmail(){ waitFor(headerEmail); }

}
