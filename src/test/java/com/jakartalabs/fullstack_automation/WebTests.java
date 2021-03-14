package com.jakartalabs.fullstack_automation;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.jakartalabs.baseui.BaseTest;
import com.jakartalabs.page_objects.HomePage;
import com.jakartalabs.page_objects.LandingPage;
import com.jakartalabs.page_objects.LoginPage;
import com.jakartalabs.utils.DataUtils;

public class WebTests extends BaseTest {
	@Test
	public void login() throws InterruptedException {
		String credential = DataUtils.getDataFromExcel("Config", "UIUsername");

		browserActions.clickElementByXpath(HomePage.loginButtonxpath);
		browserActions.sendKeysToElementByID(LoginPage.usernameFieldID, credential);
		browserActions.sendKeysToElementByID(LoginPage.passwordFieldID, credential);
		browserActions.clickElementByCSS(LoginPage.submitButtonCSS);

		String actualUsername = browserActions.getTextOfElementByCSS(LandingPage.userGreetingCSS);

		Assert.assertEquals(actualUsername, credential);
	}

	@Test
	public void loginWithWrongCredentials() {
		String credential = "fullstackdemos";

		browserActions.clickElementByXpath(HomePage.loginButtonxpath);
		browserActions.sendKeysToElementByID(LoginPage.usernameFieldID, credential);
		browserActions.sendKeysToElementByID(LoginPage.passwordFieldID, credential);
		browserActions.clickElementByCSS(LoginPage.submitButtonCSS);

		Assert.assertEquals(true, driver.get().findElement(By.cssSelector(LoginPage.errorMessageCSS)).isDisplayed());
	}

	@Test
	public void testTitle() {
		Assert.assertEquals(driver.get().getTitle(), "Home -");
	}

	@Test
	public void keyboardAndMouseSample() {
		browserActions.clickElementByXpath(HomePage.loginButtonxpath);
		browserActions.clickElementByID(LoginPage.usernameFieldID);
		browserActions.setKeyboardKey(LoginPage.usernameFieldID, Keys.NUMPAD4);
		browserActions.performRightClick(LoginPage.usernameFieldID);
		System.out.println();

	}

	@Test
	public void testSwitches() {

		browserActions.createTab();
		browserActions.switchDriver(1);
		browserActions.openUrl("https://facebook.com");
		browserActions.createWindow();
		browserActions.switchDriver(2);
		browserActions.openUrl("https://google.com");
		browserActions.switchDriver(0);
		System.err.println();

	}

	@Test
	public void testJS() {
		String script = "alert('my script')";
		browserActions.executeJS(script);
		browserActions.acceptAlert();
		System.out.println();

	}

	@Test
	public void refreshPage() throws InterruptedException {
		Thread.sleep(5000);
		browserActions.refresh();
		Thread.sleep(5000);

	}
}
