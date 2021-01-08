package stepDefinitions;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.TimeUnit;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import pageObjects.PostLoginPage;
import cucumber.api.java.en.Given;
import util.PropertiesUtil;
import util.WebController;

public class HomePageDefs {
	public static Hooks hooks;
	public static WebDriver driver = null;
	private WebController Wctrl;
	private PropertiesUtil putil = null;
	private PostLoginPage page;
	public Duration timeout;
	public HomePageDefs() throws FileNotFoundException, IOException
	{
		driver = Hooks.driver;
		hooks = PageFactory.initElements(driver, Hooks.class);
		Wctrl = PageFactory.initElements(driver, WebController.class);
		putil = new PropertiesUtil();	
		page = PageFactory.initElements(driver, PostLoginPage.class);
	}
		
	@Given("^User login to Chronicle application as \"([^\"]*)\"$")
	public void userLoginToChronicleApplicationAs(String arg1) throws Throwable {
		driver.get(putil.getPropertyValue("URL"));
		Wctrl.MinWait();
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		driver.manage().window().maximize();
		page.getLogin_Btn().click();
		page.getUsername_txt().sendKeys(putil.getPropertyValue("Consultant_User")); 
		page.getPassword_txt().sendKeys(putil.getPropertyValue("Consultant_Password"));  
		page.getSignIn_Btn().click();
		Wctrl.MinWait();
	}

	@Given("^User landed on Home page of Chronicle application$")
	public void userLandedOnHomePageOfChronicleApplication() throws Throwable {
	    String Actual_Title = driver.getTitle();
	    String Expected_Title ="Chronicle 4";
	    Assert.assertEquals(Actual_Title, Expected_Title);
	}

}