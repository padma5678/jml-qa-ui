package stepDefinitions;

import cucumber.api.java.en.Given;
import util.PropertiesUtil;
import util.WebController;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import pageObjects.DashboardPage;
import pageObjects.PostLoginPage;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;

/**
 * @author Padmaja.Sagi
 */
public class DashboardDefs {

	public static Hooks hooks;
	public static WebDriver driver = null;
	private WebController Wctrl;
	private PropertiesUtil putil = null;
	private PostLoginPage page;
	public Duration timeout;
	private DashboardPage dashboardPage;

	public DashboardDefs() throws FileNotFoundException, IOException {
		driver = Hooks.driver;
		hooks = PageFactory.initElements(driver, Hooks.class);
		Wctrl = PageFactory.initElements(driver, WebController.class);
		putil = new PropertiesUtil();
		dashboardPage = PageFactory.initElements(driver, DashboardPage.class);
	}

	@Given("^I click on users link$")
	public void iClickOnUsersLink() {
		dashboardPage.getJmlMenu_btn().click();
		Wctrl.MinWait();
		dashboardPage.getUsers_link().click();
	}

	@Given("^I click on genders link$")
	public void iClickOnGendersLink() {
		dashboardPage.getJmlMenu_btn().click();
		Wctrl.MinWait();
		dashboardPage.getGenders_link().click();
	}

	@Given("^I click on ranks link$")
	public void iClickOnRanksLink() {
		dashboardPage.getJmlMenu_btn().click();
		Wctrl.MinWait();
		dashboardPage.getRanks_link().click();
	}
}
