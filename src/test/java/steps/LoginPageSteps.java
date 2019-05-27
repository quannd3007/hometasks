package steps;

import cucumber.api.DataTable;
import org.openqa.selenium.WebDriver;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.api.java.en.Given;
import cucumberTestNG.DriverManager;
import pages.LoginPage;
import pages.WelcomePage;
import java.util.Map;

public class LoginPageSteps {
	WebDriver driver = DriverManager.getTLDriver();
	String url = "https://cp.qc.coccoc.com/sign-in";

	@Given("^I navigate to the Login page$")
	public void iOpenTheMainPage() {
		driver.get(url);
	}

	@Then("^I should be on Login Page$")
	public void iShouldBeOnLoginPage() {
		new LoginPage().shouldBeOnLoginPage();
	}

	@When("^I login to account with$")
	public void iLoginToAccountWith(DataTable dataTable) {
		Map map = dataTable.asMaps(String.class,String.class).get(0);
		new LoginPage().login(map);
	}

	@Then("^I should login successfully$")
	public void iShouldLoginSuccessfully() {
		WelcomePage welcomePage = new WelcomePage();
		welcomePage.shouldBeOnWelcomePage();
		welcomePage.shouldSeeHeaderEmail();
	}

	@When("^I enter my account to login as email is (.*) and password is (.*)$")
	public void iEnterMyInfoToRegisterANewAccountAs(String email, String password) {
		new LoginPage().enterInfoToLogin(email, password);
	}

	@Then("^I should see the error message for (.*)$")
	public void iShouldSeeTheErrorMessageForError(String error) {
		new LoginPage().checkErrorField(error);
	}

}
