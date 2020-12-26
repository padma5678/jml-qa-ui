package StepDefinitions;



import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.lowagie.text.html.WebColors;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;


import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import util.WebController;

public class Hooks {
	public static Scenario scenario;
	public static WebDriver driver;
	public static String env;
	public static String browser;
	protected static String fileSeperator = System.getProperty("file.separator");
	private Properties proplocal = null;
	public static String screencapture_flag = "Yes";
	public Duration timeout;
	public WebController wc;

	@Before
	public void setsuite(Scenario scenario) throws InvocationTargetException, Exception 
	{
		wc = new WebController();
		proplocal = new Properties();
		proplocal.load(new FileInputStream("Chronicle.properties"));
		browser = proplocal.getProperty("Browser");
		createanddelete_Dir();	


		if (browser.equalsIgnoreCase("chrome") || browser.equalsIgnoreCase("Google chrome")) {
			ChromeOptions chromeOptions = new ChromeOptions();
			HashMap chromePrefs = new HashMap();
			chromePrefs.put("download.default_directory", "//tmp//automation_downloads");
			chromeOptions = new ChromeOptions();
			chromeOptions.setExperimentalOption("useAutomationExtension", false);
			chromeOptions.setExperimentalOption("prefs", chromePrefs);
			chromeOptions.setExperimentalOption("excludeSwitches", new String[] { "enable-automation" });
			System.setProperty("webdriver.chrome.driver", "Drivers" + File.separator + "chromedriver.exe");
			System.setProperty("webdriver.chrome.silentOutput", "true");
			driver = new ChromeDriver(chromeOptions);
			driver.manage().deleteAllCookies();
			System.out.println("\n"+"**************************** <<< >>>> *******************************");
			System.out.println("TEST INFO >>> Test Execution started in "+"-"+browser+"Browser");
			System.out.println("\n"+"**************************** <<< >>>> *******************************");
		}

		else if (browser.equalsIgnoreCase("Chromium") || browser.equalsIgnoreCase("Microsoft Edge")|| browser.equalsIgnoreCase("Edge")) 
		{
			EdgeOptions options = new EdgeOptions();
			options.addArguments("disable-extensions");
			options.setExperimentalOption("useAutomationExtension", false);
			options.addArguments("--no-sandbox");
			options.setPageLoadStrategy(PageLoadStrategy.EAGER);
			System.setProperty("webdriver.edge.driver", "Drivers" + File.separator + "msedgedriver.exe");
			try {
				driver = new EdgeDriver(options);
				driver.manage().deleteAllCookies();
				System.out.println("\n"+"**************************** <<< >>>> *******************************");
				System.out.println("TEST INFO >>> Test Execution started in "+"-"+browser+"Browser");
				System.out.println("\n"+"**************************** <<< >>>> *******************************");
			}
			catch (Exception e) {
				System.out.println(e);
			}
	}

}

	@After
	public void takescreenshot(Scenario scenario) throws Throwable {
		tearDown(scenario);
		driver.close();
		driver.quit();

	}

	public void tearDown(Scenario scenario) throws Exception {
		try {

			if (scenario.isFailed()) {
				String fileSeperator = null;
				String screenShotName = scenario.getName() + " " + getTimeStamp() + ".png";
				String TotalFileName = scenario.getId().split(";")[0];
				String FeatureName = TotalFileName.replace("-", "_").toUpperCase();
				String imagePath = ".." + fileSeperator + "Screenshots" + fileSeperator + "Results" + fileSeperator
						+ FeatureName + fileSeperator + takeScreenShot(screenShotName, FeatureName);
				System.out.println("TESTINFO >> Script Failed & Screenshot Captured");

				byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
				scenario.embed(screenshot, "image/png");

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

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

	public void createanddelete_Dir() throws IOException {
		if ((screencapture_flag.equalsIgnoreCase("Yes"))) {
			String Path = System.getProperty("user.dir") + "\\" + "Screenshots";
			File file = new File(Path);
			FileUtils.deleteDirectory(new File(Path));
			file.mkdir();
			screencapture_flag = "No";

		}
	}
}