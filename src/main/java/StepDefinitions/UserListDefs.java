package StepDefinitions;



import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import PageObjects.PostLoginPage;
import PageObjects.UserListPage;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import util.PropertiesUtil;
import util.WebController;

public class UserListDefs {

	public static Hooks hooks;
	public static WebDriver driver = null;
	private WebController Wctrl;
	private PropertiesUtil putil = null;
	private PostLoginPage page;
	public Duration timeout;
	private UserListPage userListPage;

	public UserListDefs() throws FileNotFoundException, IOException {
		driver = Hooks.driver;
		hooks = PageFactory.initElements(driver, Hooks.class);
		Wctrl = PageFactory.initElements(driver, WebController.class);
		putil = new PropertiesUtil();
		userListPage = PageFactory.initElements(driver, UserListPage.class);
	}

	@Given("^I am on the \"([^\"]*)\" page$")
	public void iAmOnThePage(String expectedUserListTitle) throws Throwable {
		String actualUserListTitle = userListPage.getUser_list_label().getText();
		Assert.assertEquals(expectedUserListTitle, actualUserListTitle);
	}

	@When("^I add a user with required fields \"([^\"]*)\",\"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\"$")
	public void iAddAUserWithRequiredFields(String uniqueRef, String otherRef, String firstName, String surname,
			String email, String gender, String ethnicSubgrp, String force, String rank, String userName,
			String password) throws Throwable {
		userListPage.getUserAdd_btn().click();
		Wctrl.MinWait();
		userListPage.getUniqueReferenceId_txt().sendKeys(uniqueRef);
		userListPage.getOtherRef_txt().sendKeys(otherRef);
		userListPage.getFirstName_txt().sendKeys(firstName);
		userListPage.getSurname_txt().sendKeys(surname);
		userListPage.getEmail_txt().sendKeys(email);
		userListPage.getUsername_txt().sendKeys(userName);
		userListPage.getPassword_txt().sendKeys(password);
		userListPage.getGender_dropDown().click();
		userListPage.prepareWebElementWithDynamicXpath(userListPage.SelectDropddownvalue, gender).click();
		Wctrl.MinWait();
		userListPage.getGeography1_dropDown().click();
		userListPage.prepareWebElementWithDynamicXpath(userListPage.SelectDropddownvalue, force).click();
		Wctrl.MinWait();
		userListPage.getEthnicSubgroup_dropDown().click();
		userListPage.prepareWebElementWithDynamicXpath(userListPage.SelectDropddownvalue, ethnicSubgrp).click();
		Wctrl.MinWait();
		userListPage.getRank_dropDown().click();
		userListPage.prepareWebElementWithDynamicXpath(userListPage.SelectDropddownvalue, rank).click();
		Wctrl.MinWait();
		userListPage.getSave_btn().click();
		System.out.println("Debug");

	}

	@Then("^I select user row of \"([^\"]*)\"$")
	public void iSelectUserRowOf(String arg1) throws Throwable {

	}

	@Then("^I can see user information in the user details page$")
	public void iCanSeeUserInformationInTheUserDetailsPage() throws Throwable {

	}

}
