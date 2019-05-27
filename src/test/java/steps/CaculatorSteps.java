package steps;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pages.mobile.CalculatorScreen;

public class CaculatorSteps {
    @Given("^I click OK button to dismiss$")
    public void iClickOKbutton() {
        new CalculatorScreen().clickOnOKButton();
    }

    @When("^(.*) plus (.*)$")
    public void iOpenTheMainPage(String number1, String number2) {
        new CalculatorScreen().add(number1, number2);
    }

    @Then("^The result should be (.*)$")
    public void iShouldSeeTheErrorMessageForError(String result) {
        new CalculatorScreen().result(result);
    }

}
