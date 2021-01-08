package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

import java.util.List;

public abstract class PageObject {
	protected final WebDriver driver;
	
	protected PageObject(WebDriver driver) {
		this.driver = driver;
	}

	@FindBy(id = "jml-add-button")
	private WebElement add_btn;
	@FindBy(id = "jml-submit-button")
	private WebElement save_btn;
	@FindBy(xpath = "//button[contains(text(),'ID')]")
	private WebElement idSort_btn;
	@FindBy(id = "jml-column-button")
	private WebElement columns_btn;
	@FindBy(id = "jml-button-update-columns")
	private WebElement columnUpdate_btn;
	@FindBy(id = "jml-title")
	private WebElement title_label;
	@FindBy(id = "jml-hide-button")
	private WebElement hide_btn;
	@FindBy(id = "jml-edit-button")
	private WebElement edit_btn;
	@FindBy(id = "name")
	private WebElement name_txt;
	@FindBy(xpath = "//mat-cell[contains(@id,'_name_value')]")
	private WebElement name_value;
   	@FindBys(@FindBy(xpath = "//mat-table//mat-row"))
	private List<WebElement> table_count;
	@FindBy(xpath = "//div[@class='mat-paginator-range-label']")
	private WebElement paginatorRange_label;

	public String SelectDropddownvalue =  "//mat-option[contains(., \""+"PARAMETER"+"\")]";
	public String userfirstname_fld = "//div[text()="+"'PARAMETER'"+"]";
	public String name_fld = "//div[text()="+"'PARAMETER'"+"]";
	public String columnHeader_fld = "//button[contains(text(),'PARAMETER')]";
	public String columnSelection_fld = "//span[contains(text(),'PARAMETER')]/parent::label/div";
	public String columnSelection_fld_Status = "//span[contains(text(),'PARAMETER')]/parent::label/div/input";
	public String page_navigator = "//button[@aria-label="+"'PARAMETER'"+"]";





	public WebElement prepareWebElementWithDynamicXpath(String xpathValue, String substitutionValue) {
		xpathValue = xpathValue.replace("PARAMETER", substitutionValue);
		return driver.findElement(By.xpath(xpathValue));
	}

	public WebElement getAdd_btn() {
		return add_btn;
	}

	public WebElement getSave_btn() {
		return save_btn;
	}

	public WebElement getIdSort_btn() {
		return idSort_btn;
	}

	public WebElement getColumns_btn() {
		return columns_btn;
	}

	public WebElement getColumnUpdate_btn() {
		return columnUpdate_btn;
	}

	public WebElement getTitle_label() {
		return title_label;
	}

	public WebElement getEdit_btn() {
		return edit_btn;
	}

	public WebElement getHide_btn() {
		return hide_btn;
	}

	public WebElement getName_txt() {
		return name_txt;
	}

	public WebElement getName_value() {
		return name_value;
	}

	public List<WebElement> getTable_Count() {
		return table_count;
	}

	public WebElement getPaginatorRange_label() {
		return paginatorRange_label;
	}

}
