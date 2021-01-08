/**
 * Copyright (c) 2019 JML Software Solutions Ltd
 */
package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * @author Padmaja.Sagi
 */
public class RankListPage extends PageObject {

    public RankListPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "rankcategory")
    private WebElement rankCategory_dropDown;
    @FindBy(xpath = "//mat-cell[contains(@id,'_name_value')]")
    private WebElement rankName_value;
    @FindBy(xpath = "//mat-cell[contains(@id,'_rankCategory_value')]")
    private WebElement rankCategory_value;


    public WebElement getRankCategory_dropDown() {
        return rankCategory_dropDown;
    }

    public WebElement getRankName_value() {
        return rankName_value;
    }

    public WebElement getRankCategory_value() {
        return rankCategory_value;
    }

}
