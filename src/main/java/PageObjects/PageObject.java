package pageObjects;

import org.openqa.selenium.WebDriver;

public abstract class PageObject {
	protected final WebDriver driver;
	
	protected PageObject(WebDriver driver) {
		this.driver = driver;
	}
	
}
