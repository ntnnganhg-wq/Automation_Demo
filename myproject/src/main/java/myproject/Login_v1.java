

package myproject;
import java.time.Duration;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class Login_v1 extends BasePage implements InterfaceLogin {
	
	//Locator
	By txtEmail = By.name("email");
	By txtPass = By.name("password");
	By BtnAccept = By.cssSelector(".btn-lg.btn-primary");
	By BtnLogin = By.cssSelector("button[type='submit']");
	By MsgInvalidEmail = By.xpath("(//div[@class='is-invalid-messages']//span[2])[1]");
	By MsgInvalidPass = By.xpath("(//div[@class='is-invalid-messages']//span[2])[2]");
	
	//Constructor
	public Login_v1 ( WebDriver driver) {
		super(driver);		//super is using for inherited from parent BasePage
	}
	
	public void Login(String email, String pass) throws InterruptedException {
		Click(BtnAccept);
		EnterText(txtEmail, email);
		EnterText(txtPass, pass);
		Click(BtnLogin);
		
	}
	public void handleChangePasswordPopup() {
	    try {
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
	        alert.accept(); // click OK
	    } catch (TimeoutException e) {
	        // No alert appeared
	    }
	}

}