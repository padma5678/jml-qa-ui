package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

/**
 * @author Padmaja.Sagi
 */
public class UserListPage extends PageObject {

    
	public UserListPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "uniquereferenceid")
    private WebElement uniqueReferenceId_txt;
    @FindBy(id = "otherref")
    private WebElement otherRef_txt;
    @FindBy(id = "firstname")
    private WebElement firstName_txt;
    @FindBy(id = "middlename")
    private WebElement middleName_txt;
    @FindBy(id = "surname")
    private WebElement surname_txt;
    @FindBy(id = "email")
    private WebElement email_txt;
    @FindBy(id = "gender")
    private WebElement gender_dropDown;
    @FindBy(id = "dateofbirth")
    private WebElement dateOfBirth_txt;
    @FindBy(id = "retirementdate")
    private WebElement retirementDate_txt;
    @FindBy(id = "ethnicsubgroup")
    private WebElement ethnicSubgroup_dropDown;
    @FindBy(id = "geography1")
    private WebElement geography1_dropDown;
    @FindBy(id = "rank")
    private WebElement rank_dropDown;
    @FindBy(id = "geography4")
    private WebElement geography4_dropDown;
    @FindBy(id = "jml-quill-material-directive-id-2")
    private WebElement notes_txt;
    @FindBy(id = "mat-radio-button-yes-input")
    private WebElement trainingExpYes_radioBtn;
    @FindBy(id = "mat-radio-button-no-input")
    private WebElement trainingExpNo_radioBtn;
    @FindBy(id = "costcentre")
    private WebElement costCentre_txt;
    @FindBy(id = "username")
    private WebElement username_txt;
    @FindBy(id = "password")
    private WebElement password_txt;
    @FindBy(id = "jml-title")
    private WebElement user_list_label;
    @FindBy(id = "jml-add-button")
    private WebElement userAdd_btn;
    @FindBy(id = "jml-submit-button")
    private WebElement save_btn;
    
    
//GENERIC method for selecting dropdown
//    actual xpath for any drop down with mat-option
    public String SelectDropddownvalue =  "//mat-option[contains(., \""+"PARAMETER"+"\")]";

 	public WebElement prepareWebElementWithDynamicXpath(String xpathValue, String substitutionValue) {
 		xpathValue = xpathValue.replace("PARAMETER", substitutionValue);
 		System.out.println(xpathValue);		
 		return driver.findElement(By.xpath(xpathValue));
 	}


    public WebElement getUniqueReferenceId_txt() {
        return uniqueReferenceId_txt;
    }

    public WebElement getOtherRef_txt() {
        return otherRef_txt;
    }

    public WebElement getFirstName_txt() {
        return firstName_txt;
    }

    public WebElement getMiddleName_txt() {
        return middleName_txt;
    }

    public WebElement getSurname_txt() {
        return surname_txt;
    }

    public WebElement getEmail_txt() {
        return email_txt;
    }

    public WebElement getGender_dropDown() {
        return gender_dropDown;
    }

    public WebElement getDateOfBirth_txt() {
        return dateOfBirth_txt;
    }

    public WebElement getRetirementDate_txt() {
        return retirementDate_txt;
    }

    public WebElement getEthnicSubgroup_dropDown() {
        return ethnicSubgroup_dropDown;
    }

    public WebElement getGeography1_dropDown() {
        return geography1_dropDown;
    }

    public WebElement getRank_dropDown() {
        return rank_dropDown;
    }

    public WebElement getGeography4_dropDown() {
        return geography4_dropDown;
    }

    public WebElement getNotes_txt() {
        return notes_txt;
    }

    public WebElement getTrainingExpYes_radioBtn() {
        return trainingExpYes_radioBtn;
    }

    public WebElement getTrainingExpNo_radioBtn() {
        return trainingExpNo_radioBtn;
    }

    public WebElement getCostCentre_txt() {
        return costCentre_txt;
    }

    public WebElement getUsername_txt() {
        return username_txt;
    }

    public WebElement getPassword_txt() {
        return password_txt;
    }
    
    public WebElement getUser_list_label() {
        return user_list_label;
    }

    public WebElement getUserAdd_btn() {
        return userAdd_btn;
    }
    
    public WebElement getSave_btn() {
        return save_btn;
    }
    
}
