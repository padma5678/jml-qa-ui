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
import org.openqa.selenium.support.PageFactory;
import pageObjects.PostLoginPage;
import pageObjects.RankListPage;
import pageObjects.UserListPage;
import util.PropertiesUtil;
import util.WebController;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;

/**
 * @author Padmaja.Sagi
 */
public class RankListDefs {

    public static Hooks hooks;
    public static WebDriver driver = null;
    private WebController Wctrl;
    private PropertiesUtil putil = null;
    private PostLoginPage page;
    public Duration timeout;
    private RankListPage rankListPage;
    public HashMap rankdtls = new HashMap();

    public RankListDefs() throws FileNotFoundException, IOException {
        WebDriver driver = Hooks.driver;
        hooks = PageFactory.initElements(driver, Hooks.class);
        Wctrl = PageFactory.initElements(driver, WebController.class);
        putil = new PropertiesUtil();
        rankListPage = PageFactory.initElements(driver, RankListPage.class);
    }

    @When("^I add a rank with required data \"([^\"]*)\" and \"([^\"]*)\"$")
    public void iAddARankWithRequiredDataAnd(String rankName, String rankCategory) throws Throwable {
        rankName = rankName+"-"+Wctrl.RandomStringGeneration();
        rankdtls.put("rankName", rankName);
        rankdtls.put("rankCategory", rankCategory);
        rankListPage.getAdd_btn().click();
        Wctrl.MinWait();
        rankListPage.getName_txt().sendKeys(rankName);
        rankListPage.getRankCategory_dropDown().click();
        rankListPage.prepareWebElementWithDynamicXpath(rankListPage.SelectDropddownvalue, rankCategory).click();
        Wctrl.MinWait();
        rankListPage.getSave_btn().click();
        Wctrl.MinWait();
        Assert.assertEquals("Rank List",rankListPage.getTitle_label().getText());
    }

    @And("^I select newly added rank row of \"([^\"]*)\"$")
    public void iSelectNewlyAddedRankRowOf(String rankName) throws Throwable {
        rankListPage.getIdSort_btn().click();
        Wctrl.MinWait();
        rankListPage.getIdSort_btn().click();
        Wctrl.MinWait();
        rankName = rankdtls.get("rankName").toString();
        rankListPage.prepareWebElementWithDynamicXpath(rankListPage.name_fld,rankName).click();
        Wctrl.MinWait();
    }


    @Then("^I can see below rank information in the rank details page$")
    public void iCanSeeBelowRankInformationInTheRankDetailsPage() {
        Assert.assertEquals(rankdtls.get("rankName"),rankListPage.getName_value().getText());
        Assert.assertEquals(rankdtls.get("rankCategory"),rankListPage.getRankCategory_value().getText());
    }

    @When("^I switch on \"([^\"]*)\" column on rank list page$")
    public void iSwitchOnColumnOnRankListPage(String rankCategory) throws Throwable {
        rankListPage.getColumns_btn().click();
        Wctrl.MinWait();
        String status = rankListPage.prepareWebElementWithDynamicXpath(rankListPage.columnSelection_fld_Status, rankCategory).getAttribute("aria-checked");
        if (status.equalsIgnoreCase("false"))
        {
            System.out.println("TestInfo : Check box unchecked ");
            rankListPage.prepareWebElementWithDynamicXpath(rankListPage.columnSelection_fld, rankCategory).click();
            Wctrl.MinWait();
            rankListPage.getColumnUpdate_btn().click();
            Wctrl.MinWait();
        }else {
            System.out.println("TestInfo : Check box checked ");
        }
    }

    @Then("^I can see \"([^\"]*)\" column on rank list page$")
    public void iCanSeeColumnOnRankListPage(String rankCategory) throws Throwable {
        if(!rankListPage.prepareWebElementWithDynamicXpath(rankListPage.columnHeader_fld, rankCategory).isDisplayed())
        {
            Assert.fail("Test Error : Column is not visible");
        }else {
            Assert.assertTrue("Test Info : Column is visible", true);
        }
    }

    @When("^I switch off \"([^\"]*)\" column on rank list page$")
    public void iSwitchOffColumnOnRankListPage(String rankCategory) throws Throwable {
        rankListPage.getColumns_btn().click();
        Wctrl.MinWait();
        String status = rankListPage.prepareWebElementWithDynamicXpath(rankListPage.columnSelection_fld_Status, rankCategory).getAttribute("aria-checked");
        if (status.equalsIgnoreCase("true"))
        {
            System.out.println("TestInfo : Check box unchecked ");
            rankListPage.prepareWebElementWithDynamicXpath(rankListPage.columnSelection_fld, rankCategory).click();
            Wctrl.MinWait();
            rankListPage.getColumnUpdate_btn().click();
            Wctrl.MinWait();
        }else {
            System.out.println("TestInfo : Check box checked ");
        }
    }

    @Then("^I cannot see \"([^\"]*)\" column on rank list page$")
    public void iCannotSeeColumnOnRankListPage(String rankCategory) throws Throwable {
        try {

            if(rankListPage.prepareWebElementWithDynamicXpath(rankListPage.columnHeader_fld, rankCategory).isDisplayed())
            {
                Assert.fail("Test Error : Column is Visible");
            }else {
                Assert.assertTrue("Test Info : Column not Visible", true);
            }
        } catch (Exception e) {
            System.out.println("Test Info : Column not Visible");
        }
    }

    @When("^I change rank to \"([^\"]*)\" and \"([^\"]*)\" and save$")
    public void iChangeRankToAndAndSave(String rankName, String rankCategory) throws Throwable {
        rankListPage.getEdit_btn().click();
        rankListPage.getName_txt().clear();
        rankListPage.getName_txt().sendKeys(rankName);
        rankListPage.getRankCategory_dropDown().click();
        rankListPage.prepareWebElementWithDynamicXpath(rankListPage.SelectDropddownvalue, rankCategory).click();
        rankListPage.getSave_btn().click();
        Wctrl.MinWait();
        Assert.assertEquals("Rank",rankListPage.getTitle_label().getText());
    }

    @Then("^I should see rank changed to \"([^\"]*)\" and \"([^\"]*)\" in rank details page$")
    public void iShouldSeeRankChangedToAndInRankDetailsPage(String expectedRankName, String expectedRankCategory) throws Throwable {
        Assert.assertEquals(expectedRankName,rankListPage.getRankName_value().getText());
        Assert.assertEquals(expectedRankCategory,rankListPage.getRankCategory_value().getText());
    }

    @And("^I should see the default page size is \"([^\"]*)\"$")
    public void iShouldSeeTheDefaultPageSizeIs(String itemCount) throws Throwable {
        int rowcount  = rankListPage.getTable_Count().size();
        String rowCountStr = Integer.toString(rowcount);
        Assert.assertEquals(itemCount,rowCountStr);
    }

    @When("^I navigate to \"([^\"]*)\"$")
    public void iNavigateTo(String pageNavigator) throws Throwable {
        rankListPage.prepareWebElementWithDynamicXpath(rankListPage.page_navigator, pageNavigator).click();
        Wctrl.MinWait();
    }

    @Then("^I am on the next page starting with page count \"([^\"]*)\"$")
    public void iAmOnTheNextPageStartingWithPageCount(String expectedPageFirstRecord) throws Throwable {
        String actualPageRangeLabel =rankListPage.getPaginatorRange_label().getText();
        String[] actualPageFirstRecord = actualPageRangeLabel.split(" – ");
        Assert.assertEquals(expectedPageFirstRecord,actualPageFirstRecord[0]);
    }

    @Then("^I am on the previous page starting with page count \"([^\"]*)\"$")
    public void iAmOnThePreviousPageStartingWithPageCount(String arg0) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }
}
