package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class PostLoginPage extends PageObject {
	
	public PostLoginPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Login')]")
	private WebElement Login_Btn;
	@FindBy(how = How.XPATH, using = "//input[@id='username']")
	private WebElement Username_txt;
	@FindBy(how = How.XPATH, using = "//input[@id='password']")
	private WebElement Password_txt;
	@FindBy(how = How.XPATH, using = "//input[@name='submit']")
	private WebElement SignIn_Btn;

	public WebElement getUsername_txt() {
		return Username_txt;
	}

	public WebElement getPassword_txt() {
		return Password_txt ;
	}

	public WebElement getLogin_Btn() {
		return Login_Btn;
	}

	public WebElement getSignIn_Btn() {
		return SignIn_Btn ;
	}

}
