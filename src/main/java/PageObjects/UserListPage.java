package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

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
    @FindBy(xpath = "//mat-cell[contains(@id,'_uniqueReferenceId_value')]")
    private WebElement uniqueReferenceId_value;
    @FindBy(xpath = "//mat-cell[contains(@id,'_firstname_value')]")
    private WebElement firstname_value;
    @FindBy(xpath = "//mat-cell[contains(@id,'_surname_value')]")
    private WebElement surname_value;
    @FindBy(xpath = "//mat-cell[contains(@id,'_email_value')]")
    private WebElement email_value;
    @FindBy(xpath = "//mat-cell[contains(@id,'_geography1_value')]")
    private WebElement geography1_value;
    @FindBy(xpath = "//mat-cell[contains(@id,'_rank_value')]")
    private WebElement rank_value;
    @FindBy(xpath = "//mat-cell[contains(@id,'_username_value')]")
    private WebElement username_value;

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
    
    public WebElement getUniqueReferenceId_value() {
    	return uniqueReferenceId_value;
    }
    
    public WebElement getFirstname_value() {
    	return firstname_value;
    }
    
    public WebElement getSurname_value() {
    	return surname_value;
    }
    
    public WebElement getEmail_value() {
    	return email_value;
    }
    
    public WebElement getGeography1_value() {
    	return geography1_value;
    }
    
    public WebElement getRank_value() {
    	return rank_value;
    }

    public WebElement getUserName_value() {
        return username_value;
    }
    
}
