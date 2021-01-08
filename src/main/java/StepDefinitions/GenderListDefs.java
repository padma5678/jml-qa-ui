/**
 * Copyright (c) 2019 JML Software Solutions Ltd
 */
package stepDefinitions;

import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import pageObjects.DashboardPage;
import pageObjects.GenderListPage;
import util.PropertiesUtil;
import util.WebController;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

/**
 * @author Padmaja.Sagi
 */
public class GenderListDefs {

    public static Hooks hooks;
    public static WebDriver driver = null;
    private WebController Wctrl;
    private PropertiesUtil putil = null;
    private GenderListPage genderListPage;
    private DashboardPage dashboardPage;
    public HashMap genderdtls = new HashMap();

    public GenderListDefs() throws FileNotFoundException, IOException {
        driver = Hooks.driver;
        hooks = PageFactory.initElements(driver, Hooks.class);
        Wctrl = PageFactory.initElements(driver, WebController.class);
        putil = new PropertiesUtil();
        dashboardPage = PageFactory.initElements(driver, DashboardPage.class);
        genderListPage = PageFactory.initElements(driver, GenderListPage.class);
    }

    @When("^I add a gender with required data \"([^\"]*)\"$")
    public void iAddAGenderWithRequiredData(String genderName) {
        genderName = genderName+"-"+Wctrl.RandomStringGeneration();
        genderdtls.put("genderName", genderName);
        genderListPage.getAdd_btn().click();
        Wctrl.MinWait();
        genderListPage.getName_txt().sendKeys(genderName);
        genderListPage.getSave_btn().click();
        Wctrl.MinWait();
        Assert.assertEquals("Gender List",genderListPage.getTitle_label().getText());
    }

    @And("^I select new gender row of \"([^\"]*)\"$")
    public void iSelectNewGenderRowOf(String genderName) throws Throwable {
        genderListPage.getIdSort_btn().click();
        Wctrl.MinWait();
        genderListPage.getIdSort_btn().click();
        Wctrl.MinWait();
//        since key is object it needs to be converted to a string
        genderName = genderdtls.get("genderName").toString();
        genderListPage.prepareWebElementWithDynamicXpath(genderListPage.name_fld,genderName).click();
        Wctrl.MinWait();
    }

    @And("^I select gender row of \"([^\"]*)\"$")
    public void iSelectGenderRowOf(String genderName) {
        genderListPage.prepareWebElementWithDynamicXpath(genderListPage.name_fld, genderName).click();
        Wctrl.MinWait();
    }

    @Then("^I can see gender information in the gender details page$")
    public void iCanSeeGenderInformationInTheGenderDetailsPage() {
        Assert.assertEquals(genderdtls.get("genderName"),genderListPage.getName_value().getText());
    }

    @When("^I switch on \"([^\"]*)\" column on gender list page$")
    public void iSwitchOnColumnOnGenderListPage(String genderName) throws Throwable {
        genderListPage.getColumns_btn().click();
        Wctrl.MinWait();
        String status = genderListPage.prepareWebElementWithDynamicXpath(genderListPage.columnSelection_fld_Status, genderName).getAttribute("aria-checked");
        if (status.equalsIgnoreCase("true"))
        {
            System.out.println("TestInfo : Check box already checked ");
            genderListPage.prepareWebElementWithDynamicXpath(genderListPage.columnSelection_fld, genderName).click();
            Wctrl.MinWait();
            genderListPage.getColumnUpdate_btn().click();
            Wctrl.MinWait();
        }else {
            System.out.println("TestInfo : Check box not checked ");
        }
    }

    @Then("^I cannot see \"([^\"]*)\" column on gender list page$")
    public void iCannotSeeColumnOnGenderListPage(String genderName) throws Throwable {
        try {

            if(genderListPage.prepareWebElementWithDynamicXpath(genderListPage.columnHeader_fld, genderName).isDisplayed())
            {
                Assert.fail("Test Error : Column is Visible");
            }else {
                Assert.assertTrue("Test Info : Column not Visible", true);
            }
        } catch (Exception e) {
            System.out.println("Test Info : Column not Visible");
        }
    }

    @When("^I switch \"([^\"]*)\" column on gender list page$")
    public void iSwitchColumnOnGenderListPage(String genderName) throws Throwable {
        genderListPage.getColumns_btn().click();
        Wctrl.MinWait();
        genderListPage.prepareWebElementWithDynamicXpath(genderListPage.columnSelection_fld, genderName).click();
        Wctrl.MinWait();
        genderListPage.getColumnUpdate_btn().click();
        Wctrl.MinWait();
    }

    @Then("^I can see \"([^\"]*)\" column on gender list page$")
    public void iCanSeeColumnOnGenderListPage(String genderName) throws Throwable {
        if(!genderListPage.prepareWebElementWithDynamicXpath(genderListPage.columnHeader_fld, genderName).isDisplayed())
        {
            Assert.fail("Test Error : Column is not visible");
        }else {
            Assert.assertTrue("Test Info : Column is visible", true);
        }

    }

    @When("^I change gender name to \"([^\"]*)\" and save$")
    public void iChangeGenderNameToAndSave(String genderName) throws Throwable {
        genderListPage.getEdit_btn().click();
        genderListPage.getName_txt().clear();
        genderListPage.getName_txt().sendKeys(genderName);
        genderListPage.getSave_btn().click();
        Wctrl.MinWait();
        Assert.assertEquals("Gender",genderListPage.getTitle_label().getText());
    }

    @Then("^I should see gender name changed to \"([^\"]*)\" in gender details page$")
    public void iShouldSeeGenderNameChangedToInGenderDetailsPage(String genderName) throws Throwable {
        Assert.assertEquals(genderName,genderListPage.getName_value().getText());
    }

    @When("^I click on archive gender$")
    public void iClickOnArchiveGender() {
       genderListPage.getHide_btn().click();
       Wctrl.MinWait();
    }

    @Then("^I should not see gender \"([^\"]*)\" in gender list page$")
    public void iShouldNotSeeGenderInGenderListPage(String genderName) throws Throwable {
        dashboardPage.getJmlMenu_btn().click();
        dashboardPage.getGenders_link().click();
        Assert.assertEquals("Gender List",genderListPage.getTitle_label().getText());
    try {
        WebElement genderele  = genderListPage.prepareWebElementWithDynamicXpath(genderListPage.name_fld, genderName);
        if(genderele.isDisplayed())
        {
            Assert.fail("TestError - Gender Name shouldn't be visible");
        }else
        {
            Assert.assertTrue("TestInfo - Gender Name is Archived",true);
        }

    }catch  (Exception e)
    {
        Assert.assertTrue("TestInfo - Gender Name is Archived",true);
    }
    }

    @When("^I click on unarchive gender$")
    public void iClickOnUnarchiveGender() {
        String url = driver.getCurrentUrl();
        url = url.replace("/list","/view/3");
        driver.navigate().to(url);
        Wctrl.MinWait();
        genderListPage.getHide_btn().click();
    }

    @Then("^I should see gender \"([^\"]*)\" in gender list page$")
    public void iShouldSeeGenderInGenderListPage(String genderName) throws Throwable {
        Wctrl.MinWait();
        dashboardPage.getJmlMenu_btn().click();
        dashboardPage.getGenders_link().click();
        Assert.assertEquals("Gender List",genderListPage.getTitle_label().getText());
        try {
            WebElement genderele  = genderListPage.prepareWebElementWithDynamicXpath(genderListPage.name_fld, genderName);
            if(genderele.isDisplayed())
            {
                Assert.assertTrue("TestInfo - Gender Name is Archived",true);

            }else
            {
                Assert.fail("TestError - Gender Name shouldn't be visible");
            }

        }catch  (Exception e)
        {
            Assert.fail("TestError - Gender Name shouldn't be visible");
        }

    }
}
