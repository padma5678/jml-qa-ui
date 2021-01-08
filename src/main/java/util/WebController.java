package util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import stepDefinitions.Hooks;
import cucumber.deps.com.thoughtworks.xstream.InitializationException;

public class WebController {
	public static Duration waittime = Duration.ofSeconds(60);
	public static WebDriver driver = null;
	public static Hooks hooks;
	public Duration timeout;
	private PropertiesUtil putil = null;
	protected static String fileSeperator = System.getProperty("file.separator");

	public WebController() throws FileNotFoundException, IOException {
		driver = Hooks.driver;
		hooks = PageFactory.initElements(driver, Hooks.class);
		putil = new PropertiesUtil();
	}

	/**
	 * Get a reference to an initialized driver.
	 * 
	 * @return reference to driver
	 */
	public static WebDriver getDeviceDriver() {

		if (driver != null) {
			return driver;
		}

		throw new InitializationException("Browser Driver Not Initialized");
	}

	/**
	 * Purpose: Click on Element
	 * 
	 * @param locatorValue
	 * @param locator
	 */
	public static void click(String locatorValue, String locator) {
		WebElement element = findElement(locatorValue, locator);
		try {
			element.click();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Purpose: To validate element is selected or not
	 * 
	 * @param locatorValue
	 * @param locator
	 * @return boolean value e.g.true/false
	 */
	public static boolean isSelected(String locatorValue, String locator) {
		WebElement element = findElement(locatorValue, locator);
		if (element.isSelected()) {
			return true;
		} else {
			return false;
		}

	}

	/**
	 * Purpose: to close the browser
	 */
	public static void closebrowser() {
		getDeviceDriver().quit();
	}

	public void MinWait() {
		try {
			Thread.sleep(2000);

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void MaxWait() {
		try {
			Thread.sleep(4000);

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Purpose: To get the text
	 * 
	 * @param locatorValue
	 * @param locator
	 * @return a string that Text
	 */
//	public static String getText(String locatorValue, String locator) {
//
//		WebElement element = findElement(locatorValue, locator);
//		if (element != null) {
//
//			return element.getText().replace("\n", "");
//		}
//		return "Did not find object";
//	}

	/**
	 * Purpose: To verify the element is active
	 * 
	 * @param locatorValue
	 * @param locator
	 * @return true/false
	 */
//	public static boolean isDisplayed(String locatorValue, String locator) {
//		WebElement element = findElement(locatorValue, locator);
//		if (element != null) {
//			return element.isDisplayed();
//		}
//		return false;
//	}

	/**
	 * Purpose: To maximize the window
	 */
	public static void maximizeWindow() {
		getDeviceDriver().manage().window().maximize();
	}

	/**
	 * Purpose: To launch the URL
	 * 
	 * @param URL
	 */
	public static void openUrl(String URL) {

		JavascriptExecutor jsExec = (JavascriptExecutor) getDeviceDriver();
		jsExec.executeScript("window.open()");
		Set Idcontainer = getDeviceDriver().getWindowHandles();
		Iterator ite = Idcontainer.iterator();

		while (ite.hasNext()) {

			String wntitle = ite.next().toString();

			getDeviceDriver().switchTo().window(wntitle);

			if (getDeviceDriver().getTitle().contains("Blank Page")) {
				break;

			}
		}
		getDeviceDriver().get(URL);
		getDeviceDriver().manage().window().maximize();

	}

	/**
	 * Purpose: To get the Sub String from given String
	 * 
	 * @param text
	 * @param StartPosition
	 * @param EndPosition
	 * @return a string that represents text (Sub String)
	 */
//	public static String subStringText(String text, int StartPosition, int EndPosition) {
//		return text.substring(StartPosition, EndPosition);
//
//	}

	/**
	 * Purpose: To identify the object
	 * 
	 * @param locatorValue
	 * @param locator
	 * @return WebElement
	 */
	public static WebElement findElement(String locatorValue, String locator) {

		try {
			if (locator.equalsIgnoreCase("partiallinktext")) {
				(new WebDriverWait(getDeviceDriver(), waittime))
						.until(ExpectedConditions.elementToBeClickable(By.partialLinkText(locatorValue)));
				return getDeviceDriver().findElement(By.linkText(locatorValue));
			} else if (locator.equalsIgnoreCase("linktext")) {
				(new WebDriverWait(getDeviceDriver(), waittime))
						.until(ExpectedConditions.elementToBeClickable(By.linkText(locatorValue)));
				return getDeviceDriver().findElement(By.linkText(locatorValue));
			} else if (locator.equalsIgnoreCase("ID")) {
				(new WebDriverWait(getDeviceDriver(), waittime))
						.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id(locatorValue)));
				return getDeviceDriver().findElement(By.id(locatorValue));

			} else if (locator.equalsIgnoreCase("class")) {
				(new WebDriverWait(getDeviceDriver(), waittime))
						.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className(locatorValue)));
				return getDeviceDriver().findElement(By.className(locatorValue));
			} else if (locator.equalsIgnoreCase("name")) {
				(new WebDriverWait(getDeviceDriver(), waittime))
						.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.name(locatorValue)));
				return getDeviceDriver().findElement(By.name(locatorValue));
			} else if (locator.equalsIgnoreCase("TagName")) {
				(new WebDriverWait(getDeviceDriver(), waittime))
						.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.tagName(locatorValue)));
				return getDeviceDriver().findElement(By.tagName(locatorValue));
			} else if (locator.equalsIgnoreCase("xpath")) {

				(new WebDriverWait(getDeviceDriver(), waittime))
						.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(locatorValue)));
				return getDeviceDriver().findElement(By.xpath(locatorValue));

			}
		} catch (Exception e) {
			try {
				Thread.sleep(20);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}

		return null;

	}

	/**
	 * Close a previously initialized driver.
	 * 
	 * @return true if successful
	 */
	public static boolean closeDeviceDriver() {

		if (getDeviceDriver() != null) {
			getDeviceDriver().quit();
			setDriver(null);
		}
		return getDeviceDriver() == null;
	}

	public static void setDriver(WebDriver driver) {
		WebController.driver = driver;
	}

	public boolean waitForElement(WebElement elem, int seconds) {

		boolean displayedElement = false;

		WebDriverWait wait = new WebDriverWait(driver, timeout.ofSeconds(seconds));

		try {
			wait.until(ExpectedConditions.visibilityOf(elem));
			displayedElement = true;
			System.out.println("Test Execution Info - Pass >> Wait For Element - " + elem + "-Webelement is Avilabel");
			MinWait();
		} catch (Exception e) {
			displayedElement = false;
			System.out.println("Test Execution Info - Fail >>  Wait For Element - " + elem
					+ "-Webelement is Not Avilabel - Test Step Failed");
			Assert.fail();
		}
		return displayedElement;
	}

	public String RandomStringGeneration() {
		String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		StringBuilder sb = new StringBuilder();
		Random random = new Random();
		int length = 7;
		for (int i = 0; i < length; i++) {
			int index = random.nextInt(alphabet.length());
			char randomChar = alphabet.charAt(index);
			sb.append(randomChar);
		}
		String randomString = sb.toString();
		return randomString;
	}

	public String RandomEmailGeneration() {
		String Number = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		StringBuilder sb = new StringBuilder();
		Random random = new Random();
		int length = 4;
		for (int i = 0; i < length; i++) {
			int index = random.nextInt(Number.length());
			char randomChar = Number.charAt(index);
			sb.append(randomChar);
		}
		String randomString = "autogen"+sb.toString() + "@gmail.com";
		return randomString;
	}

}
