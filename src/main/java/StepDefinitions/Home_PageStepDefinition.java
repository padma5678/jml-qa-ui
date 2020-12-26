package StepDefinitions;



import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import PageObjects.PostLoginPage;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import util.PropertiesUtil;
import util.WebController;

public class Home_PageStepDefinition{
	public static Hooks hooks;
	public static WebDriver driver = null;
	private WebController Wctrl;
	private PropertiesUtil putil = null;
	private PostLoginPage page;
	public Duration timeout;
	public Home_PageStepDefinition() throws FileNotFoundException, IOException
	{
		driver = Hooks.driver;
		hooks = PageFactory.initElements(driver, Hooks.class);
		Wctrl = PageFactory.initElements(driver, WebController.class);
		putil = new PropertiesUtil();	
		page = PageFactory.initElements(driver, PostLoginPage.class);
	}
		
	@Given("^Consultant login to Chronicle application as \"([^\"]*)\"$")
	public void consultantLoginToChronicleApplicationAs(String arg1) throws Throwable {
		driver.get(putil.getPropertyValue("URL"));
		Wctrl.MinWait();
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		page.getLogin_Btn().click();
		page.getUsername_txt().sendKeys(putil.getPropertyValue("Consultant_User")); 
		page.getPassword_txt().sendKeys(putil.getPropertyValue("Consultant_Password"));  
		page.getSignIn_Btn().click();
		Wctrl.MinWait();
	}

	@Given("^Consultant landed on Home page of Chronicle application$")
	public void consultantLandedOnHomePageOfChronicleApplication() throws Throwable {
	    String Actual_Title = driver.getTitle();
	    String Expected_Title ="Chronicle 4";
	    Assert.assertEquals(Actual_Title, Expected_Title);
	}

	@Given("^I click on the ethnicity subgroup link$")
	public void iClickOnTheEthnicitySubgroupLink() throws Throwable {
	   
	}

	

}