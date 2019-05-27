package cucumberTestNG;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class Hook {

	@Before
	public void init(Scenario scenario){
		System.out.println(StartScenario(scenario));
	}
	
	@After
	public void tearDown(Scenario result){
		if (result.isFailed()) {
			byte[] screenshot = ((TakesScreenshot)DriverManager.getTLDriver()).getScreenshotAs(OutputType.BYTES);
			result.embed(screenshot, "image/png");
		}
		System.out.println(EndScenario(result));
	}

	private String StartScenario(Scenario scenario) {
		return "\n ======== Execute " + scenario.getName() + " ========\n";
	}

	private String EndScenario(Scenario scenario) {
		return "\n******* Execute scenario " + scenario.getName() + " - Done as :" + scenario.getStatus().toUpperCase() + " ********\n";
	}

}
