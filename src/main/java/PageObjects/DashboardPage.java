package pageObjects;

/**
 * Copyright (c) 2019 JML Software Solutions Ltd
 */

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * @author Padmaja.Sagi
 */
public class DashboardPage extends PageObject {

    public DashboardPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "jml-menu_button")
    private WebElement jmlMenu_btn;
    @FindBy(id = "jml-menu-item-users")
    private WebElement users_link;
    @FindBy(id = "jml-menu-item-genders")
    private WebElement genders_link;
    @FindBy(id = "jml-menu-item-ranks")
    private WebElement ranks_link;

    public WebElement getJmlMenu_btn() {
        return jmlMenu_btn;
    }

    public WebElement getUsers_link() {
        return users_link;
    }

    public WebElement getGenders_link() {
        return genders_link;
    }

    public WebElement getRanks_link() {
        return ranks_link;
    }

}
