package pages;

import cucumberTestNG.DriverManager;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.Map;

public class LoginPage extends PageHelper {
	public LoginPage() {
		PageFactory.initElements(DriverManager.getTLDriver(), this);
	}

	@FindBy(name = "email") private WebElement email;
	@FindBy(name = "password") private WebElement password;
	@FindBy(xpath = "//button[@data-track_event-action='Login']") private WebElement btnLogin;
	@FindBy(xpath = "//div[@class='form-errors clearfix'][contains(text(),'Bắt buộc phải nhập \"Email\"')]") private WebElement msgEmailBlank;
	@FindBy(xpath = "//div[@class='form-errors clearfix'][contains(text(),'Bắt buộc phải nhập \"Mật khẩu\"')]") private WebElement msgPasswordBlank;
	@FindBy(xpath = "//div[@class='form-errors clearfix'][contains(text(),'Sai mật khẩu')]") private WebElement msgIncorrectPassword;
	@FindBy(xpath = "//div[@class='form-errors clearfix'][contains(text(),'Email bạn nhập không hợp lệ')]") private WebElement msgInvalidEmail;

	public void login(Map<String,String> data){
		waitFor(email);
		inputField(email, data.get("Email"));
		inputField(password, data.get("Password"));
		clickOn(btnLogin);
	}

	public void shouldBeOnLoginPage(){
		waitUntilUrlBecome("/sign-in");
	}

	public void enterInfoToLogin(String email,String password){
		inputField(this.email, email);
		inputField(this.password, password);
		clickOn(btnLogin);
	}

	public void checkErrorField(String error){
		switch (error){
			case "emailblank": waitFor(msgEmailBlank); break;
			case "passwordblank": waitFor(msgPasswordBlank);break;
			case "incorrectpass": waitFor(msgIncorrectPassword);break;
			case "invalidem": waitFor(msgInvalidEmail);break;
		}
	}


}
