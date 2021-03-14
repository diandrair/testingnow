package com.jakartalabs.utils;

import java.util.ArrayList;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BrowserActions {
	public ThreadLocal<WebDriver> driver = new ThreadLocal<>();
	public ThreadLocal<WebDriverWait> explicitWait = new ThreadLocal<>();

	/**
	 * @param               driver.get()2
	 * @param explicitWait2
	 */
	public BrowserActions(ThreadLocal<WebDriver> driver2, ThreadLocal<WebDriverWait> explicitWait2) {
		this.driver = driver2;
		this.explicitWait = explicitWait2;
	}

	public void clickElementByXpath(String elementXpath) {
		WebElement elementByXpath = explicitWait.get()
				.until(ExpectedConditions.elementToBeClickable(driver.get().findElement(By.xpath(elementXpath))));
		elementByXpath.click();
	}

	public void clickElementByID(String elementID) {
		WebElement elementByID = explicitWait.get()
				.until(ExpectedConditions.elementToBeClickable(driver.get().findElement(By.id(elementID))));
		elementByID.click();
	}

	public void clickElementByCSS(String elementCSS) {
		WebElement elementByCSS = explicitWait.get()
				.until(ExpectedConditions.elementToBeClickable(driver.get().findElement(By.cssSelector(elementCSS))));
		elementByCSS.click();
	}

	public void sendKeysToElementByID(String elementID, String keys) {
		WebElement elementByID = explicitWait.get()
				.until(ExpectedConditions.visibilityOf(driver.get().findElement(By.id(elementID))));
		elementByID.sendKeys(keys);
	}

	public void setKeyboardKey(String elementID, Keys key) {
		WebElement elementByID = explicitWait.get()
				.until(ExpectedConditions.visibilityOf(driver.get().findElement(By.id(elementID))));
		elementByID.sendKeys(key);
	}

	public String getTextOfElementByCSS(String elementCSS) {
		WebElement elementInPage = explicitWait.get()
				.until(ExpectedConditions.visibilityOf(driver.get().findElement(By.cssSelector(elementCSS))));
		return elementInPage.getText();
	}

	public void performRightClick(String elementID) {
		Actions mouseAction = new Actions(driver.get());

		mouseAction.contextClick(driver.get().findElement(By.id(elementID))).perform();

	}

	public void createTab() {
		driver.get().switchTo().newWindow(WindowType.TAB);
	}

	public void createWindow() {
		driver.get().switchTo().newWindow(WindowType.WINDOW);
	}

	public void switchDriver(int index) {

		Set<String> windows = driver.get().getWindowHandles();

		ArrayList<String> windowsList = new ArrayList<String>(windows);

		driver.get().switchTo().window(windowsList.get(index));

	}

	public void openUrl(String url) {
		driver.get().get(url);
	}

	public void executeJS(String script) {
		JavascriptExecutor js = (JavascriptExecutor) driver.get();
		js.executeScript(script);

	}

	public void acceptAlert() {

		driver.get().switchTo().alert().accept();

	}

	public void refresh() {
		driver.get().navigate().refresh();
	}

}
