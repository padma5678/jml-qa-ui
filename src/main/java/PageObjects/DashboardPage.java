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

    public WebElement getJmlMenu_btn() {
        return jmlMenu_btn;
    }

    public WebElement getUsers_link() {
        return users_link;
    }

}
