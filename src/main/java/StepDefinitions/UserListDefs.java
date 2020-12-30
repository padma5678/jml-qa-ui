package stepDefinitions;



import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import pageObjects.PostLoginPage;
import pageObjects.UserListPage;
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
	public HashMap userdtls= new HashMap();

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
		uniqueRef = uniqueRef+"-"+Wctrl.RandomStringGeneration();
		otherRef = otherRef+"-"+Wctrl.RandomStringGeneration();
		userName = userName+"-"+Wctrl.RandomStringGeneration();
		email = Wctrl.RandomEmailGeneration();
		userdtls.put("uniqueRef", uniqueRef);
		userdtls.put("firstName", firstName);
		userdtls.put("surname", surname);
		userdtls.put("email", email);
		userdtls.put("force", force);
		userdtls.put("rank", rank);
		userdtls.put("userName", userName);
		
		userListPage.getUserAdd_btn().click();
		Wctrl.MinWait();
		userListPage.getUniqueReferenceId_txt().sendKeys(uniqueRef);
		userListPage.getOtherRef_txt().sendKeys(otherRef);
		userListPage.getFirstName_txt().sendKeys(firstName);
		userListPage.getSurname_txt().sendKeys(surname);
		userListPage.getEmail_txt().sendKeys(email);
		userListPage.getGender_dropDown().click();
		userListPage.prepareWebElementWithDynamicXpath(userListPage.SelectDropddownvalue, gender).click();
		Wctrl.MinWait();
		userListPage.getEthnicSubgroup_dropDown().click();
		userListPage.prepareWebElementWithDynamicXpath(userListPage.SelectDropddownvalue, ethnicSubgrp).click();
		Wctrl.MinWait();
		userListPage.getGeography1_dropDown().click();
		userListPage.prepareWebElementWithDynamicXpath(userListPage.SelectDropddownvalue, force).click();
		Wctrl.MinWait();
		userListPage.getRank_dropDown().click();
		userListPage.prepareWebElementWithDynamicXpath(userListPage.SelectDropddownvalue, rank).click();
		Wctrl.MinWait();
		userListPage.getUsername_txt().sendKeys(userName);
		userListPage.getPassword_txt().sendKeys(password);
		userListPage.getSave_btn().click();
		Wctrl.MinWait();
		Assert.assertEquals("User List",userListPage.getUser_list_label().getText());
	}

	@Then("^I select user row of \"([^\"]*)\"$")
	public void iSelectUserRowOf(String firstname) throws Throwable {
		userListPage.getIdSort_btn().click();
		Wctrl.MinWait();
		userListPage.getIdSort_btn().click();
		Wctrl.MinWait();
		userListPage.prepareWebElementWithDynamicXpath(userListPage.userfirstname_fld, firstname).click();
		Wctrl.MinWait();
	}

	@Then("^I can see user information in the user details page$")
	public void iCanSeeUserInformationInTheUserDetailsPage() throws Throwable {
		Assert.assertEquals(userdtls.get("uniqueRef"),userListPage.getUniqueReferenceId_value().getText());
		Assert.assertEquals(userdtls.get("firstName"),userListPage.getFirstname_value().getText());
		Assert.assertEquals(userdtls.get("surname"),userListPage.getSurname_value().getText());
		Assert.assertEquals(userdtls.get("email"),userListPage.getEmail_value().getText());
		Assert.assertEquals(userdtls.get("force"),userListPage.getGeography1_value().getText());
		Assert.assertEquals(userdtls.get("rank"),userListPage.getRank_value().getText());
		Assert.assertEquals(userdtls.get("userName"),userListPage.getUserName_value().getText());
	}
	
	@When("^I switch on \"([^\"]*)\" column on user list page$")
	public void iSwitchOnColumnOnUserListPage(String surname) throws Throwable {
		userListPage.getColumns_btn().click();
		Wctrl.MinWait();
		String status = userListPage.prepareWebElementWithDynamicXpath(userListPage.columnSelection_fld_Status, surname).getAttribute("aria-checked");
		if (status.equalsIgnoreCase("true")) 
		{
			System.out.println("TestInfo : Check box already checked ");
			userListPage.prepareWebElementWithDynamicXpath(userListPage.columnSelection_fld, surname).click();
			Wctrl.MinWait();
		    userListPage.getColumnUpdate_btn().click();
		    Wctrl.MinWait();
		}else {
			System.out.println("TestInfo : Check box not checked ");
	    }    
	}

	@When("^I switch \"([^\"]*)\" column on user list page$")
	public void iSwitchColumnOnUserListPage(String surname) throws Throwable {
		userListPage.getColumns_btn().click();
		Wctrl.MinWait();
		userListPage.prepareWebElementWithDynamicXpath(userListPage.columnSelection_fld, surname).click();
		Wctrl.MinWait();
	    userListPage.getColumnUpdate_btn().click();
	    Wctrl.MinWait();
	}

	@Then("^I cannot see \"([^\"]*)\" column on user list page$")
	public void iCannotSeeColumnOnUserListPage(String surname) throws Throwable {
	try {
			
		if(userListPage.prepareWebElementWithDynamicXpath(userListPage.columnHeader_fld, surname).isDisplayed())
		{
			Assert.fail("Test Error : Column is Visible");
		}else {
			Assert.assertTrue("Test Info : Column not Visible", true);
		}
		} catch (Exception e) {
			System.out.println("Test Info : Column not Visible");
		}
	}

	@Then("^I can see \"([^\"]*)\" column on user list page$")
	public void iCanSeeColumnOnUserListPage(String surname) throws Throwable {
		if(!userListPage.prepareWebElementWithDynamicXpath(userListPage.columnHeader_fld, surname).isDisplayed())
		{
			Assert.fail("Test Error : Column is not visible");
		}else {
			Assert.assertTrue("Test Info : Column is visible", true);
		}
	   
	   
	}

}
