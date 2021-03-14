package com.jakartalabs.baseui;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.jakartalabs.fullstack_automation.IDriverManager;
import com.jakartalabs.utils.BrowserActions;
import com.jakartalabs.utils.DataUtils;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest implements IDriverManager {
	public ThreadLocal<WebDriver> driver = new ThreadLocal<>();
	public ThreadLocal<WebDriverWait> explicitWait = new ThreadLocal<>();

	protected BrowserActions browserActions;

	@Override
	@BeforeMethod
	public void setUpSystemUnderTest() {
		WebDriverManager.chromedriver().setup();

		Map<String, String> mobileProps = new HashMap<String, String>();
		mobileProps.put("deviceName", "iPhone X");
		ChromeOptions chromeOptions = new ChromeOptions();
		// chromeOptions.addArguments("--headless");
		// chromeOptions.setExperimentalOption("mobileEmulation", mobileProps);

		driver.set(new ChromeDriver(chromeOptions));
		driver.get().manage().window().maximize();

		explicitWait.set(new WebDriverWait(driver.get(), Duration.ofMinutes(1)));

		browserActions = new BrowserActions(driver, explicitWait);
		browserActions.openUrl(DataUtils.getDataFromExcel("Config", "BaseUrlUI"));
	}

	@Override
	@AfterMethod(alwaysRun = true)
	public void cleanUp(ITestResult result) throws IOException {
		if (result.getStatus() == ITestResult.FAILURE) {
			File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(screenshotFile,
					new File(System.getProperty("user.dir") + File.separator + result.getName() + ".png"));
		}

		driver.get().quit();
	}
}
