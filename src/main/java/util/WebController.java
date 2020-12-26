package util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import StepDefinitions.Hooks;
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
	 * Purpose: To accept the alert popped up in Application
	 */
	public static void acceptAlert() {
		getDeviceDriver().switchTo().alert().accept();
	}

	/**
	 * Purpose: To decline the alert popped up in Application
	 */
	public static void dismissAlert() {
		getDeviceDriver().switchTo().alert().dismiss();

	}

	/**
	 * Purpose: to clear the input box
	 * 
	 * @param locatorValue
	 * @param locator
	 * 
	 */
	public static void clearText(String locatorValue, String locator) {
		WebElement element = findElement(locatorValue, locator);
		try {
			element.clear();

		} catch (Exception e) {
			e.printStackTrace();
		}
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
	 * Purpose: to Select the Drop Down value by Index
	 * 
	 * @param locatorValue
	 * @param locator
	 * @param Index
	 */
	public static void selectByIndex(String locatorValue, String locator, int Index) {

		WebElement element = findElement(locatorValue, locator);
		if (element != null) {
			Select select = new Select(element);
			select.selectByIndex(Index);
			select.getOptions().size();
		}
	}

	/**
	 * Purpose: to get the Count on Drop Down
	 * 
	 * @param locatorValue
	 * @param locator
	 * @param Index
	 * @return
	 */
	public static int getSelectItemCount(String locatorValue, String locator, int Index) {

		WebElement element = findElement(locatorValue, locator);
		if (element != null) {
			Select select = new Select(element);
			return select.getOptions().size();
		}
		return 0;
	}

	/**
	 * Purpose: to close the browser
	 */
	public static void closebrowser() {
		getDeviceDriver().quit();
	}

	/**
	 * Purpose: return the URL of current opened window
	 * 
	 * @return a string that represents current URL
	 */
	public static String returnCurrentURL() {

		return getDeviceDriver().getCurrentUrl();
	}

	/**
	 * Purpose: Select the value from list box
	 * 
	 * @param Value
	 * @param locatorValue
	 * @param locator
	 */
	public static void select(String Value, String locatorValue, String locator) {
		WebElement element = findElement(locatorValue, locator);
		if (element != null) {
			Select select = new Select(element);
			select.selectByVisibleText(Value);
		}
	}

	/**
	 * Purpose: To handle the multiple windows and java script
	 * 
	 * @param script
	 */
	public static void executeJavaScript(String script) {
		((JavascriptExecutor) getDeviceDriver()).executeScript(script);
	}

	/**
	 * Purpose: To handle the synchronization issues
	 * 
	 * @param value
	 */
	public void MinWait() {
		try {
			Thread.sleep(3000);

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void MaxWait() {
		try {
			Thread.sleep(6000);

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Purpose: To get the background color of the dashboard or page
	 * 
	 * @param locatorValue
	 * @param locator
	 * @return a string that represents back ground color
	 */
	public static String getbackgroundcolor(String locatorValue, String locator) {
		String text;

		WebElement element = findElement(locatorValue, locator);

		if (element == null) {
			return null;
		}
		try {
			text = element.getCssValue("background-color").toString();

			System.out.println("try-" + text);
		} catch (Exception e) {
			text = "";
		}
		System.out.println(text);

		String[] numbers = text.replace("rgba(", "").replace(")", "").split(",");
		int number1 = Integer.parseInt(numbers[0]);
		numbers[1] = numbers[1].trim();
		int number2 = Integer.parseInt(numbers[1]);
		numbers[2] = numbers[2].trim();
		int number3 = Integer.parseInt(numbers[2]);
		String hex = String.format("#%02x%02x%02x", number1, number2, number3);
		System.out.println(hex);
		return hex;

	}

	/**
	 * Purpose: To get the background image
	 * 
	 * @param locatorValue
	 * @param locator
	 * @return a string that represents back ground images
	 */
	public static String getbackgroundimage(String locatorValue, String locator) {
		String bimage;

		WebElement element = findElement(locatorValue, locator);

		if (element == null) {
			return null;
		}
		try {
			bimage = element.getCssValue("background-image");

			System.out.println("try-" + bimage);
		} catch (Exception e) {
			bimage = "";
		}
		System.out.println(bimage);

		return bimage;

	}

	/**
	 * Purpose: To get the text
	 * 
	 * @param locatorValue
	 * @param locator
	 * @return a string that Text
	 */
	public static String getText(String locatorValue, String locator) {

		WebElement element = findElement(locatorValue, locator);
		if (element != null) {

			return element.getText().replace("\n", "");
		}
		return "Did not find object";
	}

	/**
	 * @param locatorValue
	 * @param locator
	 * @return return attribute
	 */
	public static String fsGetValue(String locatorValue, String locator) {

		WebElement element = findElement(locatorValue, locator);
		if (element != null) {

			return element.getAttribute("value");
		}
		return "Did not find object";
	}

	/**
	 * Purpose: To enter the value in the textbox
	 * 
	 * @param Value
	 * @param locatorValue
	 * @param locator
	 */
	public static void input(String Value, String locatorValue, String locator) {
		WebElement element = findElement(locatorValue, locator);
		element.sendKeys(Value);
	}

	/**
	 * Purpose: To verify the element is active
	 * 
	 * @param locatorValue
	 * @param locator
	 * @return true/false
	 */
	public static boolean isDisplayed(String locatorValue, String locator) {
		WebElement element = findElement(locatorValue, locator);
		if (element != null) {
			return element.isDisplayed();
		}
		return false;
	}

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
	 * Purpose: To switch to other window
	 * 
	 * @param Value
	 */
	public static void switchToWindow(String Value) {
		Set Idcontainer = getDeviceDriver().getWindowHandles();
		Iterator ite = Idcontainer.iterator();

		while (ite.hasNext()) {

			String wntitle = ite.next().toString();
			getDeviceDriver().switchTo().window(wntitle);
			if (getDeviceDriver().getTitle().contains(Value)) {
				break;
			}
		}
	}

	/**
	 * Purpose: To switch to other window
	 * 
	 * @param locatorValue
	 * @param locator
	 */
	public static void switchToFrame(String locatorValue, String locator) {
		if (locator.equalsIgnoreCase("linktext")) {
			getDeviceDriver().switchTo().frame(getDeviceDriver().findElement(By.linkText(locatorValue)));
		} else if (locator.equalsIgnoreCase("ID")) {
			getDeviceDriver().switchTo().frame(getDeviceDriver().findElement(By.id(locatorValue)));

		} else if (locator.equalsIgnoreCase("class")) {
			getDeviceDriver().switchTo().frame(getDeviceDriver().findElement(By.className(locatorValue)));
		} else if (locator.equalsIgnoreCase("name")) {
			getDeviceDriver().switchTo().frame(getDeviceDriver().findElement(By.name(locatorValue)));

		} else if (locator.equalsIgnoreCase("TagName")) {
			getDeviceDriver().switchTo().frame(getDeviceDriver().findElement(By.tagName(locatorValue)));

		} else if (locator.equalsIgnoreCase("xpath")) {

			getDeviceDriver().switchTo().frame(getDeviceDriver().findElement(By.xpath(locatorValue)));

		}
	}

	/**
	 * Purpose: To get all the windownames
	 * 
	 * @return all active windows
	 */
	public static Set<String> getWindows() {
		return getDeviceDriver().getWindowHandles();
	}

	/**
	 * Purpose: To get the tilte of the window
	 * 
	 * @return a string that represents Title of Window
	 */
	public static String getTitleOfWindow() {
		return getDeviceDriver().getTitle();

	}

	/**
	 * Purpose: To get the Sub String from given String
	 * 
	 * @param text
	 * @param StartPosition
	 * @param EndPosition
	 * @return a string that represents text (Sub String)
	 */
	public static String subStringText(String text, int StartPosition, int EndPosition) {
		return text.substring(StartPosition, EndPosition);

	}

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
	 * Purpose: This method will switch to default content
	 */
	public static void swtichToDefaultContent() {
		getDeviceDriver().switchTo().defaultContent();

	}

	/**
	 * Purpose: This method will validate Inner Text
	 * 
	 * @param value
	 * @param locatorValue
	 * @param locator
	 * @return a string that represents Inner Text
	 */
	public static String validateInnerText(String value, String locatorValue, String locator) {
		WebElement element = findElement(locatorValue, locator);
		if (element != null) {
			if (element.getAttribute("value").trim().equalsIgnoreCase(value))

				return element.getAttribute("value").trim();
		}

		return "";

	}

	/**
	 * Purpose: This method will wait for Alert for given condition
	 */
	public static void waitForAlert() {
		new WebDriverWait(getDeviceDriver(), waittime).until(ExpectedConditions.alertIsPresent());
	}

	/**
	 * Purpose: This method will remove the browser cookies
	 */
	public static void removeCookies() {
		getDeviceDriver().manage().deleteAllCookies();
	}

	public static boolean isElementExist(String locatorValue, String locator) {

		WebElement element = findElement(locatorValue, locator);
		if (element != null) {
			return true;
		}
		return false;

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

	public static String GetCurrentDate(String format) {
		String currentDate = "";

		try {
			DateFormat dateformat = new SimpleDateFormat(format);
			Date date = new Date();
			currentDate = dateformat.format(date).toString();

		} catch (Exception e) {
			e.printStackTrace();
			String errormsg = e.getMessage();
			System.out.println("Error message is " + errormsg);
		}

		return currentDate;
	}

	public String getMonthName(String date) throws ParseException {
		Date d = new SimpleDateFormat("mm/dd/yyyy", Locale.ENGLISH).parse(date);
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		String monthName = new SimpleDateFormat("MMMM").format(cal.getTime());
		return monthName;
	}

	public String getYear(String date) throws ParseException {
		Date d = new SimpleDateFormat("mm/dd/yyyy", Locale.ENGLISH).parse(date);
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		String year = new SimpleDateFormat("yyyy").format(cal.getTime());
		return year;
	}

	public String GetCurrentDate() {
		String currentDate = "";

		try {
			DateFormat dateformat = new SimpleDateFormat("mm/dd/yyyy");
			Date date = new Date();
			currentDate = dateformat.format(date).toString();

		} catch (Exception e) {
			e.printStackTrace();
			String errormsg = e.getMessage();
			System.out.println("Error message is " + errormsg);
		}

		return currentDate;
	}

	public String getPreviousMonthDate() {
		String prevDate = "";
		String currentDate = GetCurrentDate();

		try {
			SimpleDateFormat sdf = new SimpleDateFormat("mm/dd/yyyy");
			Calendar c = Calendar.getInstance();
			c.setTime(sdf.parse(currentDate));
			c.add(Calendar.DAY_OF_MONTH, -30);
			prevDate = sdf.format(c.getTime());

		} catch (Exception e) {
			e.printStackTrace();
			String errormsg = e.getMessage();
			System.out.println("Error message is " + errormsg);
		}

		return prevDate;
	}

	public String getNextMonthDate() {
		String nextDate = "";
		String currentDate = GetCurrentDate();

		try {
			SimpleDateFormat sdf = new SimpleDateFormat("mm/dd/yyyy");
			Calendar c = Calendar.getInstance();
			c.setTime(sdf.parse(currentDate));
			c.add(Calendar.DAY_OF_MONTH, 30);
			nextDate = sdf.format(c.getTime());

		} catch (Exception e) {
			e.printStackTrace();
			String errormsg = e.getMessage();
			System.out.println("Error message is " + errormsg);
		}

		return nextDate;
	}

	public String AddNumberOfDays(String xDate, int numberOfDays) {
		String newDate = "";

		try {
			SimpleDateFormat sdf = new SimpleDateFormat("mm/dd/yyyy");
			Calendar c = Calendar.getInstance();
			c.setTime(sdf.parse(xDate));
			c.add(Calendar.DAY_OF_MONTH, numberOfDays);
			newDate = sdf.format(c.getTime());

		} catch (Exception e) {
			e.printStackTrace();
			String errormsg = e.getMessage();
			System.out.println("Error message is " + errormsg);
		}

		return newDate;
	}

	public String getPreviousYearDate() {
		String prevDate = "";
		String currentDate = GetCurrentDate();

		try {
			SimpleDateFormat sdf = new SimpleDateFormat("mm/dd/yyyy");
			Calendar c = Calendar.getInstance();
			c.setTime(sdf.parse(currentDate));
			c.add(Calendar.YEAR, -1);
			prevDate = sdf.format(c.getTime());

		} catch (Exception e) {
			e.printStackTrace();
			String errormsg = e.getMessage();
			System.out.println("Error message is " + errormsg);
		}

		return prevDate;
	}

	public static boolean verifyDropdownValues(String xpath, String dropdownList, String attribute) {
		driver.manage().timeouts().implicitlyWait(4000, TimeUnit.SECONDS);
		String[] expectedDropdownValuesArray = dropdownList.split(";");
		System.out.println("Expected Values :" + dropdownList);
		List<String> expectedDropdownList = new ArrayList<String>(Arrays.asList(expectedDropdownValuesArray));

		List<String> actualDropdownList = new ArrayList<String>();
		List<WebElement> dropdownValues = driver.findElements(By.xpath(xpath));
		for (WebElement value : dropdownValues) {
			if (!value.getAttribute(attribute).equalsIgnoreCase("--None--")) {
				System.out.println("actual dropdown: " + value.getAttribute(attribute));
				actualDropdownList.add(value.getAttribute(attribute));
			}
		}
		System.out.println("expecteddrop :" + expectedDropdownList);
		System.out.println("actualdrop :" + actualDropdownList);
		return true;
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

	public String RandomNumberGeneration(int number) {
		String Number = "1234567890";
		StringBuilder sb = new StringBuilder();
		Random random = new Random();
		int length = number;
		for (int i = 0; i < length; i++) {
			int index = random.nextInt(Number.length());
			char randomChar = Number.charAt(index);
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
		String randomString = sb.toString() + "@gmail.com";
		return randomString;
	}

	public String getTimeStamp() {
		String today;
		DateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
		Calendar calendar = Calendar.getInstance();
		today = dateFormat.format(calendar.getTime());
		return today;
	}

	public String takeScreenShot(String screenShotName, String FeatureName) {
		try {
			File file = new File("Screenshots" + fileSeperator + "Results");
			if (!file.exists()) {
				System.out.println("File created " + file);
				file.mkdir();
			}
			File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			File targetFile = new File("Screenshots" + fileSeperator + "Results" + fileSeperator + FeatureName,
					screenShotName);
			FileUtils.copyFile(screenshotFile, targetFile);

			return screenShotName;
		} catch (Exception e) {
			System.out.println("An exception occured while taking screenshot " + e.getCause());
			return null;
		}
	}

	public void JSclick(WebElement we, WebDriver driver) {
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", we);
	}

	public void JSHighlightElement(WebElement we, WebDriver driver) {

		for (int i = 0; i < 2; i++) {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].setAttribute('style', arguments[1]);", we,
					"color: red; border: 3px solid red;");
			js.executeScript("arguments[0].setAttribute('style', arguments[1]);", we, "");
		}
	}

	public void JSEnterText(WebElement we, WebDriver driver, String requiredtestdata) {
		((JavascriptExecutor) driver).executeScript("arguments[0].value=" + requiredtestdata + ";", we);
	}

}
